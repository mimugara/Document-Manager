package domainlayer.datainterface;

import datalayer.CtrlDocumentMem;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import datalayer.CtrlBoolExpressionMem;

/**
 * The controller of the Factoria
 */
public class FactoriaCtrl {

    // Singleton --------------------------------------------------------------
    /**
     * Reference to the only instance that allows this class. Singleton Pattern
     */
    private static FactoriaCtrl single_instance = null;

    /**
     * Private Constructor for Singleton Object
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    private FactoriaCtrl() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException {
        docs = new CtrlDocumentMem();
        bools = new CtrlBoolExpressionMem();        
    }

    /**
     * Get Instance
     * @return The only existing instance of the class. Singleton Pattern.
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public static FactoriaCtrl getInstance() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException{
        if(single_instance == null) single_instance = new FactoriaCtrl();
        return single_instance;
    }
    // ------------------------------------------------------------------------
    
    // Other controllers 0..1
    /**
     * Reference to the Document controller in memory, ensures only one instance of it
     */
    CtrlDocument docs = null;
    /**
     * Reference to the BoolExpression controller in memory, ensures only one instance of it
     */
    CtrlBoolExpression bools = null;

    /**
     * Get Document Controller
     * @return Document controller
     */
    public CtrlDocument getCtrlDocument() {
        return docs;
    }

    /**
     * Get BoolExpression Controller
     * @return BoolExpression controller
     */
    public CtrlBoolExpression getCtrlBoolExpression() {
        return bools;
    }

    
}
