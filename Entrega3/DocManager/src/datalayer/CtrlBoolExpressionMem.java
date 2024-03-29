package datalayer;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import utils.exceptions.boolexpression.BoolExpressionCampNullException;
import utils.exceptions.boolexpression.BoolExpressionIdentificadorJaExisteixException;
import utils.exceptions.boolexpression.BoolExpressionIdentificadorNoExisteixException;
import utils.exceptions.boolexpression.InvalidLiteralException;
import utils.exceptions.boolexpression.InvalidSequenceException;
import utils.exceptions.boolexpression.InvalidSetException;
import utils.exceptions.boolexpression.WronglyParenthesizedException;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;

import datalayer.databaseinterface.BoolExpressionDB;
import datalayer.databaseinterface.FactoriaDB;
import domainlayer.datainterface.CtrlBoolExpression;
import domainlayer.model.BoolExpression;


public class CtrlBoolExpressionMem implements CtrlBoolExpression{

    // Array to store all loaded BoolExpressions
    /**
     * Container for all the BoolExpressions in memory
     */
    private HashMap<String,String> bools;
    /**
     * Reference to the Database
     */
    private FactoriaDB factDatabase;

    /**
     * Default constructor
     * @throws FolderNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public CtrlBoolExpressionMem() throws FolderNotFoundException, IOException, ParseException{
        factDatabase = FactoriaDB.getInstance();
        bools = factDatabase.getBoolExpressionDB().init_database("DATA");
    }

    /**
     * @see domainlayer.datainterface.CtrlBoolExpression#alta_BoolExpression(java.lang.String, java.lang.String)
     */
    public BoolExpression alta_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorJaExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException {
        if(bools.containsKey(_nom)) throw new BoolExpressionIdentificadorJaExisteixException();
        
        BoolExpression b = new BoolExpression(q);
        bools.put(_nom,q);

        // Call to database element to handle persistance
        BoolExpressionDB database = factDatabase.getBoolExpressionDB();
        database.insert(_nom,q);  
        
        return b;
    }

    /**
     * @see domainlayer.datainterface.CtrlBoolExpression#mod_BoolExpression(java.lang.String, java.lang.String)
     */
    public BoolExpression mod_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorNoExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException {
        if(!bools.containsKey(_nom)) throw new BoolExpressionIdentificadorNoExisteixException();
        
        BoolExpression b = new BoolExpression(q);
        bools.put(_nom,q);

        // Call to database element to handle persistance
        BoolExpressionDB database = factDatabase.getBoolExpressionDB();
        database.update(_nom,q);  

        return b;
    }

    /**
     * @see domainlayer.datainterface.CtrlBoolExpression#baixa_BoolExpression(java.lang.String)
     */
    public void baixa_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException, IOException, DatabaseInconsistentException {
        if(!bools.containsKey(_nom)) throw new BoolExpressionIdentificadorNoExisteixException();

        // Call to database element to handle persistance
        BoolExpressionDB database = factDatabase.getBoolExpressionDB();
        database.delete(_nom);  

        bools.remove(_nom);
    }

    /**
     * @see domainlayer.datainterface.CtrlBoolExpression#get_BoolExpression(java.lang.String)
     */
    public BoolExpression get_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException {
        if(!bools.containsKey(_nom)) throw new BoolExpressionIdentificadorNoExisteixException();
        
        String q = bools.get(_nom);
        BoolExpression b = null;
        try {
            b = new BoolExpression(q);
        } catch (Exception e) {
            // If it was stored, it is safe to ignore it
        }
        return b;
    }

    /**
     * @see domainlayer.datainterface.CtrlBoolExpression#reset()
     */
    public void reset() throws IOException, ParseException {
        bools = new HashMap<String,String>();

        BoolExpressionDB database = factDatabase.getBoolExpressionDB();
        database.reset();  
    }
    
}
