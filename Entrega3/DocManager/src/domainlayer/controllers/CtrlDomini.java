package domainlayer.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import utils.Parseig;
import utils.Composar;
import utils.exceptions.database.*;
import utils.exceptions.document.*;
import utils.exceptions.boolexpression.*;


import domainlayer.model.*;
import domainlayer.datainterface.*;

/**
 * The domain controller of the application
 */
public class CtrlDomini {

    /**
     * Singleton object CtrlDomini
     */
    private static CtrlDomini single_instance = null;

    /**
     * Constructor of the class
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    private CtrlDomini() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException  {fc = FactoriaCtrl.getInstance();}
    
    /**
     * Returns the singleton object CtrlDomini
     * @return CtrlDomini
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public static CtrlDomini getInstance() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException {
        if(single_instance == null) single_instance = new CtrlDomini();
        return single_instance;
    }
    // ----------------------------------------------------------------------------------------------------

    /**
     * FactoriaCtrl object
     */
    FactoriaCtrl fc;

    /**
     * Creates the controller of the documents and then calls the method alta_doc with the corresponding parameters. Returns the Document that has been registered
     * @param format Format of the Document
     * @param autor Author of the Document
     * @param titol Title of the Document
     * @param contingut Author of the Document
     * @throws AutorTitolJaExisteixException
     * @throws DocumentCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public Document alta_document(String format, String autor, String titol, String contingut) throws AutorTitolJaExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException{
        CtrlDocument docs = fc.getCtrlDocument();
        Document n = docs.alta_doc(format, titol, autor, contingut);
        
        return n;
    }
    
    /**
     * Crea el controlador de documents y posteriorment al mètode mod_doc amb els paràmetres corresponents. Retorna el Document que s'ha modificat
     * @param autor Autor del Document
     * @param titol Titol del Document
     * @param contingut Nou ontingut del Document
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     * @throws DocumentCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public Document modificar_document(String autor, String titol, String contingut) throws AutorTitolNoExisteixException, AutorNoExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException {
        CtrlDocument docs = fc.getCtrlDocument();
        Document n = docs.mod_doc(titol, autor, contingut);
        
        return n;
    }

    /**
     * Creates the controller of the documents and then calls the method baixa_doc with the corresponding parameters. It is a 'void' procedure
     * @param autor Author of the Document
     * @param titol Title of the Document
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * 
     */
    public void baixa_document(String autor, String titol) throws AutorNoExisteixException, AutorTitolNoExisteixException, IOException, DatabaseInconsistentException{
        CtrlDocument docs = fc.getCtrlDocument();
        docs.baixa_doc(titol, autor);
        return;
    }
    
    /**
     * Creates the controller of the documents and then calls the method consultar_document with the corresponding parameters. Returns the content of a Document
     * @param autor Author of the Document
     * @param titol Title of the Document
     * 
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws ParseException
     *
     */
    public String mostrar_contingut(String autor, String titol) throws AutorNoExisteixException, AutorTitolNoExisteixException, IOException, DatabaseInconsistentException, ParseException {
        CtrlDocument docs = fc.getCtrlDocument();
        String n = docs.mostrar_contingut(titol, autor);
        return n;
    }

    /**
     * Creates the controller of the documents and then calls the method llistarPrefix with the corresponding parameter. Returns a list of authors whose name starts with the given prefix
     * @param autor Prefix of the authors names
     */
    public ArrayList<String> llistarPrefix(String autor) {
        CtrlDocument docs = fc.getCtrlDocument();
        ArrayList<String> n = docs.llistarPrefix(autor);
        return n;
    }

    /**
     * Creates the controller of the documents and then calls the method llistar_titols_unautor with the corresponding parameters. Returns a list of titles of the given author
     * @param autor Author of the Documents to be listed
     *
     * @throws AutorNoExisteixException
     */
    public ArrayList<String> llistar_titols_unautor(String autor) throws AutorNoExisteixException {
        CtrlDocument docs = fc.getCtrlDocument();
        ArrayList<String> n = docs.llistar_titols_unautor(autor);
        return n;
    }

    /**
     * Creates the controller of the documents and then calls the method ordenar_per_semblanca with the corresponding parameters. Returns a list of documents that are most similar
     * @param t Title of the Document
     * @param a Author of the Document
     * @param k Number of documents to be returned
     *
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     *  
     */
    public ArrayList<Document> ordenar_per_semblanca(String t, String a, Integer k) throws AutorNoExisteixException, AutorTitolNoExisteixException, FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException {
        CtrlDocument docs = fc.getCtrlDocument();
        ArrayList<Document> n = docs.ordenar_per_semblanca(t,a,k);
        return n;
    }
    
    /**
     * Creates the controller of boolean expressions and then calls the method alta_BoolExpression with the corresponding parameters. Returns the boolean expression that the user has created
     * @param _nom Name that identifies the boolean expression
     * @param q Boolean expression
     *
     * @throws BoolExpressionIdentificadorJaExisteixException
     * @throws BoolExpressionCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws WronglyParenthesizedException
     * @throws InvalidSetException
     * @throws InvalidSequenceException
     * @throws InvalidLiteralException
     * 
     *  
     */
    public BoolExpression alta_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorJaExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException {
        CtrlBoolExpression bools = fc.getCtrlBoolExpression();
        BoolExpression b =  bools.alta_BoolExpression(_nom, q);
        return b;
    }

    /**
     * Creates the controller of boolean expressions and then calls the method mod_BoolExpression with the corresponding parameters. Returns the modified boolean expression
     * @param _nom Name that identifies the boolean expression
     * @param q New boolean expression
     *
     * @throws BoolExpressionIdentificadorNoExisteixException
     * @throws BoolExpressionCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws WronglyParenthesizedException
     * @throws InvalidSetException
     * @throws InvalidSequenceException
     * @throws InvalidLiteralException
     * 
     *  
     */
    public BoolExpression mod_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorNoExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException{
        CtrlBoolExpression bools = fc.getCtrlBoolExpression();
        BoolExpression b =  bools.mod_BoolExpression(_nom, q);
        return b;
    }

    /**
     * Creates the controller of boolean expressions and then calls the method baixa_BoolExpression with the corresponding parameters. It is a void procedure
     * @param _nom Name that identifies the boolean expression
     *
     * @throws BoolExpressionIdentificadorNoExisteixException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * 
     *  
     */
    public void baixa_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException, IOException, DatabaseInconsistentException{
        CtrlBoolExpression bools = fc.getCtrlBoolExpression();
        bools.baixa_BoolExpression(_nom);
        return;
    }

    /**
     * Creates the controller of boolean expressions and then calls the method get_BoolExpression with the corresponding parameters. Returns the boolean expression identified by the name
     * @param _nom Name that identifies the boolean expression
     * @return The boolean expression identified by the name
     * 
     * @throws BoolExpressionIdentificadorNoExisteixException
     */
    public BoolExpression get_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException {
        CtrlBoolExpression bools = fc.getCtrlBoolExpression();
        BoolExpression b =  bools.get_BoolExpression(_nom);
        return b;
    }

    /**
     * Creaates the controller of boolean expressions, the controller of documents and then calls the method get_BoolExpression and bool_search with the corresponding parameters. Returns a list of documents according to the boolean expressions
     * @param _nom Name that identifies the boolean expression
     * 
     * @throws BoolExpressionIdentificadorNoExisteixException
     */
    public ArrayList<Document> bool_search(String _nom) throws BoolExpressionIdentificadorNoExisteixException{
        CtrlBoolExpression bools = fc.getCtrlBoolExpression();
        CtrlDocument docs = fc.getCtrlDocument();

        BoolExpression b =  bools.get_BoolExpression(_nom);
        ArrayList<Document> res = docs.bool_search(b);
        return res;
    }

    /**
     * Loads a document from a location on the computer if it is in txt, xml or json format.
     * @param path Path of the document to load
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws DocumentCampNullException
     * @throws AutorTitolJaExisteixException
     * @throws ParseException
     */
    public void carregar_document(String path) throws AutorTitolJaExisteixException, DocumentCampNullException, DatabaseInconsistentException, IOException, ParseException {
        Parseig parseig = new Parseig();
        String[] info = parseig.anytoStrings(path);
        alta_document(info[0], info[2], info[1], info[3]);
    }

    /**
     * Downloads a document to the computer in txt, xml or json format.
     * @param titol Title of the Document 
     * @param autor Author of the Document 
     * @param path Path where the document will be saved
     * @throws ParseException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     */
    public void guardar_document(String titol, String autor, String path) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException, ParseException {
        CtrlDocument docs = fc.getCtrlDocument();
        String contingut = docs.mostrar_contingut(titol, autor);
        
        Composar composar = new Composar();
        composar.guardar_document(titol, autor, contingut, path);
    }

    /**
     * Reset Functionality, deletes all Documents and BoolExpressions
     * 
     * @throws IOException
     * @throws ParseException
     */
    public void reset() throws IOException, ParseException {
        fc.getCtrlDocument().reset();
        fc.getCtrlBoolExpression().reset();

    }
}
