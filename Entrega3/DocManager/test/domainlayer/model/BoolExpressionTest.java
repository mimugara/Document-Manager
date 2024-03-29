package test.domainlayer.model;

import domainlayer.model.BoolExpression;
import domainlayer.model.Document;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoolExpressionTest {

    // Testing the interpretation of boolean expressions, all of them well formated
    // (Spaces are omited in the reading)
    @Test
    public void test_evaluate() throws Exception{
        String q0 = "{a} | {b} & {c}";
        String q1 = "( !(({a} | {c}) & ({b} | {c})) )";
        String q2 = "{a} | {b} & {c} & {d} | ({a} | {b} | {c} & {d})";

        BoolExpression e0 = new BoolExpression(q0);
        BoolExpression e1 = new BoolExpression(q1);
        BoolExpression e2 = new BoolExpression(q2);

        Document a = new Document("0", "aha", "aha", "     ");
        Document b = new Document("0", "aha", "aha", "    c");
        Document c = new Document("0", "aha", "aha", "  b  ");
        Document d = new Document("0", "aha", "aha", "  b c");
        Document e = new Document("0", "aha", "aha", "a    ");
        Document f = new Document("0", "aha", "aha", "a   c");
        Document g = new Document("0", "aha", "aha", "a b  ");
        Document h = new Document("0", "aha", "aha", "a b c");

        assertEquals(false, e0.evaluate(a));
        assertEquals(false, e0.evaluate(b));
        assertEquals(false, e0.evaluate(c));
        assertEquals(true, e0.evaluate(d));
        assertEquals(true, e0.evaluate(e));
        assertEquals(true, e0.evaluate(f));
        assertEquals(true, e0.evaluate(g));
        assertEquals(true, e0.evaluate(h));

        assertEquals(true, e1.evaluate(a));
        assertEquals(false, e1.evaluate(b));
        assertEquals(true, e1.evaluate(c));
        assertEquals(false, e1.evaluate(d));
        assertEquals(true, e1.evaluate(e));
        assertEquals(false, e1.evaluate(f));
        assertEquals(false, e1.evaluate(g));
        assertEquals(false, e1.evaluate(h));

         Document x1 = new Document("0", "aha", "aha", "       ");
         Document x2 = new Document("0", "aha", "aha", "      d");
         Document x3 = new Document("0", "aha", "aha", "    c  ");
         Document x4 = new Document("0", "aha", "aha", "    c d");
         Document x5 = new Document("0", "aha", "aha", "  b    ");
         Document x6 = new Document("0", "aha", "aha", "  b   d");
         Document x7 = new Document("0", "aha", "aha", "  b c  ");
         Document x8 = new Document("0", "aha", "aha", "  b c d");
         Document x9 = new Document("0", "aha", "aha", "a      ");
        Document x10 = new Document("0", "aha", "aha", "a     d");
        Document x11 = new Document("0", "aha", "aha", "a   c  ");
        Document x12 = new Document("0", "aha", "aha", "a   c d");
        Document x13 = new Document("0", "aha", "aha", "a b    ");
        Document x14 = new Document("0", "aha", "aha", "a b   d");
        Document x15 = new Document("0", "aha", "aha", "a b c  ");
        Document x16 = new Document("0", "aha", "aha", "a b c d");

        assertEquals(false,e2.evaluate(x1));
        assertEquals(false,e2.evaluate(x2));
        assertEquals(false,e2.evaluate(x3));
        assertEquals(true,e2.evaluate(x4));
        assertEquals(true,e2.evaluate(x5));
        assertEquals(true,e2.evaluate(x6));
        assertEquals(true,e2.evaluate(x7));
        assertEquals(true,e2.evaluate(x8));
        assertEquals(true,e2.evaluate(x9));
        assertEquals(true,e2.evaluate(x10));
        assertEquals(true,e2.evaluate(x11));
        assertEquals(true,e2.evaluate(x12));
        assertEquals(true,e2.evaluate(x13));
        assertEquals(true,e2.evaluate(x14));
        assertEquals(true,e2.evaluate(x15));
        assertEquals(true,e2.evaluate(x16));
    }
    
}
