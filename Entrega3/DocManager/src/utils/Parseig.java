package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.*;

import utils.exceptions.document.DocumentCampNullException;

import org.json.simple.JSONObject;

public class Parseig {
    public Parseig() {
    }
    
    private String getExtension(String path) {
        File file = new File(path);
        String extension = "";
        if (file.exists()) {
            String fileName = file.getName();

            int i = fileName.lastIndexOf('.');
  
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }
        }
        return extension;
    }

    public String[] anytoStrings(String path) throws DocumentCampNullException, IOException, ParseException, FileNotFoundException {
        String extension = getExtension(path);
        if (extension.equals("txt"))
            return txttoStrings(path);
        else if (extension.equals("xml"))
            return xmltoStrings(path);
        else if (extension.equals("json"))
            return jsontoStrings(path);
        return null;
    }

    private String[] txttoStrings(String path) throws FileNotFoundException, DocumentCampNullException {
        File file = new File(path);
        String[] info = new String[4];
        info[0] = "txt";
        info[1] = file.getName().replaceFirst("[.][^.]+$", "");
        if (info[1] == null) throw new DocumentCampNullException();
        Scanner lector = new Scanner(file);
        if (lector.hasNextLine())
            info[2] = lector.nextLine();
        if (info[2] == null) {
            lector.close();
            throw new DocumentCampNullException();
        }
        if (lector.hasNextLine())
            info[3] = lector.nextLine();
        while (lector.hasNextLine())
            info[3] = info[3] + "\n" + lector.nextLine();
        lector.close();
        return info;
    }

    private String[] xmltoStrings(String path) throws FileNotFoundException, DocumentCampNullException {
        File file = new File(path);
        String[] info = new String[4];
        info[0] = "xml";
        info[1] = file.getName().replaceFirst("[.][^.]+$", "");
        if (info[1].length() == 0) throw new DocumentCampNullException();
        Scanner lector = new Scanner(file);
        String linia;
        while (lector.hasNextLine()) {
            linia = lector.nextLine();
            if (linia.contains("<author>")) { 
                linia = linia.strip();
                linia = linia.substring(8, linia.length()-9);
                info[2] = linia;
            }
            else if (linia.contains("<body>")) {
                linia = linia.strip();
                linia = linia.substring(6, linia.length()-7);
                if (info[3] == null)
                    info[3] = linia;
                else
                    info[3] = info[3] + "\n" + linia;   
            }
        }
        lector.close();
        
        if (info[2] == null || info[2].length() == 0) throw new DocumentCampNullException();
        
        return info;
    }

    private String[] jsontoStrings(String path) throws FileNotFoundException, IOException, ParseException, DocumentCampNullException {
        File file = new File(path);
        String[] info = new String[4];

        // parsing file
        Object obj = new JSONParser().parse(new FileReader(file));
          
        // typecasting obj to JSONObject to DocumentJSON
        JSONObject jo = (JSONObject) obj;
        DocumentJSON doc = new DocumentJSON(jo);
        PseudoDocument pDoc = doc.unmarshall();
        
        info[0] = "json";
        info[1] = pDoc.get_titol();
        if (info[1] == null) throw new DocumentCampNullException();
        info[2] = pDoc.get_autor();
        if (info[2] == null) throw new DocumentCampNullException();
        info[3] = (String) jo.get("contingut");
        info[3] = info[3].replace("\\n", "\n");

        return info;
    }

}
