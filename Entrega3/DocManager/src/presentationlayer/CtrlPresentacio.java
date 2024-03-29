package presentationlayer;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import domainlayer.controllers.CtrlDomini;
import domainlayer.model.Document;
import utils.exceptions.boolexpression.BoolExpressionException;
import utils.exceptions.boolexpression.BoolExpressionIdentificadorNoExisteixException;
import utils.exceptions.database.DatabaseException;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;
import utils.exceptions.document.AutorNoExisteixException;
import utils.exceptions.document.AutorTitolJaExisteixException;
import utils.exceptions.document.AutorTitolNoExisteixException;
import utils.exceptions.document.DocumentCampNullException;

public class CtrlPresentacio {
    /**
     * CtrlDomini instance
     */    
    private CtrlDomini ctrlDomini;
    /**
     * VistaPrincipal instance
     */
    private VistaPrincipal vistaPrincipal;
    /**
     * AltaDocument instance
     */
    private AltaDocument altaDocument;
    /**
     * ModificarDocument instance
     */
    private ModificarDocument modificarDocument;
    /**
     * BaixaDocument instance
     */
    private BaixaDocument baixaDocument;
    /**
     * MostrarContingut instance
     */
    private MostrarContingut mostrarContingut;
    /**
     * CarregarDocument instance
     */
    private CarregarDocument carregarDocument;
    /**
     * GuardarDocument instance
     */
    private GuardarDocument guardarDocument;
    /**
     * LlistarDocAut instance
     */
    private LlistarDocAut llistarDocAut;
    /**
     * LlistarPrefix instance
     */
    private LlistarPrefix llistarPrefix;
    /**
     * LlistarSemb instance
     */
    private LlistarSemb llistarSemb;
    /**
     * LlistarBool instance
     */
    private LlistarBool llistarBool;
    /**
     * AltaExpBooleana instance
     */
    private AltaExpBooleana altaExpBooleana;
    /**
     * BaixaExpBooleana instance
     */
    private BaixaExpBooleana baixaExpBooleana;
    /**
     * ModificarExpBooleana instance
     */
    private ModificarExpBooleana modificarExpBooleana;

    /**
     * CtrlPresentacio constructor
     */    
    public CtrlPresentacio() {
        try{
            ctrlDomini = CtrlDomini.getInstance();
        } catch(Exception e) {
            e.getStackTrace();
            System.err.println(e);
        }
        
        vistaPrincipal = new VistaPrincipal(this);
        altaDocument = new AltaDocument(this);
        modificarDocument = new ModificarDocument(this);
        baixaDocument = new BaixaDocument(this);
        mostrarContingut = new MostrarContingut(this);
        carregarDocument = new CarregarDocument(this);
        guardarDocument = new GuardarDocument(this);
        llistarDocAut = new LlistarDocAut(this);
        llistarPrefix = new LlistarPrefix(this);
        llistarSemb = new LlistarSemb(this);
        llistarBool = new LlistarBool(this);
        altaExpBooleana = new AltaExpBooleana(this);
        baixaExpBooleana = new BaixaExpBooleana(this);
        modificarExpBooleana = new ModificarExpBooleana(this);
    }

    /**
     * Initialize the presentation layer making the main window vistaPrincipal visible
     */    
    public void inicialitzarPresentacio() {
        //ctrlDomini.inicializarCtrlDomini(); 
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view altaDocument
     */   
    public void sincronitzarVistaPrincipalAAltaDocument() {
        vistaPrincipal.ferInvisible();
        altaDocument.ferVisible();
    }
    /**
     * Changes the view from the secondary view altaDocument to the main view vistaPrincipal
     */   
    public void sincronitzarAltaDocumentAVistaPrincipal() {
        altaDocument.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view baixaDocument
     */   
    public void sincronitzarVistaPrincipalABaixaDocument() {
        vistaPrincipal.ferInvisible();
        baixaDocument.ferVisible();
    }
    /**
     * Changes the view from the secondary view baixaDocument to the main view vistaPrincipal
     */   
    public void sincronitzarBaixaDocumentAVistaPrincipal() {
        baixaDocument.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view modificarDocument
     */
    public void sincronitzarVistaPrincipalAModificarDocument() {
        vistaPrincipal.ferInvisible();
        modificarDocument.ferVisible();
    }
    /**
     * Changes the view from the secondary view modificarDocument to the main view vistaPrincipal
     */ 
    public void sincronitzarModificarDocumentAVistaPrincipal() {
        modificarDocument.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view mostrarContingut
     */  
    public void sincronitzarVistaPrincipalAMostrarContingut() {
        vistaPrincipal.ferInvisible();
        mostrarContingut.ferVisible();
    }
    /**
     * Changes the view from the secondary view mostrarContingut to the main view vistaPrincipal
     */ 
    public void sincronitzarMostrarContingutAVistaPrincipal() {
        mostrarContingut.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view carregarDocument
     */ 
    public void sincronitzarVistaPrincipalACarregarDocument() {
        vistaPrincipal.ferInvisible();
        carregarDocument.ferVisible();
    }
    /**
     * Changes the view from the secondary view carregarDocument to the main view vistaPrincipal
     */ 
    public void sincronitzarCarregarDocumentAVistaPrincipal() {
        carregarDocument.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view guardarDocument
     */ 
    public void sincronitzarVistaPrincipalAGuardarDocument() {
        vistaPrincipal.ferInvisible();
        guardarDocument.ferVisible();
    }
    /**
     * Changes the view from the secondary view guardarDocument to the main view vistaPrincipal
     */ 
    public void sincronitzarGuardarDocumentAVistaPrincipal() {
        guardarDocument.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view llistarDocAut
     */ 
    public void sincronitzarVistaPrincipalALlistDocAut() {
        vistaPrincipal.ferInvisible();
        llistarDocAut.ferVisible();
    }
    /**
     * Changes the view from the secondary view llistarDocAut to the main view vistaPrincipal
     */ 
    public void sincronitzarLlistDocAutAVistaPrincipal() {
        llistarDocAut.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view llistarPrefix
     */ 
    public void sincronitzarVistaPrincipalALlistarPrefix() {
        vistaPrincipal.ferInvisible();
        llistarPrefix.ferVisible();
    }
    /**
     * Changes the view from the secondary view llistarPrefix to the main view vistaPrincipal
     */ 
    public void sincronitzarLlistarPrefixAVistaPrincipal() {
        llistarPrefix.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view llistarSemb
     */ 
    public void sincronitzarVistaPrincipalALlistarSemb() {
        vistaPrincipal.ferInvisible();
        llistarSemb.ferVisible();
    }
    /**
     * Changes the view from the secondary view llistarSemb to the main view vistaPrincipal
     */
    public void sincronitzarLlistarSembAVistaPrincipal() {
        llistarSemb.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view llistarBool
     */ 
    public void sincronitzarVistaPrincipalALlistarBool() {
        vistaPrincipal.ferInvisible();
        llistarBool.ferVisible();
    }
    /**
     * Changes the view from the secondary view llistarBool to the main view vistaPrincipal
     */
    public void sincronitzarLlistarBoolAVistaPrincipal() {
        llistarBool.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view baixaExpBooleana
     */ 
    public void sincronitzarVistaPrincipalAAltaExpBooleana() {
        vistaPrincipal.ferInvisible();
        altaExpBooleana.ferVisible();
    }
    /**
     * Changes the view from the secondary view altaExpBooleana to the main view vistaPrincipal
     */
    public void sincronitzarAltaExpBooleanaAVistaPrincipal() {
        altaExpBooleana.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view baixaExpBooleana
     */ 
    public void sincronitzarVistaPrincipalABaixaExpBooleana() {
        vistaPrincipal.ferInvisible();
        baixaExpBooleana.ferVisible();
    }
    /**
     * Changes the view from the secondary view baixaExpBooleana to the main view vistaPrincipal
     */
    public void sincronitzarBaixaExpBooleanaAVistaPrincipal() {
        baixaExpBooleana.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    /**
     * Changes the view from the main view vistaPrincipal to the secondary view modificarExpBooleana
     */ 
    public void sincronitzarVistaPrincipalAModificarExpBooleana() {
        vistaPrincipal.ferInvisible();
        modificarExpBooleana.ferVisible();
    }
    /**
     * Changes the view from the secondary view modificarExpBooleana to the main view vistaPrincipal
     */
    public void sincronitzarModificarExpBooleanaAVistaPrincipal() {
        modificarExpBooleana.ferInvisible();
        vistaPrincipal.ferVisible();
    }
    


    /* Rest of public functions */

    /**
     * Creates a Document with the given parameters title, author and content
     * @param titol Title of the Document
     * @param autor Author of the Document
     * @param contingut Content of the Document
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws DocumentCampNullException
     * @throws AutorTitolJaExisteixException
     * @throws Exception
     */
    public void crearDocument(String titol, String autor, String contingut) throws AutorTitolJaExisteixException, DocumentCampNullException, DatabaseInconsistentException, IOException {
        ctrlDomini.alta_document("No en té", autor, titol, contingut);
    }

    /**
     * Deletes a Document
     * @param titol Title of the Document
     * @param autor Author of the Document
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     * @throws Exception
     */
    public void borrarDocument(String titol, String autor) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException  {
        ctrlDomini.baixa_document(autor, titol);
    }

    /**
     * Returns the content of a Document
     * @param titol Title of the Document
     * @param autor Author of the Document
     * @return The content of the Document
     * @throws ParseException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     * @throws Exception
     */
    public String getContingut(String titol, String autor) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException, ParseException {
        return ctrlDomini.mostrar_contingut(autor, titol);
    }

    /**
     * Returns a list of Documents that have the given author
     * @param autor Author of the Documents
     * @return A list of the Documents that have the given author
     * @throws AutorNoExisteixException
     * @throws Exception
     */
    public ArrayList<String> getllistatTitols(String autor) throws AutorNoExisteixException  {
        return ctrlDomini.llistar_titols_unautor(autor);
    }

    /**
     * Returns a list of authors that its name begin with the given prefix
     * @param prefix Prefix of the authors names
     * @return A list of authors that its name begin with the given prefix
     */
    public ArrayList<String> getllistatAutors(String prefix) {
        return ctrlDomini.llistarPrefix(prefix);
    }

    /**
     * Returns a list of Documents ordered by similarity with the Document with the given title and author
     * @param t Title of the Document
     * @param a Author of the Document
     * @param k Number of Documents to return
     * @return A list of Documents ordered by similarity with the Document with the given title and author
     * @throws ParseException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws FolderNotFoundException
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     * @throws Exception
     */
    public ArrayList<Document> getllistatSembl(String t, String a, Integer k) throws AutorNoExisteixException, AutorTitolNoExisteixException, FolderNotFoundException, DatabaseInconsistentException, IOException, ParseException  {
        return ctrlDomini.ordenar_per_semblanca(t,a,k);
    }

    /**
     * Returns a list of Documents that match the given boolean expression
     * @param e Boolean expression
     * @return  A list of Documents that match the given boolean expression
     * @throws BoolExpressionIdentificadorNoExisteixException
     * @throws Exception
     */
    public ArrayList<Document> getllistatBool(String e) throws BoolExpressionIdentificadorNoExisteixException  {
        return ctrlDomini.bool_search(e);
    }

    /**
     * Modifies the content of a Document
     * @param titol Title of the Document
     * @param autor Author of the Document
     * @param contingut New content of the Document
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws DocumentCampNullException
     * @throws AutorNoExisteixException
     * @throws AutorTitolNoExisteixException
     * @throws Exception
     */
    public void setContingut(String titol, String autor, String contingut) throws AutorTitolNoExisteixException, AutorNoExisteixException, DocumentCampNullException, DatabaseInconsistentException, IOException {
        ctrlDomini.modificar_document(autor, titol, contingut);
    }

    /**
     * Loads a Document from the computer to the database
     * @param path Path of the Document
     * @throws ParseException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws DocumentCampNullException
     * @throws AutorTitolJaExisteixException
     * @throws Exception
     */
    public void carregarDocument(String path) throws AutorTitolJaExisteixException, DocumentCampNullException, DatabaseInconsistentException, IOException, ParseException {
        ctrlDomini.carregar_document(path);
    }

    /**
     * Downloads a Document from the database to the computer
     * @param titol Title of the Document
     * @param autor Author of the Document
     * @param path Path where the Document will be downloaded
     * @throws ParseException
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws AutorTitolNoExisteixException
     * @throws AutorNoExisteixException
     * @throws Exception
     */
    public void guardarDocument(String titol, String autor, String path) throws AutorNoExisteixException, AutorTitolNoExisteixException, DatabaseInconsistentException, IOException, ParseException {
        ctrlDomini.guardar_document(titol, autor, path);
    }

    /**
     * Creates a new boolean expression
     * @param nom Name of the boolean expression
     * @param query Boolean expression
     * @throws IOException
     * @throws DatabaseException
     * @throws BoolExpressionException
     */
    public void altaExpBool(String nom, String query) throws BoolExpressionException, DatabaseException, IOException {
        ctrlDomini.alta_BoolExpression(nom, query);
    }

    /**
     * Deletes a boolean expression
     * @param nom Name of the boolean expression
     * @throws IOException
     * @throws DatabaseInconsistentException
     * @throws BoolExpressionException
     */
    public void baixaExpBool(String nom) throws BoolExpressionException, DatabaseException, IOException  {
        ctrlDomini.baixa_BoolExpression(nom);
    }

   
    /** Modifies a boolean expression 
     * @param nom Name of the boolean expression
     * @param query New boolean expression
     * @throws BoolExpressionException
     * @throws DatabaseException
     * @throws IOException
     */
    public void modificarExpBool(String nom, String query) throws BoolExpressionException, DatabaseException, IOException {
        ctrlDomini.mod_BoolExpression(nom, query);
    }

    /**
     * Returns the tree of a boolean expression
     * @param expr Boolean expression
     * @return The tree of a boolean expression
     * @throws BoolExpressionException
     */
    public String getExpBool(String expr) throws BoolExpressionException  {
        return ctrlDomini.get_BoolExpression(expr).print();
    }

    /**
     * Resets the database
     * @throws Exception
     */
    public void reset() throws Exception {
        ctrlDomini.reset();
    }

} 
