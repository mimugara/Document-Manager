package datalayer.databaseinterface;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;
import domainlayer.model.Document;

public interface DocumentDB {
    
    /**
     * Insert Document on Database
     * @param d
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void insert(Document d) throws DatabaseInconsistentException, IOException;
    /**
     * Upadte Document on Database
     * @param d
     * @param c
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void update(Document d, String c) throws DatabaseInconsistentException, IOException;
    /**
     * Delete Document on Database
     * @param d
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void delete(Document d) throws DatabaseInconsistentException, IOException;
    /**
     * Load Content from existing Document on database
     * @param d
     * @return
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public String load_content(Document d) throws DatabaseInconsistentException, IOException, ParseException;
    /**
     * Get Number of docs in database
     * @return Number of docs in Database
     * @throws DatabaseInconsistentException
     */
    public int get_num_doc() throws DatabaseInconsistentException;
    /**
     * Initialize Database
     * @param root_data
     * @return All the Documents found on the Database, in order to sync with runtime memory
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public HashMap<String,HashMap<String,Document>> init_database(String root_data) throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException;

    /**
     * Reset Functionality, resets the whole database of Documents
     * @throws IOException
     */
    public void reset() throws IOException;
}
