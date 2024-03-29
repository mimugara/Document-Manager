package utils.exceptions.document;

public class AutorNoExisteixException extends DocumentException{

    /**
     * @see utils.exceptions.document.DocumentException#getType()
     */
    @Override
    public String getType() {
        return "[Autor No Existeix]";
    }

    /**
     * Default constructor
     */
    public AutorNoExisteixException() {
        super("No existeix aquest autor");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public AutorNoExisteixException(String message) {
        super(message);
    }
    
}
