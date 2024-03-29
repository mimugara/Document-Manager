package utils.exceptions.boolexpression;

public class BoolExpressionIdentificadorNoExisteixException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Identificador No Existeix Error]";
    }

    /**
     * Default Constructor
     */
    public BoolExpressionIdentificadorNoExisteixException() {
        super("No existeix una BoolExpression amb aquest identificador");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public BoolExpressionIdentificadorNoExisteixException(String message) {
        super(message);
    }
    
}
