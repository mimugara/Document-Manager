package test.datalayer;

import domainlayer.model.Document;
import static org.junit.Assert.*;

import org.junit.Test;
import datalayer.CtrlDocumentMem;

import java.util.ArrayList;

public class CtrlDocumentMemTest {

    // Comprovació alta document 
    @Test
    public void testAlta1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "comprovem que funciona alta document, mostrar i llistar document");
        Document d = docs.getDoc("a", "lucas");
        assertEquals(d.get_autor(), "lucas");
        assertEquals(d.get_titol(), "a");
        assertEquals(d.get_contingut(), "comprovem que funciona alta document, mostrar i llistar document");
    }

    // Comprovació alta document, modificar document i mostrar contingut
    @Test
    public void testMod1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.mod_doc("a", "lucas", "aixo si");
        String Output = docs.mostrar_contingut("a","lucas");
        assertEquals(Output, "aixo si");
    }
    
    @Test
    public void testMod2() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.mod_doc("a", "lucas", "aixo tampoc");
        docs.mod_doc("a", "lucas", "aixo si");
        String Output = docs.mostrar_contingut("a","lucas");
        assertEquals(Output, "aixo si");
    }

    // Comprovació alta document, baixa document i llistar titols autor
    @Test
     public void testBaixa1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        docs.alta_doc ("0", "c", "lucas", "aquest tambe");
        docs.baixa_doc("a", "lucas");
        ArrayList<String> Output = docs.llistar_titols_unautor("lucas");
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("b");
        Expected.add("c");
        Output.equals(Expected);
    }
    
    //Comprovació que quan un autor no té documents s'esborra del sistema
    @Test
    public void testBaixa2() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aixo no surt");
        docs.alta_doc ("0", "c", "lucia", "aquest si");
        docs.baixa_doc("a", "lucas");
        docs.baixa_doc("b", "lucas");
        ArrayList<String> Output = docs.llistarPrefix("lu");
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("lucia");
        Output.equals(Expected);
    }
    
    //Comprovació que en fer una baixa podem tornar a fer una alta del mateix títol i autor
    @Test
    public void testAltaBaixa() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.baixa_doc ("a", "lucas");
        docs.alta_doc ("0", "a", "lucas", "aixo si que surt");
        Document d = docs.getDoc("a", "lucas");
        assertEquals(d.get_autor(), "lucas");
        assertEquals(d.get_titol(), "a");
        assertEquals(d.get_contingut(), "aixo si que surt");
    }
    

    // Comprovació llistarPrefix quan hi ha autors que comencen per l'string passat com a paràmetre
    @Test
    public void testLlistarPrefix1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        docs.alta_doc ("0", "b", "lucia", "aquest pot ser");
        docs.alta_doc ("0", "e", "miquel", "aquest pot ser que si");
        ArrayList<String> Output = docs.llistarPrefix("luc");
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("lucas");
        Expected.add("lucia");
        Output.equals(Expected);
    }

    // Comprovació llistarPrefix quan passem un string buit com a paràmetre
    @Test
    public void testLlistarPrefix2() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        docs.alta_doc ("0", "b", "lucia", "aquest pot ser");
        docs.alta_doc ("0", "e", "miquel", "aquest pot ser que si");
        ArrayList<String> Output = docs.llistarPrefix("");
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("lucas");
        Expected.add("lucia");
        Expected.add("miquel");
        Output.equals(Expected);
    }
    
    // Comprovació llistarPrefix quan no hi ha cap autor (ni document) a la base de dades
    @Test
    public void testLlistarPrefix3() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        ArrayList<String> Output = docs.llistarPrefix("");
        ArrayList<String> Expected = new ArrayList<String>();
        Output.equals(Expected);
    }

    // Comprovació llistarPrefix quan no hi ha cap autor que comenci pel prefix passat com a paràmetre
    @Test
    public void testLlistarPrefix4() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        docs.alta_doc ("0", "b", "lucia", "aquest pot ser");
        docs.alta_doc ("0", "e", "miquel", "aquest pot ser que si");
        ArrayList<String> Output = docs.llistarPrefix("a");
        ArrayList<String> Expected = new ArrayList<String>();
        Output.equals(Expected);
    }
    
    
    // Comprovació llistar títols quan hi ha diversos autors a memòria
    @Test 
    public void llistarTitAut1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "c", "lucas", "aquest si");
        docs.alta_doc ("0", "d", "lucia", "aquest pot ser");
        docs.alta_doc ("0", "e", "miquel", "aquest pot ser que si");
        ArrayList<String> Output = docs.llistar_titols_unautor("lucas");
        ArrayList<String> Expected = new ArrayList<String>();
        Output.equals(Expected);
    }
    
    // Comprovació llistar títols quan hi ha diversos autors amb el mateix títol a memòria 
    @Test
    public void llistarTitAut2() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        docs.alta_doc ("0", "b", "lucia", "aquest pot ser");
        docs.alta_doc ("0", "e", "miquel", "aquest pot ser que si");
        ArrayList<String> Output = docs.llistar_titols_unautor("lucas");
        ArrayList<String> Expected = new ArrayList<String>();
        Output.equals(Expected);
    }
    
    @Test 
    //Comprovació base mostrar contingut
    public void mostrarContingut1() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "aquest si");
        String s = docs.mostrar_contingut("b", "lucas");
        assertEquals(s, "aquest si");         
    }
    
    @Test
    //Comprovació mostrar contingut buit
    public void mostrarContingut2() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc ("0", "a", "lucas", "aixo no surt");
        docs.alta_doc ("0", "b", "lucas", "");
        String s = docs.mostrar_contingut("b", "lucas");
        assertEquals(s, "");         
    }

    @Test
    public void ordenar_per_semblanca() throws Exception {
        CtrlDocumentMem docs = new CtrlDocumentMem();
        docs.alta_doc("0","La gran qüestió","Josep","El dia que se'n va adonar ja era massa tard.");
        docs.alta_doc("0","L'honestedat","Quim Monzó","Empenyent un carret amb una safata on hi ha un got d'aigua, la infermera entra a l'habitació 93.");
        docs.alta_doc("0","Els detalls del nou protocol de desnonaments dels Mossos d'Esquadra","GEMMA GARCIA","Des de l’entrada en vigor del protocol que estableix l’actuació policial en relació amb desnonaments no ha canviat res.");
        docs.alta_doc("0","Aquest està buit","Eva","");
        docs.alta_doc("0","Aquest hauria de ser la gran cosa","Joan","El dia que se'n va adonar ja era massa tard.");
        docs.alta_doc("0","El que realment sorprèn","Pere","Text qualsevol que parla sobre el temps i de que se l'hi va fer");
        docs.alta_doc("0","Aquest és molt llarg","Pere","Per incorporar al projecte qualsevol codi (font, objecte, executable, o llibreries) no desenvolupat per l’equip, caldrà comptar amb el permís explícit del tutor, per escrit o per correu electrònic. Això inclou el codi generat per eines altres que el compilador del llenguatge de referència, com per exemple, programes per dissenyar interfícies que  generen codi font o objecte. S'exceptua el codi instalat junt amb el llenguatge de referència en els PCs de les sales d’usuaris de l'LCFIB, i aquell que es designi explícitament en una llista que es podrà publicar a la pàgina de l'assignatura. Incorporar codi extern al projecte sense permís es considerarà un intent d'engany i estarà subjecte a la consideració de còpia recollida en les normatives acadèmiques de la universitat. En cas de dubte, cal consultar el tutor.");
        docs.alta_doc("0","El perquè de moltes coses","Eva","Aquí hi posem text, poc text, però text.");
        docs.alta_doc("0","El perquè de moltes coses","Joan","Aquí hi posem text, poc text, però text.");

        ArrayList<Document> res = docs.ordenar_per_semblanca("La gran qüestió","Josep",10);
        String[][] result = new String[8][2];
        int i=0;
        for (Document d : res) {
            result[i][0] = d.get_titol();
            result[i][1] = d.get_autor();
        }
        String[][] comparar = new String[8][2];

        comparar[0][0] = "Aquest està buit";
        comparar[0][1] = "Eva";
        comparar[1][0] = "Aquest és molt llarg";
        comparar[1][1] = "Pere";
        comparar[2][0] = "El perquè de moltes coses";
        comparar[2][1] = "Joan";
        comparar[3][0] = "El perquè de moltes coses";
        comparar[3][1] = "Eva";
        comparar[4][0] = "Aquest hauria de ser la gran cosa";
        comparar[4][1] = "Joan";
        comparar[5][0] = "Els detalls del nou protocol de desnonaments dels Mossos d'Esquadra";
        comparar[5][1] = "GEMMA GARCIA";
        comparar[6][0] = "El que realment sorprèn";
        comparar[6][1] = "Pere";
        comparar[7][0] = "L'honestedat";
        comparar[7][1] = "Quim Monzó";

        result.equals(comparar);
    }
}
