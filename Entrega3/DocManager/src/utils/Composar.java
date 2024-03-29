package utils;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Composar {
    public Composar() {
    }

    public String getExtension(String path) {
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

    public void guardar_document(String titol, String autor, String contingut, String ubicacio) throws IOException {
        String body = null;
        String extensio = getExtension(ubicacio);
        
        if (extensio.equals("txt")) {
            body = stringstoTxt(autor, contingut);
        }
        else if (extensio.equals("xml")) {
            body = stringstoXml(autor, contingut);   
        }
        else if (extensio.equals("json")) {
            body = stringstoJSON(titol, autor, contingut);
        }
        
        //guardar
        Path path = Paths.get(ubicacio);

        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        writer.write(body);
        writer.flush();
    }

    private String stringstoTxt(String autor, String contingut) {
        String body = autor + "\n";
        if (contingut != null) {
            body = body + contingut;
        }
        return body;
    }

    private String stringstoXml(String autor, String contingut) {
        String body = ("<author>" + autor + "</author>");
        if (contingut != null) {
            String lines[] = contingut.split("\\r?\\n");
            for (String line : lines) {
                body = body + ("\n<body>" + line + "</body>");
            }
        }
        return body;
    }

    private String stringstoJSON(String titol, String autor, String contingut) {
        String body = ("{\"titol\":\"" + titol + "\",\"autor\":\"" + autor);
        if (contingut != null) {
            contingut = contingut.replace("\n", "\\n");
            body = body + ("\",\"contingut\":\"" + contingut);
        }
        body = body + "\"}";
        return body;
    }
    
}
