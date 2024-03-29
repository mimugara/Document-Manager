package test.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import domainlayer.model.Document;
import utils.DocumentJSON;
import utils.PseudoDocument;

public class DocumentJSONTest {

    @Test
    public void conversionTest() throws Exception{
        //System.out.println("\n");
        Document d = new Document("txt", "The Bible", "Jesus", "The heavens and the earth were completed with everything that was in them. By the seventh day God finished the work that he had been doing, and he ceased on the seventh day all the work that he had been doing. God blessed the seventh day and made it holy because on it he ceased all the work that he had been doing in creation.");

        //System.out.println("1. LLISTA DE PARAULES:" + rv.get_llista_paraules());
        //rv.imprimir_llista_paraules();

        System.out.println("\n2.");
        DocumentJSON djs = new DocumentJSON(d);
        djs.print();
        System.out.println("\n");

        JSONParser ps = new JSONParser();
        JSONObject reparse = (JSONObject) ps.parse(djs.toJSONString());
        DocumentJSON drp = new DocumentJSON(reparse);

        PseudoDocument salida = drp.unmarshall();

        assertEquals(new PseudoDocument(d.get_format(), d.get_titol(), d.get_autor()), salida);

    }
    
    
}
