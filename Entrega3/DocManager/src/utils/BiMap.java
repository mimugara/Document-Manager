package utils;

import java.util.HashMap;

import utils.exceptions.database.DatabaseInconsistentException;

public class BiMap{

    private HashMap<PseudoDocument,Integer> k1_to_k2;
    private HashMap<Integer,PseudoDocument> k2_to_k1;
    private Integer max_id = -1;

    public BiMap() {
        k1_to_k2 = new HashMap<PseudoDocument,Integer>();
        k2_to_k1 = new HashMap<Integer,PseudoDocument>();
    }

    public Integer put(PseudoDocument pd) throws DatabaseInconsistentException{
        Integer id = generate_id();
        if(id > max_id) max_id = id;
        k2_to_k1.put(id, pd);
        Integer prev_id = k1_to_k2.put(pd, id);
        if(prev_id != null) throw new DatabaseInconsistentException("Some IDs in the Database seem to be identical");
        return id;
    }

    public Integer put(PseudoDocument pd, Integer id) {
        if(id > max_id) max_id = id;
        k2_to_k1.put(id, pd);
        return k1_to_k2.put(pd, id);
    }

    public PseudoDocument put(Integer id, PseudoDocument pd) {
        if(id > max_id) max_id = id;
        k1_to_k2.put(pd, id);
        return k2_to_k1.put(id, pd);
    }

    public void remove(PseudoDocument pd) {
        Integer id = k1_to_k2.remove(pd);
        k2_to_k1.remove(id);
        if(id == max_id) max_id--; // max_id will be the previous number or lower, so we don't overlap id on next insert
        return;
    }

    public Integer get(PseudoDocument pd) {
        return k1_to_k2.get(pd);
    }

    public PseudoDocument get(Integer id) {
        return k2_to_k1.get(id);
    }

    private Integer generate_id() {
        max_id++;
        return max_id;
    }

    public int size() throws DatabaseInconsistentException{
        int a = k1_to_k2.size();
        int b = k2_to_k1.size();
        if(a != b) throw new DatabaseInconsistentException("Some IDs in the Database seem to be identical");
        return a;
    }

    public void clear() {
        k1_to_k2.clear();
        k2_to_k1.clear();
    }

    
}
