package utils.exceptions.boolexpression;

public class BoolExpressionIdentificadorJaExisteixException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Identificador Ja Existeix Error]";
    }

    /**
     * Default Constructor
     */
    public BoolExpressionIdentificadorJaExisteixException() {
        super("Ja existeix una BoolExpression amb el mateix identificador");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public BoolExpressionIdentificadorJaExisteixException(String message) {
        super(message);
    }
    
}
