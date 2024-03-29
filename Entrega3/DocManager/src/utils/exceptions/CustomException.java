package utils.exceptions;

public abstract class CustomException extends Exception{

    /**
     * @return Type of the produced exception
     */
    public abstract String getType();

    /**
     * Default constructor
     */
    public CustomException() {
        super();
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public CustomException(String message) {
        super(message);
    }
    
}
