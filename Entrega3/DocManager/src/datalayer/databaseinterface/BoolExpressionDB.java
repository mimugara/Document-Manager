package datalayer.databaseinterface;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;

public interface BoolExpressionDB {
    
    /**
     * Insert BoolExpression in Database
     * @param name
     * @param query
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void insert(String name, String query) throws DatabaseInconsistentException, IOException;
    /**
     * Update BoolExpression in Database
     * @param name
     * @param query
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void update(String name, String query) throws DatabaseInconsistentException, IOException;
    /**
     * Delete BoolExpression in Database
     * @param name
     * @throws DatabaseInconsistentException
     * @throws IOException
     */
    public void delete(String name) throws DatabaseInconsistentException, IOException;
    /**
     * Initialize the Database
     * @param root_data
     * @return All the BoolExpressions found on the Database, in order to sync with runtime memory
     * @throws FolderNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public HashMap<String,String>init_database(String root_data) throws FolderNotFoundException, IOException, ParseException;

    /**
     * Reset Functionality, resets the whole database of BoolExpressions
     * @throws IOException
     */
    public void reset() throws IOException, ParseException;
}
