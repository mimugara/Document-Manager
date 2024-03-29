package test.domainlayer.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domainlayer.model.Document;
import domainlayer.model.RepVec;

public class RepVecTest {
    private RepVec rep_vec= null;

    @Before
    public void setUp() {
        String content="This document will serve to explain how I, Judas, lured the man into thinking he could count on me\n" +
        "it is not easy to befriend the only child of God, as any only-child, he constantly got everything he asked from daddy.\n" +
        "One would think that 30 pieces of silver would not be worth the trouble, but bear with be, as someone who is THE child of God\n" +
        "how can a mere human like me trick him? I'can't! He would have known it all along, as the reincarnation of God in the face of the Earth Earth";
        try {
            Document auxliar = new Document("txt", "How I became best friends with Jesus" , "Judas", content);
            rep_vec = auxliar.get_repvec();
        } catch (Exception e) {
            rep_vec = null;
            assertTrue("Bad constructor", false);
        }
        
    }

    @After
    public void tearDown() {
        rep_vec = null;
    }

    @Test
    public void squenceNullExists() {
        String[] subquery = {""};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), true);
    }

    @Test
    public void squenceExists() {
        String[] subquery = {"Judas", "lured", "the", "man"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), true);
    }

    @Test
    public void squenceOfOneWordIn() {
        String[] subquery = {"Judas"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), true);
    }

    @Test
    public void squenceExistsRepeatedWord() {
        String[] subquery = {"Earth", "Earth"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), true);
    }

    @Test
    public void squenceNotExistsRepeatedWord() {
        String[] subquery = {"Judas", "Judas"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), false);
    }

    @Test
    public void squenceNotExists1() {
        // First word exceeded its appearances
        String[] subquery = {"Judas", "is" , "lured", "the", "man"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), false);
    }

    @Test
    public void squenceNotExists2() {
        // First word exceeded its appearances
        String[] subquery = {"God", "is", "good"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), false);
    }

    @Test
    public void squenceNotExists3() {
        // Other word exceeded its appearances
        String[] subquery = {"of", "God", "Earth"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), false);
    }

    @Test
    public void sequenceOneWordNotIn() {
        // Other word exceeded its appearances
        String[] subquery = {"of", "God", "Baby"};
        assertEquals(rep_vec.is_sequence_in(subquery, 0), false);
    }

    
    
}
