package utils;

import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import domainlayer.model.RepVec;

@SuppressWarnings("unchecked")
public class RepVecJSON extends JSONObject{


    public RepVecJSON() {
        super();
    }

    public RepVecJSON(JSONObject js) {
        super(js);
    }

    public RepVecJSON(RepVec rv) {
        // private int num_paraules;
        // private TreeMap<String, ArrayList<Integer>> llista_paraules;

        super();

        this.put("num_paraules",rv.get_num_paraules());

        JSONObject lp = new JSONObject(rv.get_llista_paraules());
        this.put("llista_paraules", lp);

    }

    public void print() {
        String s = this.toJSONString();
        System.out.println(s);
    }

    public RepVec unmarshall() {
        TreeMap<String, ArrayList<Integer>> llista_paraules = new TreeMap<String, ArrayList<Integer>>();
        JSONObject lp = (JSONObject)this.get("llista_paraules");

        Iterator it = lp.keySet().iterator();
        while(it.hasNext()) {
            String key = (String) it.next();
            JSONArray array = (JSONArray) lp.get(key);

            // Automatic conversion from JSONArrayList to
            ArrayList<Integer> val = new ArrayList<Integer>();
            for(int i = 0; i < array.size(); i++) {
                Long v = (Long) array.get(i);
                val.add(v.intValue()); 
            };

            llista_paraules.put(key, val);
        }

        Long lg = (Long) this.get("num_paraules");
        int num_paraules = lg.intValue();
        RepVec rv = new RepVec(llista_paraules,num_paraules);
        
        return rv;
    }
}
