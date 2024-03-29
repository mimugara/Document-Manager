package test.datalayer;

import static org.junit.Assert.*;
import org.junit.Test;

import datalayer.CtrlBoolExpressionMem;
import domainlayer.model.BoolExpression;
import domainlayer.model.Document;;

public class CtrlBoolExpressionMemTest {

    @Test
    public void testAlta1() throws Exception {
        CtrlBoolExpressionMem bools = new CtrlBoolExpressionMem();
        BoolExpression bTrue = new BoolExpression("{a} & {b}");

        bools.alta_BoolExpression("aha", "{a} & {b}");
        BoolExpression bo = bools.get_BoolExpression("aha");
        
        Document a = new Document("0", "aha", "aha", "     ");
        Document b = new Document("0", "aha", "aha", "    c");
        Document c = new Document("0", "aha", "aha", "  b  ");
        Document d = new Document("0", "aha", "aha", "  b c");
        Document e = new Document("0", "aha", "aha", "a    ");
        Document f = new Document("0", "aha", "aha", "a   c");
        Document g = new Document("0", "aha", "aha", "a b  ");
        Document h = new Document("0", "aha", "aha", "a b c");

        assertEquals(bTrue.evaluate(a), bo.evaluate(a));
        assertEquals(bTrue.evaluate(b), bo.evaluate(b));
        assertEquals(bTrue.evaluate(c), bo.evaluate(c));
        assertEquals(bTrue.evaluate(d), bo.evaluate(d));
        assertEquals(bTrue.evaluate(e), bo.evaluate(e));
        assertEquals(bTrue.evaluate(f), bo.evaluate(f));
        assertEquals(bTrue.evaluate(g), bo.evaluate(g));
        assertEquals(bTrue.evaluate(h), bo.evaluate(h));

    }
    
}
