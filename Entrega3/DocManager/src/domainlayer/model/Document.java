package domainlayer.model;

import java.io.IOException;
import java.lang.String;
import java.util.TreeMap;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import utils.exceptions.boolexpression.InvalidLiteralException;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;
import utils.exceptions.document.DocumentCampNullException;

/**
 * Class that represents a Document
 */
public class Document {
    /**
     * Format of the Document
     */
    private String format;
    /**
     * Title of the Document
     */
    private String titol;
    /**
     * Autor of the Document
     */
    private String autor;
    /**
     * Content of the Document
     */
    private String cont;
    /**
     * Information about the content of the Document
     */
    private RepVec repvec;

    /**
     * Creates a Document with format f, title t, author a and RepVec rv
     * @param f Format of the Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @param rv Information about the content of the Document (consult RepVec class)
     * @throws DocumentCampNullException
     */
    public Document(String f, String t, String a, RepVec rv) throws DocumentCampNullException{
        if ("".equals(a)) throw new DocumentCampNullException("El camp \"autor\" no pot ser null");
        if ("".equals(t)) throw new DocumentCampNullException("Els camp \"títol\" no pot ser null");
        if ("".equals(f)) throw new DocumentCampNullException("El camp \"format\" no pot ser null");
        format = f;
        titol = t;
        autor = a;
        repvec = rv;
    }

    /**
     * Creates a Document with format f, title t, author a and content c
     * @param f Format of the Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @param c Content of the Document
     * @throws DocumentCampNullException
     */
    public Document(String f, String t, String a, String c) throws DocumentCampNullException{
        if ("".equals(a)) throw new DocumentCampNullException("El camp \"autor\" no pot ser null");
        if ("".equals(t)) throw new DocumentCampNullException("El camp \"títol\" no pot ser null");
        if ("".equals(f)) throw new DocumentCampNullException("El camp \"format\" no pot ser null");
        format = f;
        titol = t;
        autor = a;
        cont = c;
        repvec = new RepVec(t,c);
    }

    /**
     * Creates a Document with format f, title t, author a, content c and RepVec rv
     * @param f Format of the Document
     * @param t Title of the Document
     * @param a Author of the Document
     * @param c Content of the Document
     * @param rv Information about the content of the Document (consult RepVec class)
     * @throws DocumentCampNullException
     */
    public Document(String f, String t, String a, String c, RepVec rv) throws DocumentCampNullException{
        if ("".equals(a)) throw new DocumentCampNullException("El camp \"autor\" no pot ser null");
        if ("".equals(t)) throw new DocumentCampNullException("El camp \"títol\" no pot ser null");
        if ("".equals(f)) throw new DocumentCampNullException("El camp \"format\" no pot ser null");
        format = f;
        titol = t;
        autor = a;
        cont = c;
        repvec = rv;
    }

    /**
     * This function modifies the content of the Document
     * @throws DocumentCampNullException
     */
    public void mod_doc(String c) throws DocumentCampNullException{

        if ("".equals(c)) throw new DocumentCampNullException();
        repvec = new RepVec(titol,c);
        cont = c;

    }
    
    /**
     * This function returns the content of the Document
     */
    public String get_contingut() {
        return cont;
    }

    // The use of this function is controlled by us
    /**
     * This function sets c as the content of the Document
     * @param c Content of the Document
     */
    public void set_contingut(String c) {
        cont = c;
    }

    /**
     * This function returns the title of the Document
     * @return The title of the Document
     */
    public String get_titol () {
        return titol;
    }

    /**
     * This function returns the author of the Document
     * @return The author of the Document
     */
    public String get_autor () {
        return autor;
    }

    /**
     * This function returns the format of the Document
     * @return The format of the Document
     */
    public String get_format() {
        return format;
    }

    /**
     * This function returns the repvec of the Document
     * @return The repvec of the Document
     */
    public RepVec get_repvec() {
        return repvec;
    }

    /**
     * This function returns the list of words of the Document
     * @return The list of words of the Document
     */
    public TreeMap<String, ArrayList<Integer>> get_llista_paraules () {
        return repvec.get_llista_paraules();
    }   

    /**
     * This function returns the list of metrics of the Document
     * @return The list of metrics of the Document
     */
    public TreeMap<String, Double> get_llista_metriques () {
        return repvec.get_llista_metriques();
    }

    /**
     * This function calculates the metric 
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public void calcular_metrica() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException{
        repvec.calcular_metrica();
    }

    
    
    

    
    /// For Boolean control:
    /**
     * Pre: The literal query is well formed
     * @param query Literal query (Set or Sequence) to evaluate
     * @return Returns the result of evaluating the literal query on the Document
     * @throws InvalidLiteralException
     */
    public boolean evaluate_bool(String query) throws InvalidLiteralException {
        //System.out.println("QUERY IS: " + query);
        int cursor = 0;
        boolean negated = (Character.valueOf(query.charAt(cursor)).equals('!'));

        if (negated) cursor++;

        boolean isSet = (Character.valueOf(query.charAt(cursor)).equals('{'));
        cursor++;
        if (!isSet && !(Character.valueOf(query.charAt(cursor-1)).equals('\"'))) throw new InvalidLiteralException();

        String myQuery = query.substring(cursor, query.length()-1);
        String[] subquery = myQuery.split(" ");
        
        
        boolean result = true; // Initially true
        if (isSet) {
            // Find set of words, separated by spaces in query
            for(String a : subquery) {
                if( !repvec.is_word_in(a) ) result=false;
            }
        }
        else {
            result = repvec.is_sequence_in(subquery, (titol.split("[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+")).length);
        }
        return negated^result;
    }

    /**
     * This function prints the list of words of the Document
     */
    public void imprimir_llista_paraules() {
        repvec.imprimir_llista_paraules();
    }

    /**
     * This function compares two Documents and returns true if they are the same Document
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(this.getClass() != obj.getClass()) return false; 

        return (this.autor.equals(((Document) obj).autor) &&
        this.titol.equals(((Document) obj).titol) &&
        this.cont.equals(((Document) obj).cont) &&
        this.format.equals(((Document) obj).format) &&
        this.repvec.equals(((Document) obj).repvec));
    }

    /**
     * This function returns a String with the information of the Document
     * @see java.lang.Object#toString()
     */
    @Override public String toString() {
        return String.format("Autor: %s , Títol: %s , Format: %s", autor, titol, format);
    }

}


