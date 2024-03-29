package test.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import domainlayer.model.RepVec;
import utils.RepVecJSON;

public class RepVecJSONTest {
    @Test
    public void conversionTest() throws Exception{
        //System.out.println("\n");
        RepVec rv = new RepVec("Jesus", "The heavens and the earth were completed with everything that was in them. By the seventh day God finished the work that he had been doing, and he ceased on the seventh day all the work that he had been doing. God blessed the seventh day and made it holy because on it he ceased all the work that he had been doing in creation.");

        System.out.println("1. LLISTA DE PARAULES:" + rv.get_llista_paraules());
        rv.imprimir_llista_paraules();

        System.out.println("\n2.");
        RepVecJSON rvjs = new RepVecJSON(rv);
        rvjs.print();
        System.out.println("\n");

        JSONParser ps = new JSONParser();
        JSONObject reparse = (JSONObject) ps.parse(rvjs.toJSONString());
        RepVecJSON rp = new RepVecJSON(reparse);
        System.out.println("3.\n" + reparse + "\n");

        RepVec salida = rp.unmarshall();
        System.out.println("4.");
        System.out.println("LLISTA DE PARAULES:" + salida.get_llista_paraules());
        salida.imprimir_llista_paraules();

        assertTrue("LLISTES",rv.get_llista_paraules().equals(salida.get_llista_paraules()));

        assertTrue("Equality of RepVec in and out", rv.equals(salida));
    }
}
