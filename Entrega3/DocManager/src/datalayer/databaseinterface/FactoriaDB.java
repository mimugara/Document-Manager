package datalayer.databaseinterface;

import database.BoolExpressionFile;
import database.DocumentFile;

public class FactoriaDB {

    // Singleton --------------------------------------------------------------
    /**
     * Reference to only existing instance of the class. Singleton Pattern
     */
    private static FactoriaDB single_instance = null;

    /**
     * Private constructor for Singleton Pattern
     */
    private FactoriaDB() {
        docs = new DocumentFile();
        bools = new BoolExpressionFile();        
    }

    /**
     * Get Instance
     * @return the only existing instance of the class
     */
    public static FactoriaDB getInstance() {
        if(single_instance == null) single_instance = new FactoriaDB();
        return single_instance;
    }
    // ------------------------------------------------------------------------
    
    // Other controllers 0..1
    /**
     * Reference to Document database
     */
    DocumentDB docs = null;
    /**
     * Reference to BoolExpression database
     */
    BoolExpressionDB bools = null;

    /**
     * Get Document database controller
     * @return The controller for the Documents in the persistance database
     */
    public DocumentDB getDocumentDB() {
        return docs;
    }

    /** Get BoolExpression database controller
     * @return The controller for the BoolExpressions in the persistance database
     */
    public BoolExpressionDB getBoolExpressionDB() {
        return bools;
    }
}
