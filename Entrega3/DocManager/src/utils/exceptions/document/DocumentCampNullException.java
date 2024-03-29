package utils.exceptions.document;

public class DocumentCampNullException extends DocumentException{

    /**
     * @see utils.exceptions.document.DocumentException#getType()
     */
    @Override
    public String getType() {
        return "[Document Camp Null Error]";
    }

    /**
     * Default constructor
     */
    public DocumentCampNullException() {
        super("Un Document no pot tenir el següents camps nulls: Autor, Títol, Format");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public DocumentCampNullException(String message) {
        super(message);
    }
    
}
