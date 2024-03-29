package utils.exceptions.document;

public class AutorTitolNoExisteixException extends DocumentException{

    /**
     * @see utils.exceptions.document.DocumentException#getType()
     */
    @Override
    public String getType() {
        return "[Autor i Titol No Existeix]";
    }

    /**
     * Default constructor
     */
    public AutorTitolNoExisteixException() {
        super("No existeix un Document amb aquest Autor i Títol");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public AutorTitolNoExisteixException(String message) {
        super(message);
    }
}
