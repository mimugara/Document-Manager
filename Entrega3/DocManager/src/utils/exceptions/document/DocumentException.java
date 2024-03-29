package utils.exceptions.document;

import utils.exceptions.CustomException;

public class DocumentException extends CustomException{

    /**
     * @see utils.exceptions.CustomException#getType()
     */
    public String getType() {
        return "[Document Error]";
    }

    /**
     * Default constructor
     */
    public DocumentException() {
        super("An error occurred while generating or storing a Document");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public DocumentException(String message) {
        super(message);
    }
    
}
