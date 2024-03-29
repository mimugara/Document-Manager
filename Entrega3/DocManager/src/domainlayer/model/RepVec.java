package domainlayer.model;

import java.io.IOException;
import java.lang.String;
import java.util.*;

import org.json.simple.parser.ParseException;

import domainlayer.datainterface.*;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;


/**
 * Class that contains all the information from the document needed for calculating the ressemlance between documents
 */
public class RepVec {
    /**
     * Number of words in a document
     */
    private int num_paraules;
    /**
     * List of the words in the document
     */
    private TreeMap<String, ArrayList<Integer>> llista_paraules;
    /**
     * List of the metrics for every word
     */
    private TreeMap<String, Double> llista_metriques;
    /**
     * Set with all the stop words
     */
    private static SortedSet<String> stop_words;
    /**
     * Bool with true if the set of stop words has been initialitzed or false if hasn't
     */
    private static boolean stop_words_initilized = false;


    /**
     * Creates a RepVec with parameters t and c
     * @param t Title of the document
     * @param c Author of the document
     */
    public RepVec(String t, String c) {
        if(!stop_words_initilized) {inicialitzar_stop_words(); stop_words_initilized=true;}
        contar_paraules(t,c);
    }

    /**
     * Creates a RepVec with lp as llista_paraules ant n as num_paraules
     * @param lp List of words in the document where the first value is the word and the second it's an array of the positcion of the words
     * @param n Number of words in the document
     */
    public RepVec(TreeMap<String, ArrayList<Integer>> lp, int n) {
        if(!stop_words_initilized) {inicialitzar_stop_words(); stop_words_initilized=true;}
        llista_paraules = lp;
        num_paraules = n;
    }


    /**
     * Returns llista_paraules
     * @return Returns llista_paraules
     */
    public TreeMap<String, ArrayList<Integer>> get_llista_paraules () {
        return llista_paraules;
    }   

    /**
     * Returns llista_metriques
     * @return Returns llista_metriques
     */
    public TreeMap<String, Double> get_llista_metriques () {
        return llista_metriques;
    }

    /**
     * Returns num_paraules
     * @return Returns num_paraules
     */
    public int get_num_paraules() {
        return num_paraules;
    }

    /**
     * Check if the word it's an stop word or not.
     * @param paraula Word to check
     * @return Returns true if paraula its a stop word, returns false otherwise
     */
    public boolean comprovar_si_stop_word (String paraula) {
        return stop_words.contains(paraula);
    }

    /**
     * Check if the word it's in llista_paraules
     * @param word Word to check
     * @return Returns true if word its in llista_paraules
     */
    public boolean is_word_in(String word) {
        if(word.equals("")) return true;
        return (!word.equals("") && llista_paraules.get(word.toLowerCase()) != null && llista_paraules.get(word.toLowerCase()).size() != 0);
    }


    /**
     * Check if a literal sequence is in the Document 
     * @param subquery Array of words to check
     * @param title_length Lenght of the title of the document
     * @return returns whether the sequence is in or not
     */
    public boolean is_sequence_in(String[] subquery, int title_length) {
        // Find sequence of words
        boolean result = false; // Better to set it initially false
        if(subquery.length == 0 || (subquery.length == 1 && subquery[0].equals(""))) return true;

        int[] pointers = new int[subquery.length]; // array of indexes initialized to 0 (ensured by java documentation)

        ArrayList<ArrayList<Integer>> aparicions = new ArrayList<ArrayList<Integer>>();
        for(int k = 0; k < subquery.length; ++k) {
            ArrayList<Integer> aparicions_per_paraula_k = llista_paraules.get(subquery[k].toLowerCase());
            if(aparicions_per_paraula_k == null || aparicions_per_paraula_k.size() == 0) {
                // stops when one of the words has exceeded the number of appearances
                return false; // If that word doesn't appear, no way there is a seq. with that word
            }
            if(subquery.length == 1) return true; // It is the equivalent of a sequence of one word, if we reached this point it means it exists
            aparicions.add(aparicions_per_paraula_k); // array[0...subquery.length-1][0...num_aparicions-1]
        }
        boolean title_skipped = false;
    

        while(pointers[0] < aparicions.get(0).size() && !result) {
            // While there is still instances of the first word to see
            int app_of_prev_word = aparicions.get(0).get(pointers[0]);
            int i = 1; // Begin checking next word
            if(!title_skipped) title_skipped = app_of_prev_word >= title_length; // Appears after the title

            while(i < subquery.length && title_skipped) { 
                int app_of_next_word = aparicions.get(i).get(pointers[i]);

                while(pointers[i] < aparicions.get(i).size() && app_of_next_word <= app_of_prev_word) {
                    app_of_next_word = aparicions.get(i).get(pointers[i]);
                    pointers[i]++;
                }
                if(pointers[i] == aparicions.get(i).size() && (app_of_next_word <= app_of_prev_word || app_of_next_word - app_of_prev_word > 1)) {
                    //System.out.println("One of the words indexes has been exceeded: " + Integer.toString(i));
                    return false; // stops when one of the words has exceeded the number of appearances
                }
                // If this line is reached, means we have found an instance of the next word posterior to the previous word
                if(app_of_next_word-app_of_prev_word == 1) { // If they are consecutive, jump no next word
                    i++;
                    if(i == subquery.length) result = true; // That means we found the sequence!!!! :)
                    app_of_prev_word = app_of_next_word; // get ready for next iteration
                }
                else break; // We need to increment the first index, no matter how far we got
            }
            pointers[0]++;            
        }

        //if(pointers[0] == aparicions.get(0).size()) System.out.println("First word exceeded its appearances");


        return result;
    }

    /**
     * Fills llista_paraula with the words from titol and cont
     * @param titol Title of the document
     * @param cont Content of the document
     */
    private void contar_paraules(String titol, String cont) {
        //agafar cont i anar paraula per paraula buscantla a llistatParaules, si no hi és afegirla i posar 1 al contador, si hi és sumar 1 al contador
        //llegir la primera paraula
        //aconseguir numero paraules

        llista_paraules = new TreeMap<String, ArrayList<Integer>>();
        num_paraules = 0;
        int i = 0;

        if (titol != null) {                                    //agafar les paraules del títol
            String[] paraules = titol.split("[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+");         //separarles
            num_paraules += paraules.length;

            for (String paraula : paraules) {
                paraula = paraula.toLowerCase();                //pasarho tot a minuscules
                if (llista_paraules.containsKey(paraula)) {
                    ArrayList<Integer> ap = llista_paraules.get(paraula);
                    ap.add(i);
                    llista_paraules.replace(paraula, ap);
                }
                else { 
                    ArrayList<Integer> ap = new ArrayList<Integer>();
                    ap.add(i);
                    llista_paraules.put(paraula, ap);
                }
                ++i;
            }
        }
        
        if (cont != null) {                                     //agafa les paraules del contingut
            String[] paraules = cont.split("[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+");          //separa en tot el q no és caràcter de paraula 
            num_paraules += paraules.length;

            for (String paraula : paraules) {
                paraula = paraula.toLowerCase();          //pasarho tot amb minuscules
                if (llista_paraules.containsKey(paraula)) {
                    ArrayList<Integer> ap = llista_paraules.get(paraula);
                    ap.add(i);
                    llista_paraules.replace(paraula, ap);
                } 
                else {
                    ArrayList<Integer> ap = new ArrayList<Integer>();
                    ap.add(i);
                    llista_paraules.put(paraula, ap);
                }
                ++i;
            }
        }
    }

    /**
     * Calculates the metric for every word in llista_paraules and stores the values in llista_metriques
     * @throws FolderNotFoundException
     * @throws DatabaseInconsistentException
     * @throws IOException
     * @throws ParseException
     */
    public void calcular_metrica() throws FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException{
        //calcular la mètrica de cada paraula multiplicant el nombre de vegades que apareix aquella paraula en el document entre el nombre de paraules del document per el 
        //logaritme del número de documents entre el numero de documents en els que surt la paraula
        llista_metriques = new TreeMap<String, Double>();
        FactoriaCtrl fc = FactoriaCtrl.getInstance();
        CtrlDocument docs = fc.getCtrlDocument();
        TreeMap<String, Integer> llista_aparicions = docs.get_llista_aparicions();
        double num_documents = docs.get_num_documents();
        
        for (Map.Entry<String,ArrayList<Integer>> entry : llista_paraules.entrySet()) {
            String paraula = entry.getKey();
            if (!comprovar_si_stop_word(paraula)) {
                double num_aparicions = entry.getValue().size();
                double tf = num_aparicions / num_paraules;
            
                int num_documents_on_surt = llista_aparicions.get(paraula);
                double idf = Math.log10(num_documents/(num_documents_on_surt));
                

                double tfidf = tf * idf;

                llista_metriques.put(paraula, tfidf);
            }
        }       
    }

    /**
     * This function checks if obj is a RepVec and has the same number of words and the same words as this
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        return (this.num_paraules == ((RepVec) obj).num_paraules &&
        this.llista_paraules.equals(((RepVec) obj).llista_paraules));
    }


    /**
     * Initializes the stop_words set with the stop words
     */
    private static void inicialitzar_stop_words () {
        stop_words = new TreeSet<String>();
        stop_words.add("últim");            //CATALÀ
        stop_words.add("última");
        stop_words.add("últimes");
        stop_words.add("últims");
        stop_words.add("a");
        stop_words.add("abans");
        stop_words.add("això");
        stop_words.add("al");
        stop_words.add("algun");
        stop_words.add("alguna");
        stop_words.add("algunes");
        stop_words.add("alguns");
        stop_words.add("allà");
        stop_words.add("allí");
        stop_words.add("allò");
        stop_words.add("als");
        stop_words.add("altra");
        stop_words.add("altre");
        stop_words.add("altres");
        stop_words.add("amb");
        stop_words.add("aprop");
        stop_words.add("aquí");
        stop_words.add("aquell");
        stop_words.add("aquella");
        stop_words.add("aquelles");
        stop_words.add("aquells");
        stop_words.add("aquest");
        stop_words.add("aquesta");
        stop_words.add("aquestes");
        stop_words.add("aquests");
        stop_words.add("cada");
        stop_words.add("catorze");
        stop_words.add("cent");
        stop_words.add("cert");
        stop_words.add("certa");
        stop_words.add("certes");
        stop_words.add("certs");
        stop_words.add("cinc");
        stop_words.add("com");
        stop_words.add("cosa");
        stop_words.add("d'");
        stop_words.add("darrer");
        stop_words.add("darrera");
        stop_words.add("darreres");
        stop_words.add("darrers");
        stop_words.add("davant");
        stop_words.add("de");
        stop_words.add("del");
        stop_words.add("dels");
        stop_words.add("després");
        stop_words.add("deu");
        stop_words.add("dinou");
        stop_words.add("disset");
        stop_words.add("divuit");
        stop_words.add("dos");
        stop_words.add("dotze");
        stop_words.add("durant");
        stop_words.add("el");
        stop_words.add("ell");
        stop_words.add("ella");
        stop_words.add("elles");
        stop_words.add("ells");
        stop_words.add("els");
        stop_words.add("en");
        stop_words.add("encara");
        stop_words.add("et");
        stop_words.add("extra");
        stop_words.add("fins");
        stop_words.add("hi");
        stop_words.add("i");
        stop_words.add("jo");
        stop_words.add("l'");
        stop_words.add("la");
        stop_words.add("les");
        stop_words.add("li");
        stop_words.add("llur");
        stop_words.add("lo");
        stop_words.add("los");
        stop_words.add("més");
        stop_words.add("m'");
        stop_words.add("ma");
        stop_words.add("massa");
        stop_words.add("mateix");
        stop_words.add("mateixa");
        stop_words.add("mateixes");
        stop_words.add("mateixos");
        stop_words.add("mes");
        stop_words.add("meu");
        stop_words.add("meva");
        stop_words.add("mig");
        stop_words.add("molt");
        stop_words.add("molta");
        stop_words.add("moltes");
        stop_words.add("molts");
        stop_words.add("mon");
        stop_words.add("mons");
        stop_words.add("n'");
        stop_words.add("na");
        stop_words.add("ni");
        stop_words.add("no");
        stop_words.add("nosaltres");
        stop_words.add("nostra");
        stop_words.add("nostre");
        stop_words.add("nou");
        stop_words.add("ns");
        stop_words.add("o");
        stop_words.add("on");
        stop_words.add("onze");
        stop_words.add("pel");
        stop_words.add("per");
        stop_words.add("però");
        stop_words.add("perquè");
        stop_words.add("perque");
        stop_words.add("poc");
        stop_words.add("poca");
        stop_words.add("pocs");
        stop_words.add("poques");
        stop_words.add("primer");
        stop_words.add("primera");
        stop_words.add("primeres");
        stop_words.add("primers");
        stop_words.add("prop");
        stop_words.add("què");
        stop_words.add("qual");
        stop_words.add("quals");
        stop_words.add("qualsevol");
        stop_words.add("qualssevol");
        stop_words.add("quan");
        stop_words.add("quant");
        stop_words.add("quanta");
        stop_words.add("quantes");
        stop_words.add("quants");
        stop_words.add("quatre");
        stop_words.add("que ");
        stop_words.add("qui");
        stop_words.add("quin");
        stop_words.add("quina");
        stop_words.add("quines");
        stop_words.add("quins");
        stop_words.add("quinze");
        stop_words.add("res");
        stop_words.add("s'");
        stop_words.add("sa");
        stop_words.add("segon");
        stop_words.add("segona");
        stop_words.add("segones");
        stop_words.add("segons");
        stop_words.add("sense");
        stop_words.add("ses");
        stop_words.add("set");
        stop_words.add("setze");
        stop_words.add("seu");
        stop_words.add("seus");
        stop_words.add("seva");
        stop_words.add("seves");
        stop_words.add("sino");
        stop_words.add("sis");
        stop_words.add("sobre");
        stop_words.add("son");
        stop_words.add("sons");
        stop_words.add("sota");
        stop_words.add("t'");
        stop_words.add("ta");
        stop_words.add("tal");
        stop_words.add("tals");
        stop_words.add("tan");
        stop_words.add("tant");
        stop_words.add("tanta");
        stop_words.add("tantes");
        stop_words.add("tants");
        stop_words.add("tes");
        stop_words.add("teu");
        stop_words.add("teus");
        stop_words.add("teva");
        stop_words.add("teves");
        stop_words.add("ton");
        stop_words.add("tons");
        stop_words.add("tot");
        stop_words.add("tota");
        stop_words.add("totes");
        stop_words.add("tots");
        stop_words.add("tres");
        stop_words.add("tretze");
        stop_words.add("tu");
        stop_words.add("un");
        stop_words.add("una");
        stop_words.add("unes");
        stop_words.add("uns");
        stop_words.add("vint");
        stop_words.add("vos");
        stop_words.add("vosaltres");
        stop_words.add("vosté");
        stop_words.add("vostés");
        stop_words.add("vostra");
        stop_words.add("vostre");
        stop_words.add("vuit");

        stop_words.add("a");            //CASTELLÀ
        stop_words.add("actualmente");
        stop_words.add("adelante");
        stop_words.add("además");
        stop_words.add("afirmó");
        stop_words.add("agregó");
        stop_words.add("ahora");
        stop_words.add("ahí");
        stop_words.add("al");
        stop_words.add("algo");
        stop_words.add("alguna");
        stop_words.add("algunas");
        stop_words.add("alguno");
        stop_words.add("algunos");
        stop_words.add("algún");
        stop_words.add("alrededor");
        stop_words.add("ambos");
        stop_words.add("ante");
        stop_words.add("anterior");
        stop_words.add("antes");
        stop_words.add("apenas");
        stop_words.add("aproximadamente");
        stop_words.add("aquí");
        stop_words.add("aseguró");
        stop_words.add("así");
        stop_words.add("aunque");
        stop_words.add("ayer");
        stop_words.add("añadió");
        stop_words.add("aún");
        stop_words.add("bajo");
        stop_words.add("bien");
        stop_words.add("buen");
        stop_words.add("buena");
        stop_words.add("buenas");
        stop_words.add("bueno");
        stop_words.add("buenos");
        stop_words.add("cada");
        stop_words.add("casi");
        stop_words.add("cerca");
        stop_words.add("cierto");
        stop_words.add("cinco");
        stop_words.add("comentó");
        stop_words.add("como");
        stop_words.add("con");
        stop_words.add("conocer");
        stop_words.add("considera");
        stop_words.add("consideró");
        stop_words.add("contra");
        stop_words.add("cosas");
        stop_words.add("creo");
        stop_words.add("cual");
        stop_words.add("cuales");
        stop_words.add("cualquier");
        stop_words.add("cuando");
        stop_words.add("cuanto");
        stop_words.add("cuatro");
        stop_words.add("cuenta");
        stop_words.add("cómo");
        stop_words.add("da");
        stop_words.add("dado");
        stop_words.add("dan");
        stop_words.add("dar");
        stop_words.add("de");
        stop_words.add("debe");
        stop_words.add("deben");
        stop_words.add("debido");
        stop_words.add("decir");
        stop_words.add("dejó");
        stop_words.add("del");
        stop_words.add("demás");
        stop_words.add("dentro");
        stop_words.add("desde");
        stop_words.add("después");
        stop_words.add("dice");
        stop_words.add("dicen");
        stop_words.add("dicho");
        stop_words.add("dieron");
        stop_words.add("diferente");
        stop_words.add("diferentes");
        stop_words.add("dijeron");
        stop_words.add("dijo");
        stop_words.add("dio");
        stop_words.add("donde");
        stop_words.add("dos");
        stop_words.add("durante");
        stop_words.add("e");
        stop_words.add("ejemplo");
        stop_words.add("el");
        stop_words.add("ella");
        stop_words.add("ellas");
        stop_words.add("ello");
        stop_words.add("ellos");
        stop_words.add("embargo");
        stop_words.add("en");
        stop_words.add("encuentra");
        stop_words.add("entonces");
        stop_words.add("entre");
        stop_words.add("era");
        stop_words.add("eran");
        stop_words.add("es");
        stop_words.add("esa");
        stop_words.add("esas");
        stop_words.add("ese");
        stop_words.add("eso");
        stop_words.add("esos");
        stop_words.add("esta");
        stop_words.add("estaba");
        stop_words.add("estaban");
        stop_words.add("estamos");
        stop_words.add("estar");
        stop_words.add("estará");
        stop_words.add("estas");
        stop_words.add("este");
        stop_words.add("esto");
        stop_words.add("estos");
        stop_words.add("estoy");
        stop_words.add("estuvo");
        stop_words.add("está");
        stop_words.add("están");
        stop_words.add("ex");
        stop_words.add("existe");
        stop_words.add("existen");
        stop_words.add("explicó");
        stop_words.add("expresó");
        stop_words.add("fin");
        stop_words.add("fue");
        stop_words.add("fuera");
        stop_words.add("fueron");
        stop_words.add("gran");
        stop_words.add("grandes");
        stop_words.add("ha");
        stop_words.add("haber");
        stop_words.add("habrá");
        stop_words.add("había");
        stop_words.add("habían");
        stop_words.add("hace");
        stop_words.add("hacen");
        stop_words.add("hacer");
        stop_words.add("hacerlo");
        stop_words.add("hacia");
        stop_words.add("haciendo");
        stop_words.add("han");
        stop_words.add("hasta");
        stop_words.add("hay");
        stop_words.add("haya");
        stop_words.add("he");
        stop_words.add("hecho");
        stop_words.add("hemos");
        stop_words.add("hicieron");
        stop_words.add("hizo");
        stop_words.add("hoy");
        stop_words.add("hubo");
        stop_words.add("igual");
        stop_words.add("incluso");
        stop_words.add("indicó");
        stop_words.add("informó");
        stop_words.add("junto");
        stop_words.add("la");
        stop_words.add("lado");
        stop_words.add("las");
        stop_words.add("le");
        stop_words.add("les");
        stop_words.add("llegó");
        stop_words.add("lleva");
        stop_words.add("llevar");
        stop_words.add("lo");
        stop_words.add("los");
        stop_words.add("luego");
        stop_words.add("lugar");
        stop_words.add("manera");
        stop_words.add("manifestó");
        stop_words.add("mayor");
        stop_words.add("me");
        stop_words.add("mediante");
        stop_words.add("mejor");
        stop_words.add("mencionó");
        stop_words.add("menos");
        stop_words.add("mi");
        stop_words.add("mientras");
        stop_words.add("misma");
        stop_words.add("mismas");
        stop_words.add("mismo");
        stop_words.add("mismos");
        stop_words.add("momento");
        stop_words.add("mucha");
        stop_words.add("muchas");
        stop_words.add("mucho");
        stop_words.add("muchos");
        stop_words.add("muy");
        stop_words.add("más");
        stop_words.add("nada");
        stop_words.add("nadie");
        stop_words.add("ni");
        stop_words.add("ninguna");
        stop_words.add("ningunas");
        stop_words.add("ninguno");
        stop_words.add("ningunos");
        stop_words.add("ningún");
        stop_words.add("no");
        stop_words.add("nos");
        stop_words.add("nosotras");
        stop_words.add("nosotros");
        stop_words.add("nuestra");
        stop_words.add("nuestras");
        stop_words.add("nuestro");
        stop_words.add("nuestros");
        stop_words.add("nueva");
        stop_words.add("nuevas");
        stop_words.add("nuevo");
        stop_words.add("nuevos");
        stop_words.add("nunca");
        stop_words.add("o");
        stop_words.add("ocho");
        stop_words.add("otra");
        stop_words.add("otras");
        stop_words.add("otro");
        stop_words.add("otros");
        stop_words.add("para");
        stop_words.add("parece");
        stop_words.add("parte");
        stop_words.add("partir");
        stop_words.add("pasada");
        stop_words.add("pasado");
        stop_words.add("pero");
        stop_words.add("pesar");
        stop_words.add("poca");
        stop_words.add("pocas");
        stop_words.add("poco");
        stop_words.add("pocos");
        stop_words.add("podemos");
        stop_words.add("podrá");
        stop_words.add("podrán");
        stop_words.add("podría");
        stop_words.add("podrían");
        stop_words.add("poner");
        stop_words.add("por");
        stop_words.add("porque");
        stop_words.add("posible");
        stop_words.add("primer");
        stop_words.add("primera");
        stop_words.add("primero");
        stop_words.add("primeros");
        stop_words.add("principalmente");
        stop_words.add("propia");
        stop_words.add("propias");
        stop_words.add("propio");
        stop_words.add("propios");
        stop_words.add("próximo");
        stop_words.add("próximos");
        stop_words.add("pudo");
        stop_words.add("pueda");
        stop_words.add("puede");
        stop_words.add("pueden");
        stop_words.add("pues");
        stop_words.add("que");
        stop_words.add("quedó");
        stop_words.add("queremos");
        stop_words.add("quien");
        stop_words.add("quienes");
        stop_words.add("quiere");
        stop_words.add("quién");
        stop_words.add("qué");
        stop_words.add("realizado");
        stop_words.add("realizar");
        stop_words.add("realizó");
        stop_words.add("respecto");
        stop_words.add("se");
        stop_words.add("sea");
        stop_words.add("sean");
        stop_words.add("segunda");
        stop_words.add("segundo");
        stop_words.add("según");
        stop_words.add("seis");
        stop_words.add("ser");
        stop_words.add("será");
        stop_words.add("serán");
        stop_words.add("sería");
        stop_words.add("señaló");
        stop_words.add("si");
        stop_words.add("sido");
        stop_words.add("siempre");
        stop_words.add("siendo");
        stop_words.add("siete");
        stop_words.add("sigue");
        stop_words.add("siguiente");
        stop_words.add("sin");
        stop_words.add("sino");
        stop_words.add("sobre");
        stop_words.add("sola");
        stop_words.add("solamente");
        stop_words.add("solas");
        stop_words.add("solo");
        stop_words.add("solos");
        stop_words.add("son");
        stop_words.add("su");
        stop_words.add("sus");
        stop_words.add("sí");
        stop_words.add("sólo");
        stop_words.add("tal");
        stop_words.add("también");
        stop_words.add("tampoco");
        stop_words.add("tan");
        stop_words.add("tanto");
        stop_words.add("tendrá");
        stop_words.add("tendrán");
        stop_words.add("tenemos");
        stop_words.add("tener");
        stop_words.add("tenga");
        stop_words.add("tengo");
        stop_words.add("tenido");
        stop_words.add("tenía");
        stop_words.add("tercera");
        stop_words.add("tiene");
        stop_words.add("tienen");
        stop_words.add("toda");
        stop_words.add("todas");
        stop_words.add("todavía");
        stop_words.add("todo");
        stop_words.add("todos");
        stop_words.add("total");
        stop_words.add("tras");
        stop_words.add("trata");
        stop_words.add("través");
        stop_words.add("tres");
        stop_words.add("tuvo");
        stop_words.add("un");
        stop_words.add("una");
        stop_words.add("unas");
        stop_words.add("uno");
        stop_words.add("unos");
        stop_words.add("usted");
        stop_words.add("va");
        stop_words.add("vamos");
        stop_words.add("van");
        stop_words.add("varias");
        stop_words.add("varios");
        stop_words.add("veces");
        stop_words.add("ver");
        stop_words.add("vez");
        stop_words.add("y");
        stop_words.add("ya");
        stop_words.add("yo");
        stop_words.add("él");
        stop_words.add("ésta");
        stop_words.add("éstas");
        stop_words.add("éste");
        stop_words.add("éstos");
        stop_words.add("última");
        stop_words.add("últimas");
        stop_words.add("último");
        stop_words.add("últimos");
        
        stop_words.add("a");        //ANGLÈS
        stop_words.add("a's");
        stop_words.add("able");
        stop_words.add("about");
        stop_words.add("above");
        stop_words.add("according");
        stop_words.add("accordingly");
        stop_words.add("across");
        stop_words.add("actually");
        stop_words.add("after");
        stop_words.add("afterwards");
        stop_words.add("again");
        stop_words.add("against");
        stop_words.add("ain't");
        stop_words.add("all");
        stop_words.add("allow");
        stop_words.add("allows");
        stop_words.add("almost");
        stop_words.add("alone");
        stop_words.add("along");
        stop_words.add("already");
        stop_words.add("also");
        stop_words.add("although");
        stop_words.add("always");
        stop_words.add("am");
        stop_words.add("among");
        stop_words.add("amongst");
        stop_words.add("an");
        stop_words.add("and");
        stop_words.add("another");
        stop_words.add("any");
        stop_words.add("anybody");
        stop_words.add("anyhow");
        stop_words.add("anyone");
        stop_words.add("anything");
        stop_words.add("anyway");
        stop_words.add("anyways");
        stop_words.add("anywhere");
        stop_words.add("apart");
        stop_words.add("appear");
        stop_words.add("appreciate");
        stop_words.add("appropriate");
        stop_words.add("are");
        stop_words.add("aren't");
        stop_words.add("around");
        stop_words.add("as");
        stop_words.add("aside");
        stop_words.add("ask");
        stop_words.add("asking");
        stop_words.add("associated");
        stop_words.add("at");
        stop_words.add("available");
        stop_words.add("away");
        stop_words.add("awfully");
        stop_words.add("b");
        stop_words.add("be");
        stop_words.add("became");
        stop_words.add("because");
        stop_words.add("become");
        stop_words.add("becomes");
        stop_words.add("becoming");
        stop_words.add("been");
        stop_words.add("before");
        stop_words.add("beforehand");
        stop_words.add("behind");
        stop_words.add("being");
        stop_words.add("believe");
        stop_words.add("below");
        stop_words.add("beside");
        stop_words.add("besides");
        stop_words.add("best");
        stop_words.add("better");
        stop_words.add("between");
        stop_words.add("beyond");
        stop_words.add("both");
        stop_words.add("brief");
        stop_words.add("but");
        stop_words.add("by");
        stop_words.add("c");
        stop_words.add("c'mon");
        stop_words.add("c's");
        stop_words.add("came");
        stop_words.add("can");
        stop_words.add("can't");
        stop_words.add("cannot");
        stop_words.add("cant");
        stop_words.add("cause");
        stop_words.add("causes");
        stop_words.add("certain");
        stop_words.add("certainly");
        stop_words.add("changes");
        stop_words.add("clearly");
        stop_words.add("co");
        stop_words.add("com");
        stop_words.add("come");
        stop_words.add("comes");
        stop_words.add("concerning");
        stop_words.add("consequently");
        stop_words.add("consider");
        stop_words.add("considering");
        stop_words.add("contain");
        stop_words.add("containing");
        stop_words.add("contains");
        stop_words.add("corresponding");
        stop_words.add("could");
        stop_words.add("couldn't");
        stop_words.add("course");
        stop_words.add("currently");
        stop_words.add("d");
        stop_words.add("definitely");
        stop_words.add("described");
        stop_words.add("despite");
        stop_words.add("did");
        stop_words.add("didn't");
        stop_words.add("different");
        stop_words.add("do");
        stop_words.add("does");
        stop_words.add("doesn't");
        stop_words.add("doing");
        stop_words.add("don't");
        stop_words.add("done");
        stop_words.add("down");
        stop_words.add("downwards");
        stop_words.add("during");
        stop_words.add("e");
        stop_words.add("each");
        stop_words.add("edu");
        stop_words.add("eg");
        stop_words.add("eight");
        stop_words.add("either");
        stop_words.add("else");
        stop_words.add("elsewhere");
        stop_words.add("enough");
        stop_words.add("entirely");
        stop_words.add("especially");
        stop_words.add("et");
        stop_words.add("etc");
        stop_words.add("even");
        stop_words.add("ever");
        stop_words.add("every");
        stop_words.add("everybody");
        stop_words.add("everyone");
        stop_words.add("everything");
        stop_words.add("everywhere");
        stop_words.add("ex");
        stop_words.add("exactly");
        stop_words.add("example");
        stop_words.add("except");
        stop_words.add("f");
        stop_words.add("far");
        stop_words.add("few");
        stop_words.add("fifth");
        stop_words.add("first");
        stop_words.add("five");
        stop_words.add("followed");
        stop_words.add("following");
        stop_words.add("follows");
        stop_words.add("for");
        stop_words.add("former");
        stop_words.add("formerly");
        stop_words.add("forth");
        stop_words.add("four");
        stop_words.add("from");
        stop_words.add("further");
        stop_words.add("furthermore");
        stop_words.add("g");
        stop_words.add("get");
        stop_words.add("gets");
        stop_words.add("getting");
        stop_words.add("given");
        stop_words.add("gives");
        stop_words.add("go");
        stop_words.add("goes");
        stop_words.add("going");
        stop_words.add("gone");
        stop_words.add("got");
        stop_words.add("gotten");
        stop_words.add("greetings");
        stop_words.add("h");
        stop_words.add("had");
        stop_words.add("hadn't");
        stop_words.add("happens");
        stop_words.add("hardly");
        stop_words.add("has");
        stop_words.add("hasn't");
        stop_words.add("have");
        stop_words.add("haven't");
        stop_words.add("having");
        stop_words.add("he");
        stop_words.add("he's");
        stop_words.add("hello");
        stop_words.add("help");
        stop_words.add("hence");
        stop_words.add("her");
        stop_words.add("here");
        stop_words.add("here's");
        stop_words.add("hereafter");
        stop_words.add("hereby");
        stop_words.add("herein");
        stop_words.add("hereupon");
        stop_words.add("hers");
        stop_words.add("herself");
        stop_words.add("hi");
        stop_words.add("him");
        stop_words.add("himself");
        stop_words.add("his");
        stop_words.add("hither");
        stop_words.add("hopefully");
        stop_words.add("how");
        stop_words.add("howbeit");
        stop_words.add("however");
        stop_words.add("i");
        stop_words.add("i'd");
        stop_words.add("i'll");
        stop_words.add("i'm");
        stop_words.add("i've");
        stop_words.add("ie");
        stop_words.add("if");
        stop_words.add("ignored");
        stop_words.add("immediate");
        stop_words.add("in");
        stop_words.add("inasmuch");
        stop_words.add("inc");
        stop_words.add("indeed");
        stop_words.add("indicate");
        stop_words.add("indicated");
        stop_words.add("indicates");
        stop_words.add("inner");
        stop_words.add("insofar");
        stop_words.add("instead");
        stop_words.add("into");
        stop_words.add("inward");
        stop_words.add("is");
        stop_words.add("isn't");
        stop_words.add("it");
        stop_words.add("it'd");
        stop_words.add("it'll");
        stop_words.add("it's");
        stop_words.add("its");
        stop_words.add("itself");
        stop_words.add("j");
        stop_words.add("just");
        stop_words.add("k");
        stop_words.add("keep");
        stop_words.add("keeps");
        stop_words.add("kept");
        stop_words.add("know");
        stop_words.add("knows");
        stop_words.add("known");
        stop_words.add("l");
        stop_words.add("last");
        stop_words.add("lately");
        stop_words.add("later");
        stop_words.add("latter");
        stop_words.add("latterly");
        stop_words.add("least");
        stop_words.add("less");
        stop_words.add("lest");
        stop_words.add("let");
        stop_words.add("let's");
        stop_words.add("like");
        stop_words.add("liked");
        stop_words.add("likely");
        stop_words.add("little");
        stop_words.add("look");
        stop_words.add("looking");
        stop_words.add("looks");
        stop_words.add("ltd");
        stop_words.add("m");
        stop_words.add("mainly");
        stop_words.add("many");
        stop_words.add("may");
        stop_words.add("maybe");
        stop_words.add("me");
        stop_words.add("mean");
        stop_words.add("meanwhile");
        stop_words.add("merely");
        stop_words.add("might");
        stop_words.add("more");
        stop_words.add("moreover");
        stop_words.add("most");
        stop_words.add("mostly");
        stop_words.add("much");
        stop_words.add("must");
        stop_words.add("my");
        stop_words.add("myself");
        stop_words.add("n");
        stop_words.add("name");
        stop_words.add("namely");
        stop_words.add("nd");
        stop_words.add("near");
        stop_words.add("nearly");
        stop_words.add("necessary");
        stop_words.add("need");
        stop_words.add("needs");
        stop_words.add("neither");
        stop_words.add("never");
        stop_words.add("nevertheless");
        stop_words.add("new");
        stop_words.add("next");
        stop_words.add("nine");
        stop_words.add("no");
        stop_words.add("nobody");
        stop_words.add("non");
        stop_words.add("none");
        stop_words.add("noone");
        stop_words.add("nor");
        stop_words.add("normally");
        stop_words.add("not");
        stop_words.add("nothing");
        stop_words.add("novel");
        stop_words.add("now");
        stop_words.add("nowhere");
        stop_words.add("o");
        stop_words.add("obviously");
        stop_words.add("of");
        stop_words.add("off");
        stop_words.add("often");
        stop_words.add("oh");
        stop_words.add("ok");
        stop_words.add("okay");
        stop_words.add("old");
        stop_words.add("on");
        stop_words.add("once");
        stop_words.add("one");
        stop_words.add("ones");
        stop_words.add("only");
        stop_words.add("onto");
        stop_words.add("or");
        stop_words.add("other");
        stop_words.add("others");
        stop_words.add("otherwise");
        stop_words.add("ought");
        stop_words.add("our");
        stop_words.add("ours");
        stop_words.add("ourselves");
        stop_words.add("out");
        stop_words.add("outside");
        stop_words.add("over");
        stop_words.add("overall");
        stop_words.add("own");
        stop_words.add("p");
        stop_words.add("particular");
        stop_words.add("particularly");
        stop_words.add("per");
        stop_words.add("perhaps");
        stop_words.add("placed");
        stop_words.add("please");
        stop_words.add("plus");
        stop_words.add("possible");
        stop_words.add("presumably");
        stop_words.add("probably");
        stop_words.add("provides");
        stop_words.add("q");
        stop_words.add("que");
        stop_words.add("quite");
        stop_words.add("qv");
        stop_words.add("r");
        stop_words.add("rather");
        stop_words.add("rd");
        stop_words.add("re");
        stop_words.add("really");
        stop_words.add("reasonably");
        stop_words.add("regarding");
        stop_words.add("regardless");
        stop_words.add("regards");
        stop_words.add("relatively");
        stop_words.add("respectively");
        stop_words.add("right");
        stop_words.add("s");
        stop_words.add("said");
        stop_words.add("same");
        stop_words.add("saw");
        stop_words.add("say");
        stop_words.add("saying");
        stop_words.add("says");
        stop_words.add("second");
        stop_words.add("secondly");
        stop_words.add("see");
        stop_words.add("seeing");
        stop_words.add("seem");
        stop_words.add("seemed");
        stop_words.add("seeming");
        stop_words.add("seems");
        stop_words.add("seen");
        stop_words.add("self");
        stop_words.add("selves");
        stop_words.add("sensible");
        stop_words.add("sent");
        stop_words.add("serious");
        stop_words.add("seriously");
        stop_words.add("seven");
        stop_words.add("several");
        stop_words.add("shall");
        stop_words.add("she");
        stop_words.add("should");
        stop_words.add("shouldn't");
        stop_words.add("since");
        stop_words.add("six");
        stop_words.add("so");
        stop_words.add("some");
        stop_words.add("somebody");
        stop_words.add("somehow");
        stop_words.add("someone");
        stop_words.add("something");
        stop_words.add("sometime");
        stop_words.add("sometimes");
        stop_words.add("somewhat");
        stop_words.add("somewhere");
        stop_words.add("soon");
        stop_words.add("sorry");
        stop_words.add("specified");
        stop_words.add("specify");
        stop_words.add("specifying");
        stop_words.add("still");
        stop_words.add("sub");
        stop_words.add("such");
        stop_words.add("sup");
        stop_words.add("sure");
        stop_words.add("t");
        stop_words.add("t's");
        stop_words.add("take");
        stop_words.add("taken");
        stop_words.add("tell");
        stop_words.add("tends");
        stop_words.add("th");
        stop_words.add("than");
        stop_words.add("thank");
        stop_words.add("thanks");
        stop_words.add("thanx");
        stop_words.add("that");
        stop_words.add("that's");
        stop_words.add("thats");
        stop_words.add("the");
        stop_words.add("their");
        stop_words.add("theirs");
        stop_words.add("them");
        stop_words.add("themselves");
        stop_words.add("then");
        stop_words.add("thence");
        stop_words.add("there");
        stop_words.add("there's");
        stop_words.add("thereafter");
        stop_words.add("thereby");
        stop_words.add("therefore");
        stop_words.add("therein");
        stop_words.add("theres");
        stop_words.add("thereupon");
        stop_words.add("these");
        stop_words.add("they");
        stop_words.add("they'd");
        stop_words.add("they'll");
        stop_words.add("they're");
        stop_words.add("they've");
        stop_words.add("think");
        stop_words.add("third");
        stop_words.add("this");
        stop_words.add("thorough");
        stop_words.add("thoroughly");
        stop_words.add("those");
        stop_words.add("though");
        stop_words.add("three");
        stop_words.add("through");
        stop_words.add("throughout");
        stop_words.add("thru");
        stop_words.add("thus");
        stop_words.add("to");
        stop_words.add("together");
        stop_words.add("too");
        stop_words.add("took");
        stop_words.add("toward");
        stop_words.add("towards");
        stop_words.add("tried");
        stop_words.add("tries");
        stop_words.add("truly");
        stop_words.add("try");
        stop_words.add("trying");
        stop_words.add("twice");
        stop_words.add("two");
        stop_words.add("u");
        stop_words.add("un");
        stop_words.add("under");
        stop_words.add("unfortunately");
        stop_words.add("unless");
        stop_words.add("unlikely");
        stop_words.add("until");
        stop_words.add("unto");
        stop_words.add("up");
        stop_words.add("upon");
        stop_words.add("us");
        stop_words.add("use");
        stop_words.add("used");
        stop_words.add("useful");
        stop_words.add("uses");
        stop_words.add("using");
        stop_words.add("usually");
        stop_words.add("uucp");
        stop_words.add("v");
        stop_words.add("value");
        stop_words.add("various");
        stop_words.add("very");
        stop_words.add("via");
        stop_words.add("viz");
        stop_words.add("vs");
        stop_words.add("w");
        stop_words.add("want");
        stop_words.add("wants");
        stop_words.add("was");
        stop_words.add("wasn't");
        stop_words.add("way");
        stop_words.add("we");
        stop_words.add("we'd");
        stop_words.add("we'll");
        stop_words.add("we're");
        stop_words.add("we've");
        stop_words.add("welcome");
        stop_words.add("well");
        stop_words.add("went");
        stop_words.add("were");
        stop_words.add("weren't");
        stop_words.add("what");
        stop_words.add("what's");
        stop_words.add("whatever");
        stop_words.add("when");
        stop_words.add("whence");
        stop_words.add("whenever");
        stop_words.add("where");
        stop_words.add("where's");
        stop_words.add("whereafter");
        stop_words.add("whereas");
        stop_words.add("whereby");
        stop_words.add("wherein");
        stop_words.add("whereupon");
        stop_words.add("wherever");
        stop_words.add("whether");
        stop_words.add("which");
        stop_words.add("while");
        stop_words.add("whither");
        stop_words.add("who");
        stop_words.add("who's");
        stop_words.add("whoever");
        stop_words.add("whole");
        stop_words.add("whom");
        stop_words.add("whose");
        stop_words.add("why");
        stop_words.add("will");
        stop_words.add("willing");
        stop_words.add("wish");
        stop_words.add("with");
        stop_words.add("within");
        stop_words.add("without");
        stop_words.add("won't");
        stop_words.add("wonder");
        stop_words.add("would");
        stop_words.add("would");
        stop_words.add("wouldn't");
        stop_words.add("x");
        stop_words.add("y");
        stop_words.add("yes");
        stop_words.add("yet");
        stop_words.add("you");
        stop_words.add("you'd");
        stop_words.add("you'll");
        stop_words.add("you're");
        stop_words.add("you've");
        stop_words.add("your");
        stop_words.add("yours");
        stop_words.add("yourself");
        stop_words.add("yourselves");
        stop_words.add("z");
        stop_words.add("zero");
    }
    
    /**
     * Prints llista_paraules
     */
    public void imprimir_llista_paraules () {
        for (Map.Entry<String,ArrayList<Integer>> entry : llista_paraules.entrySet())
            System.out.println(entry.getKey() + ' ' + entry.getValue().size());
    }

    /**
     * Prints llista_metriques
     */
    public void imprimir_llista_metriques () {
        for (Map.Entry<String,Double> entry : llista_metriques.entrySet())
            System.out.println(entry.getKey() + ' ' + entry.getValue());
    }   
}
