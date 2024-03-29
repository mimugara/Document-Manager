package utils;

import org.json.simple.JSONObject;

import domainlayer.model.Document;
//import domainlayer.model.RepVec;

@SuppressWarnings("unchecked")
public class DocumentJSON extends JSONObject{

    public DocumentJSON() {
        super();
    }

    public DocumentJSON(JSONObject js) {
        super(js);
    }

    public DocumentJSON(Document d) {

        super();

        this.put("format", d.get_format());

        this.put("autor", d.get_autor());

        this.put("titol", d.get_titol());

        //this.put("contingut", d.get_contingut());

        //this.put("repvec", new RepVecJSON(d.get_repvec()));

    }

    public void print() {
        String s = this.toJSONString();
        System.out.println(s);
    }

    public PseudoDocument unmarshall(){
        
        String format, autor, titol;
        //String contingut;
        //JSONObject pre_repvec;
        
        format = (String) this.get("format");
        autor = (String) this.get("autor");
        titol = (String) this.get("titol");
        
        //contingut = (String) this.get("contingut");
        //pre_repvec = (JSONObject) this.get("repvec");
        //RepVecJSON repvec = new RepVecJSON(pre_repvec);
        //RepVec rv = repvec.unmarshall();
        

        
        //return new Document(format, titol, autor, contingut, rv);
        return new PseudoDocument(format, titol, autor);

    }
    
}
