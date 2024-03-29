package domainlayer.datainterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;
import utils.exceptions.document.*;

import domainlayer.model.Document;
import domainlayer.model.BoolExpression;

/**
 * The interface of the controller of the Document
 */
public interface CtrlDocument {
    
    /**
     * Get Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @return If it exists, the desired Document
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     */
    public Document getDoc(String t, String a) throws AutorNoExisteixException, AutorTitolNoExisteixException;

    /**
     * New Document
     * @param f Format of the Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @param c Content of the Document
     * @return The new Document created
     * @throws AutorTitolJaExisteixException
     * @throws DocumentCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public Document alta_doc(String f, String t, String a, String c) throws AutorTitolJaExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException;
    
    /**
     * Modify Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @param c Content of the Document
     * @return The result of modifying the Document
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws DocumentCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public Document mod_doc(String t, String a, String c) throws AutorNoExisteixException, AutorTitolNoExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException;
    
    /**
     * Delete document
     * @param t Title of the Document
     * @param a Author of the Document
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public void baixa_doc(String t, String a) throws AutorNoExisteixException, AutorTitolNoExisteixException, IOException, DatabaseInconsistentException;
    
    /**
     * List Title for Author
     * @param a Author
     * @return Lists all the Titles of an existing Author
     * @throws AutorNoExisteixException
     */
    public ArrayList<String> llistar_titols_unautor(String a) throws AutorNoExisteixException;

    /**
     * List by prefix
     * @param p Prefix
     * @return List all the Authors that start by the prefix given
     */
    public ArrayList<String> llistarPrefix(String p);

    /**
     * Returns de Content of a Document in memory, loading it from database if necessary 
     * @param t Title of the Document
     * @param a Author of the Document
     * @return The content of the specified Document
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public String mostrar_contingut(String t, String a) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException, ParseException;
    
    /**
     * Number of Documents
     * @return The number of existing Docuements
     */
    public int get_num_documents();
    
    /**
     * Instances of words
     * @return The full list of appearances of all words in Documents, with the ammount of times each is found
     */
    public TreeMap<String, Integer> get_llista_aparicions ();

    /**
     * Get All Documents
     * @return The list of all Documents in the software
     */
    public ArrayList<Document> llistar_documents ();
    
    /**
     * Get Documents by similarity
     * @param t Title of the Document
     * @param a Author of the Document
     * @param k Number of Documents to return
     * @return Returns a list of k Documents (or num_docs-1 if there are not enough Documents stored) which are most similar to the Document given
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public ArrayList<Document> ordenar_per_semblanca (String t, String a, Integer k) throws AutorNoExisteixException, AutorTitolNoExisteixException, FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException;

    /**
     * Get Documents from a boolean expression
     * @param b BoolExpression to search for
     * @return List of Documents that fulfill a given BoolExpression
     */
    public ArrayList<Document> bool_search(BoolExpression b);

    public void reset() throws IOException;
    
}
