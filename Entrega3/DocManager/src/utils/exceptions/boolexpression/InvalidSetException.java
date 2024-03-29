package utils.exceptions.boolexpression;

public class InvalidSetException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Invalid Set Error]";
    }

    /**
     * Default Constructor
     */
    public InvalidSetException() {
        super("Algun dels sets que conformen la expressió presenta caràcters no permesos dins d'un set. Un set de cerca consisteix en una sucessió no ordenada de paraules separades per espais les quals es buscaran en el Document");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public InvalidSetException(String message) {
        super(message);
    }
    
}
