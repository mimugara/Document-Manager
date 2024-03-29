package utils.exceptions.boolexpression;

public class BoolExpressionCampNullException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[BoolExpression Camp Null Error]";
    }

    /**
     * Default Constructor
     */
    public BoolExpressionCampNullException() {
        super("El camp amb l'expressió booleana a cercar no pot ser null");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public BoolExpressionCampNullException(String message) {
        super(message);
    }
    
}
