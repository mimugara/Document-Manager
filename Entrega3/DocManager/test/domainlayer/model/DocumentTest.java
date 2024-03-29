package test.domainlayer.model;

import domainlayer.model.Document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest {

    private Document documentForBools;

    @Before
    public void setUp() {
        String content="This document will serve to explain how I, Judas, lured the man into thinking he could count on me\n" +
        "it is not easy to befriend the only child of God, as any only-child, he constantly got everything he asked from daddy.\n" +
        "One would think that 30 pieces of silver would not be worth the trouble, but bear with be, as someone who is THE child of God\n" +
        "how can a mere human like me trick him? I'can't! He would have known it all along, as the reincarnation of God in the face of the Earth";
        try {
            documentForBools = new Document("txt", "How I became best friends with Jesus" , "Judas", content);
        } catch (Exception e) {
            documentForBools = null;
            assertTrue("Bad constructor", false);
        }
        
    }

    @After
    public void tearDown() {
        documentForBools = null;
    }

    @Test
    public void mod_doc() throws Exception{
        Document a = new Document("0", "aha", "The Bible", "aixo no");
        a.mod_doc("aixo si");
        assertEquals("aixo si", a.get_contingut());
    }

    // Apart from getters and setters, the only functionalitiy to test is:
    // evaluate_bool(String query)

    @Test
    public void evaluateSetOfWordsValidCase() throws Exception{
        // A well form set of words is:
        String query = "{God}";
        boolean result = documentForBools.evaluate_bool(query);

        assertTrue("The word appears on the text but it is not detected", result);

        // Words on the title must count too
        query = "{Jesus}";
        result = documentForBools.evaluate_bool(query);

        assertTrue("The word appears on the title but it is not detected", result);

        // The empty set of words is fulfilled by any Document
        query = "{}";
        result = documentForBools.evaluate_bool(query);

        assertTrue("The empty set of words must be true for any Document", result);

        // Multiple word queries must be checked too
        query = "{Jesus God child silver}";
        result = documentForBools.evaluate_bool(query);

        assertTrue("The words appears on the Document but they are not detected", result);

        // Finally, lets put some words that are not there
        query = "{Jesus God child silver Yehaw}";
        result = documentForBools.evaluate_bool(query);

        assertTrue("The words appears on the Document but they are not detected", !result);
    }

    @Test
    public void evaluateValidSequence() throws Exception{
    // A well form sequence of words is:
    String query = "\"God\"";
    boolean result = documentForBools.evaluate_bool(query);

    assertTrue("The sequence appears on the text but it is not detected", result);

    // A sequence that does not appear
    query = "\"God is good\"";
    result = documentForBools.evaluate_bool(query);

    assertTrue("This sequence does not appear", !result);

    // A sequence that has a comma in between, which we ignore
    query = "\"Judas lured\"";
    result = documentForBools.evaluate_bool(query);

    assertTrue("The sequence appears" + documentForBools.get_llista_paraules(), result);

    // The empty sequence is true in all docs
    query = "\"\"";
    result = documentForBools.evaluate_bool(query);

    assertTrue("Empty sequence must be true", result);

    }

    @Test
    public void get_contingut() {
        
    }

    @Test
    public void get_llista_paraules () {
        
    }

    @Test
    public void get_llista_metriques () {
        
    }

    // AIXO ha de seguir
    
}
