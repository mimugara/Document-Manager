package database;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.BiMap;
import utils.PseudoDocument;
import utils.DocumentJSON;
import utils.RepVecJSON;
import utils.exceptions.database.*;

import domainlayer.model.Document;
import datalayer.databaseinterface.DocumentDB;

public class DocumentFile implements DocumentDB{

    /**
     * Container that stores the translations between IDs in Database and Documents
     */
    private BiMap id2doc;
    /**
     * Reference to DATA folder, where all the Documents identifiers are stored
     */
    File data_folder = null; 
    /**
     * Reference to DATA/RepVec folder, where the representations of Documents are stored
     */
    File repvec_folder = null;
    /**
     * reference to DATA/Contingut folder, where all the contents of Documents are stored
     */
    File content_folder = null;

    // IOException or DatabaseInconsistentException
    /**
     * @see datalayer.databaseinterface.DocumentDB#insert(domainlayer.model.Document)
     */
    public void insert(Document d) throws DatabaseInconsistentException, IOException{
        Integer id = id2doc.get(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        if(id != null) throw new DatabaseInconsistentException();

        
        id = id2doc.put(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        File doc = new File(data_folder.getAbsolutePath(),Integer.toString(id) + ".json");
        File repvec = new File(repvec_folder.getAbsolutePath(),Integer.toString(id) + ".json");
        File content = new File(content_folder.getAbsolutePath(),Integer.toString(id) + ".json");
        //System.out.println("CURRENT ID:" + Integer.toString(id));
        //System.out.println("FILES ABOUT TO CREATE");

        FileWriter wd = new FileWriter(doc,false);
        DocumentJSON djs = new DocumentJSON(d);
        wd.write(djs.toJSONString());
        wd.close();

        FileWriter wr = new FileWriter(repvec,false);
        RepVecJSON rpjs = new RepVecJSON(d.get_repvec());
        wr.write(rpjs.toJSONString());
        wr.close();

        FileWriter wc = new FileWriter(content,false);
        HashMap<String,String> obj = new HashMap<String,String>();
        obj.put("contingut", d.get_contingut());
        JSONObject cjs = new JSONObject(obj);
        wc.write(cjs.toJSONString());
        wc.close();  
        return;
    }

    /**
     * @see datalayer.databaseinterface.DocumentDB#update(domainlayer.model.Document, java.lang.String)
     */
    public void update(Document d, String c) throws DatabaseInconsistentException, IOException {
        Integer id = id2doc.get(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        FileFilter name = file -> file.getName().split("\\.")[0].equals(Integer.toString(id));
        File[] content_files = content_folder.listFiles(name);
        if (content_files.length != 1) throw new DatabaseInconsistentException();
        File[] repvec_files = repvec_folder.listFiles(name);
        if (repvec_files.length != 1) throw new DatabaseInconsistentException();

        FileWriter wr_cont = new FileWriter(content_files[0],false);
        HashMap<String,String> obj = new HashMap<String,String>();
        obj.put("contingut", c);
        JSONObject content = new JSONObject(obj);
        wr_cont.write(content.toJSONString());
        wr_cont.close();

        FileWriter wr_repvec = new FileWriter(repvec_files[0],false);
        RepVecJSON rpjs = new RepVecJSON(d.get_repvec());
        wr_repvec.write(rpjs.toJSONString());
        wr_repvec.close();
        return;
    }

    /**
     * @see datalayer.databaseinterface.DocumentDB#delete(domainlayer.model.Document)
     */
    public void delete(Document d) throws DatabaseInconsistentException, IOException{
        Integer id = id2doc.get(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        FileFilter name = file -> file.getName().split("\\.")[0].equals(Integer.toString(id));
        File[] data_files = data_folder.listFiles(name);
        if (data_files.length != 1) throw new DatabaseInconsistentException();
        File[] content_files = content_folder.listFiles(name);
        if (content_files.length != 1) throw new DatabaseInconsistentException();
        File[] repvec_files = repvec_folder.listFiles(name);
        if (repvec_files.length != 1) throw new DatabaseInconsistentException();

        data_files[0].delete();
        content_files[0].delete();
        repvec_files[0].delete();

        id2doc.remove(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        return;
    }

    /**
     * @see datalayer.databaseinterface.DocumentDB#load_content(domainlayer.model.Document)
     */
    public String load_content(Document d) throws DatabaseInconsistentException, IOException, ParseException {
        Integer id = id2doc.get(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()));
        FileFilter name = file -> file.getName().split("\\.")[0].equals(Integer.toString(id));
        File[] content_files = content_folder.listFiles(name);
        if (content_files.length != 1) throw new DatabaseInconsistentException();

        FileReader rd = new FileReader(content_files[0]);
        JSONParser parser = new JSONParser();
        JSONObject js = (JSONObject) parser.parse(rd);
        return (String) js.get("contingut");
    }


    /**
     * @see datalayer.databaseinterface.DocumentDB#init_database(java.lang.String)
     */
    public HashMap<String,HashMap<String,Document>> init_database(String root_data) throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException{

        // root_data = DATA folder

        HashMap<String,HashMap<String,Document>> llistDoc = new HashMap<String,HashMap<String,Document>>();

        data_folder = new File(root_data);
        if(!data_folder.isDirectory()) throw new FolderNotFoundException("DATA Folder Not Found");
        repvec_folder = new File(root_data,"RepVec");
        if(!repvec_folder.isDirectory()) throw new FolderNotFoundException("DATA/RepVec Folder Not Found");
        content_folder = new File(root_data,"Contingut");
        if(!content_folder.isDirectory()) throw new FolderNotFoundException("DATA/Contingut Folder Not Found");

        id2doc = new BiMap();

        JSONParser parser = new JSONParser();
        FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith("json");
        
        for(File f : data_folder.listFiles(fileFilter)) {
            //if(data_folder.getAbsolutePath().endsWith("DATA")) System.out.println(f.getName());
            FileReader rd = new FileReader(f);
            JSONObject js;

            js = (JSONObject) parser.parse(rd);
            PseudoDocument pdoc = (new DocumentJSON(js)).unmarshall();
            //if(data_folder.getAbsolutePath().endsWith("DATA")) System.out.println(js.toJSONString());

            Integer prevId = id2doc.put(pdoc, Integer.parseInt(f.getName().split("\\.")[0]));
            if(prevId != null) throw new DatabaseInconsistentException();

            FileFilter nameFilter = file -> file.getName().equals(f.getName());
            File[] repvec_file = repvec_folder.listFiles(nameFilter);
            if (repvec_file.length != 1) throw new DatabaseInconsistentException();

            File[] content_file = content_folder.listFiles(nameFilter);
            if (content_file.length != 1) throw new DatabaseInconsistentException();

            rd = new FileReader(repvec_file[0]);
            js = (JSONObject) parser.parse(rd);
            RepVecJSON rv = (new RepVecJSON(js));

            /////////////////////////////////////////

            HashMap<String,Document> titol_doc = llistDoc.get(pdoc.get_autor());
            if(titol_doc == null) titol_doc = new HashMap<String,Document>();
            Document d_aux = null;
            try {
                d_aux = new Document(pdoc.get_format(), pdoc.get_titol(), pdoc.get_autor(), rv.unmarshall());
            } catch (Exception e) {
                // No need to throw, it is safe.
            }
            titol_doc.put(pdoc.get_titol(), d_aux);
            llistDoc.put(pdoc.get_autor(),titol_doc);
            
        }

        return llistDoc;
    }

    /**
     * @see datalayer.databaseinterface.DocumentDB#get_num_doc()
     */
    public int get_num_doc() throws DatabaseInconsistentException {
        int n = -1;
        n = id2doc.size();
        return n;
    }

    /**
     * @see datalayer.databaseinterface.DocumentDB#reset()
     */
    public void reset() throws IOException{

        FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith("json");

        for(File f : data_folder.listFiles(fileFilter)) f.delete();
        for(File f : repvec_folder.listFiles()) f.delete();
        for(File f : content_folder.listFiles()) f.delete();
        id2doc.clear();

    }

    ////////////// Functions to enable testing, not on the interface -> not accessible in our program, as it calls this object through interface ///////

    public BiMap get_id2doc() {
        return id2doc;
    }
    
    public File get_data_folder() {
        return data_folder;
    }

    public File get_repvec_folder() {
        return repvec_folder;
    }

    public File get_content_folder() {
        return content_folder;
    }

    
}
