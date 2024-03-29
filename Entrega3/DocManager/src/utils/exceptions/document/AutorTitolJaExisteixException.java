package utils.exceptions.document;

public class AutorTitolJaExisteixException extends DocumentException{

    /**
     * @see utils.exceptions.document.DocumentException#getType()
     */
    @Override
    public String getType() {
        return "[Autor i Titol Ja Existeix]";
    }

    /**
     * Default constructor
     */
    public AutorTitolJaExisteixException() {
        super("Ja existeix un Document amb el mateix Autor i Títol");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public AutorTitolJaExisteixException(String message) {
        super(message);
    }
    
}
