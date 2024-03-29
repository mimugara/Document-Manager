package database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.exceptions.database.*;

import datalayer.databaseinterface.BoolExpressionDB;

@SuppressWarnings("unchecked")
public class BoolExpressionFile implements BoolExpressionDB{

    /**
     * Reference to DATA/BoolExpression in Database, where all the BoolExpressions are stored
     */
    File boolExpressionFolder;
    /**
     * Reference to map file
     */
    File map_file;
    /**
     * Map stored in map_file, which translates from BoolExpression name to query
     */
    JSONObject map;
    

    
    /**
     * @see datalayer.databaseinterface.BoolExpressionDB#init_database(java.lang.String)
     */
    public HashMap<String,String>init_database(String root_data) throws FolderNotFoundException, IOException, ParseException{
        boolExpressionFolder = new File(root_data,"BoolExpression");
        if(!boolExpressionFolder.isDirectory()) throw new FolderNotFoundException("DATA/BoolExpression Folder Not Found");

        map_file = new File(boolExpressionFolder.getAbsolutePath(),"map.json");
        HashMap<String,String> mapping = new HashMap<String,String>();

        if(map_file.exists()) {
            
            FileReader fr = new FileReader(map_file);
            JSONParser parser = new JSONParser();
            map = (JSONObject) parser.parse(fr);

            for(Object key : map.keySet()) {
                String q = (String) map.get(key);
                mapping.put((String) key, q);
            }
        }
        else {

            map_file.createNewFile();
            FileWriter fw = new FileWriter(map_file);
            fw.write("{}");  
            fw.close();    
            
            FileReader fr = new FileReader(map_file);
            JSONParser parser = new JSONParser();
            map = (JSONObject) parser.parse(fr);

            
        }

        return mapping;
        
    }

    // Throws IO Exception or DatabaseException
    /**
     * @see datalayer.databaseinterface.BoolExpressionDB#insert(java.lang.String, java.lang.String)
     */
    public void insert(String name, String query) throws DatabaseInconsistentException, IOException{
        String q = (String) map.get(name);
        if(q != null) throw new DatabaseInconsistentException();

        map.put(name, query);
        FileWriter fw = new FileWriter(map_file, false);
        fw.write(map.toJSONString());
        fw.close();

        System.out.println(map.toJSONString());

        return;
    }

    /**
     * @see datalayer.databaseinterface.BoolExpressionDB#update(java.lang.String, java.lang.String)
     */
    public void update(String name, String query) throws DatabaseInconsistentException, IOException{
        String q = (String) map.get(name);
        if(q == null) throw new DatabaseInconsistentException();

        map.put(name, query);
        FileWriter fw = new FileWriter(map_file, false);
        fw.write(map.toJSONString());
        fw.close();

        System.out.println(map.toJSONString());
        
        return;
    }

    /**
     * @see datalayer.databaseinterface.BoolExpressionDB#delete(java.lang.String)
     */
    public void delete(String name) throws DatabaseInconsistentException, IOException{
        String q = (String) map.get(name);
        if(q == null) throw new DatabaseInconsistentException();

        map.remove(name);
        FileWriter fw = new FileWriter(map_file, false);
        fw.write(map.toJSONString());
        fw.close();


        System.out.println(map.toJSONString());
        return;
    }


    /**
     * @see datalayer.databaseinterface.BoolExpressionDB#reset()
     */
    public void reset() throws IOException, ParseException{
        
        FileWriter fw = new FileWriter(map_file);
        fw.write("{}");  
        fw.close();

        FileReader fr = new FileReader(map_file);
        JSONParser parser = new JSONParser();
        map = (JSONObject) parser.parse(fr);

    }
    
}
