package datalayer;

import java.util.HashMap;
import java.util.TreeMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;

import utils.exceptions.database.*;
import utils.exceptions.document.*;

import domainlayer.datainterface.CtrlDocument;
import domainlayer.model.Document;
import domainlayer.model.BoolExpression;
import datalayer.databaseinterface.DocumentDB;
import datalayer.databaseinterface.FactoriaDB;

public class CtrlDocumentMem implements CtrlDocument{

    /**
     * Container for all the Documents in memory
     */
    private HashMap<String,HashMap<String,Document>> llistatDoc; // <Autor,<Titol,Document>>
    /**
     * List of all the words in all the Documents and the number of times they appear
     */
    public TreeMap<String, Integer> llistaAparicions;
    /**
     * Number of Documents in memory
     */
    private int num_doc; 
    /**
     * Reference to Database
     */
    private FactoriaDB factDatabase = null;

    /**
     * Default constructor
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public CtrlDocumentMem() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException{
        llistatDoc = new HashMap<String,HashMap<String,Document>>();
        llistaAparicions = new TreeMap<String, Integer>();
        factDatabase = FactoriaDB.getInstance();
        llistatDoc = factDatabase.getDocumentDB().init_database("DATA");
        num_doc = factDatabase.getDocumentDB().get_num_doc();
    }
    
    // Get Document --------------------------------------------------------------------------------------------------------------
    /**
     * @see domainlayer.datainterface.CtrlDocument#getDoc(java.lang.String, java.lang.String)
     */
    public Document getDoc(String t, String a) throws AutorTitolNoExisteixException, AutorNoExisteixException{
        if (llistatDoc.containsKey(a)) {
            if (llistatDoc.get(a).get(t) != null) return llistatDoc.get(a).get(t);
            else throw new AutorTitolNoExisteixException();
        }
        else throw new AutorNoExisteixException();
    }
    // ---------------------------------------------------------------------------------------------------------------------------

    // Alta Document--------------------------------------------------------------------------------------------------------------
    // must:
    //      - Store de Doc in the array 
    //      - Store it in persistance
    //      - Return it
    /**
     * @see domainlayer.datainterface.CtrlDocument#alta_doc(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Document alta_doc(String f, String t, String a, String c) throws AutorTitolJaExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException {
        Document d = new Document(f, t, a, c);
        if (llistatDoc.containsKey(a)) {
            HashMap<String,Document> ta = llistatDoc.get(a);
            if (ta.containsKey(t)) throw new AutorTitolJaExisteixException();
            else {
                ta.put(t,d);
                llistatDoc.put(a,ta);        
            }
        }
        else {
            HashMap<String,Document> ta = new HashMap<String,Document>();
            ta.put(t,d);
            llistatDoc.put(a,ta);
        }
        ++num_doc;

        // Call to database element to handle persistance
        DocumentDB database = factDatabase.getDocumentDB();
        database.insert(d);
        
        return d;
    }
    // ----------------------------------------------------------------------------------------------------------------------------

    /**
     * @see domainlayer.datainterface.CtrlDocument#mod_doc(java.lang.String, java.lang.String, java.lang.String)
     */
    public Document mod_doc(String t, String a, String c) throws AutorTitolNoExisteixException, AutorNoExisteixException, DocumentCampNullException, IOException, DatabaseInconsistentException{
        Document d = null;
        d = getDoc(t,a);
        d.mod_doc(c);
        HashMap<String,Document> ta = llistatDoc.get(a);
        ta.put(t,d); 
        llistatDoc.put(a,ta);  

        // Call to database element to handle persistance
        DocumentDB database = factDatabase.getDocumentDB();
        database.update(d,c);   
        
        return d;
    }
    
    /**
     * @see domainlayer.datainterface.CtrlDocument#baixa_doc(java.lang.String, java.lang.String)
     */
    public void baixa_doc(String t, String a) throws AutorTitolNoExisteixException, AutorNoExisteixException, IOException, DatabaseInconsistentException {
        Document d = getDoc(t, a); // Lença excepció si no existeix
        HashMap<String,Document> aux = llistatDoc.get(a);
        aux.remove(t);
        llistatDoc.put(a, aux);
        if (aux.isEmpty()) llistatDoc.remove(a);
        --num_doc;
        

        // Call to database element to handle persistance
        DocumentDB database = factDatabase.getDocumentDB();
        database.delete(d);  
    }
    
    /**
     * @see domainlayer.datainterface.CtrlDocument#llistar_titols_unautor(java.lang.String)
     */
    public ArrayList<String> llistar_titols_unautor(String a) throws AutorNoExisteixException {
        if (llistatDoc.containsKey(a)) {
            HashMap<String,Document> aux = llistatDoc.get(a);
            ArrayList<String> llistaTitols = new ArrayList<String>();
            for (String key : aux.keySet()) llistaTitols.add(key);
            return llistaTitols;
        }
            throw new AutorNoExisteixException();
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#llistarPrefix(java.lang.String)
     */
    public ArrayList<String> llistarPrefix(String p) {
        ArrayList<String> llistaAutors = new ArrayList<String>();
        if (p == "") for (String key : llistatDoc.keySet()) llistaAutors.add(key);
        else for (String clau:llistatDoc.keySet()) if (clau.startsWith(p)) llistaAutors.add(clau);
        return llistaAutors;
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#mostrar_contingut(java.lang.String, java.lang.String)
     */
    public String mostrar_contingut(String t, String a) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException, ParseException{
        Document d = getDoc(t, a);
        String cont = d.get_contingut();
        if((cont == null || cont.equals("")) && !Integer.valueOf(d.get_repvec().get_num_paraules()).equals(0)) {
            // Content attribute empty but repvec indicates the Document is a non-empty document -> Has not been loaded yet
            // Call to database element to handle persistance
            DocumentDB database = factDatabase.getDocumentDB();
            cont = database.load_content(d);  

            // We add it to the map, as it has been consulted one type it is likely it is consulted twice (localitat temporal)

            d.set_contingut(cont);
            HashMap<String,Document> titol_document = llistatDoc.get(a);
            titol_document.put(d.get_titol(), d);
            llistatDoc.put(d.get_autor(),titol_document);
        }
        return cont;
    }
    
    /**
     * @see domainlayer.datainterface.CtrlDocument#get_num_documents()
     */
    public int get_num_documents() {
        return num_doc;
    }
    
    /**
     * @see domainlayer.datainterface.CtrlDocument#get_llista_aparicions()
     */
    public TreeMap<String, Integer> get_llista_aparicions (){
        return llistaAparicions;
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#llistar_documents()
     */
    public ArrayList<Document> llistar_documents () {
        ArrayList<Document> llista_documents = new ArrayList<Document>();
        for (Map.Entry<String,HashMap<String,Document>> entrada : llistatDoc.entrySet()) {
            Map<String,Document> map_interior = entrada.getValue();
            for (Map.Entry<String,Document> map_titol_document : map_interior.entrySet()) {
                llista_documents.add(map_titol_document.getValue());
            }
        }
        return llista_documents;
    }
    
    /**
     * Update appearances of a word
     * @param t
     * @param a
     */
    private void actualitzar_aparicions(String t, String a) {
        Document d = null;
        try {
            d = getDoc(t, a);
        } catch (Exception e) {
            // Safe as it is called only privatelly on the map
        }
        for (Map.Entry<String,ArrayList<Integer>> entrada : d.get_llista_paraules().entrySet()) {
            String paraula = entrada.getKey();
            if (llistaAparicions.containsKey(paraula)) {
                int ap = entrada.getValue().size();
                ap++;
                llistaAparicions.replace(paraula, ap);
            }
            else {
                llistaAparicions.put(paraula, 1);
            }
        }

    }
    
    /**
     * Compute Distance
     * @param llista_metrica1
     * @param llista_metrica2
     * @return The distance between 2 Documents using their appreances list
     */
    private double calcular_distancia_docs(TreeMap<String, Double> llista_metrica1, TreeMap<String, Double> llista_metrica2) {
        //sumatori de la multiplicacio de les metriques de les paraules de dos documents entre modul d'un * el modul de laltre
        double sum_aparicions = 0;
        double modul_doc1 = 0;
        double modul_doc2 = 0;
        
        for (Map.Entry<String,Double> entry : llista_metrica1.entrySet()) {
            String paraula = entry.getKey();
            double metrica1 = entry.getValue();
            if (llista_metrica2.containsKey(paraula)) {
                double metrica2 = llista_metrica2.get(paraula);
                sum_aparicions += metrica1 * metrica2;                  //part de dalt de la fracció (sumatori de les metriques multiplicades)
            
                modul_doc1 += metrica1 * metrica1;                      //primera part de sota de la fracció (mòdul de les metriques del document 1)
                //System.out.println(metrica2);
            }
        }
        
        for (Map.Entry<String,Double> entry : llista_metrica2.entrySet()) {
            double metrica2 = entry.getValue();
            modul_doc2 += metrica2 * metrica2;                      //segona part de sota de la fracció (mòdul de les mètriques del document 2)
        }
        
        modul_doc1 = Math.sqrt(modul_doc1);
        modul_doc2 = Math.sqrt(modul_doc2);
        
        if (sum_aparicions == 0 || modul_doc1 == 0 || modul_doc2 == 0) return 0;
        return sum_aparicions / (modul_doc1 * modul_doc2);
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#ordenar_per_semblanca(java.lang.String, java.lang.String, java.lang.Integer)
     */
    public ArrayList<Document> ordenar_per_semblanca (String t, String a, Integer k) throws AutorNoExisteixException, AutorTitolNoExisteixException, FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException {              //donat un document el compara amb tots els altres per mirar amb quin és més semblant
        int aux = num_doc-1;
        if (k < num_doc-1) aux = k;
        ArrayList<Document> documents_ordenats = new ArrayList<Document>();
        
        //contar paraules i actualitzar_aparicions
        for (Map.Entry<String,HashMap<String,Document>> entrada : llistatDoc.entrySet()) {
            Map<String,Document> map_interior = entrada.getValue();
            for (Map.Entry<String,Document> map_titol : map_interior.entrySet()) {
                actualitzar_aparicions(map_titol.getKey(), entrada.getKey());
            }
        }
        Document d = getDoc(t, a);
        d.calcular_metrica();
        HashMap<Document,Double> map_distancies = new HashMap<Document,Double>();

        for (Map.Entry<String,HashMap<String,Document>> entrada : llistatDoc.entrySet()) {      //calcular la distància i guardarla ordenada al vector distancia
            Map<String,Document> map_interior = entrada.getValue();
            for (Map.Entry<String,Document> map_titol : map_interior.entrySet()) {
                if (!(entrada.getKey().equals(a) && map_titol.getKey().equals(t))) {
                    Document d2 = map_titol.getValue();
                    d2.calcular_metrica();            
                    map_distancies.put(map_titol.getValue(), calcular_distancia_docs(d2.get_llista_metriques(), d.get_llista_metriques()));
                    //System.out.println(map_titol.getKey() +entrada.getKey()+ map_distancies.get(map_titol.getValue()));
                }
            }
        }


        List<Entry<Document,Double>> llista_distancies = new ArrayList<>(map_distancies.entrySet());    //converteix el map en una llista i la ordena per les distancies
        llista_distancies.sort(Entry.comparingByValue());
        Collections.reverse(llista_distancies);

        int j = 0;
        for (Map.Entry<Document,Double> entrada : llista_distancies) {
            documents_ordenats.add(entrada.getKey());
            ++j;
            if (j == aux) return documents_ordenats;
        }
        return documents_ordenats;
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#bool_search(domainlayer.model.BoolExpression)
     */
    public ArrayList<Document> bool_search(BoolExpression b) {
        ArrayList<Document> res = new ArrayList<Document>();

        for (Map.Entry<String,HashMap<String,Document>> entrada : llistatDoc.entrySet()) {
            Map<String,Document> map_interior = entrada.getValue();
            for (Map.Entry<String,Document> map_titol_document : map_interior.entrySet()) {
                boolean bool = false;
                try {
                    bool = b.evaluate(map_titol_document.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(bool) res.add(map_titol_document.getValue());
            }
        }

        return res;
    }

    /**
     * @see domainlayer.datainterface.CtrlDocument#reset()
     */
    public void reset() throws IOException {
        llistatDoc = new HashMap<String,HashMap<String,Document>>();
        llistaAparicions = new TreeMap<String, Integer>();
        num_doc = 0;

        DocumentDB database = factDatabase.getDocumentDB();
        database.reset();
    }

    
}
