package utils;

public class PseudoDocument {
    private String format, autor, titol;

    public PseudoDocument(String f, String t, String a) {
        format = f;
        titol = t;
        autor = a;
    }

    public String get_format() {
        return format;
    }

    public String get_autor() {
        return autor;
    }

    public String get_titol() {
        return titol;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof PseudoDocument)) return false;
        PseudoDocument pobj = (PseudoDocument) obj;
        return(this.autor.equals(pobj.autor) &&
               this.titol.equals(pobj.titol) &&
               this.format.equals(pobj.format));
    }

    @Override
    public int hashCode() {
        String bigString = format+titol+autor;
        return bigString.hashCode();
    }
    
}
