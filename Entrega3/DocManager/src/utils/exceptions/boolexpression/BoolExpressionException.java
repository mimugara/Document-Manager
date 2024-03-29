package utils.exceptions.boolexpression;

import utils.exceptions.CustomException;

public class BoolExpressionException extends CustomException{

    /**
     * @see utils.exceptions.CustomException#getType()
     */
    @Override
    public String getType() {
        return "[BoolExpression Error]";
    }

    /**
     * Default Constructor
     */
    public BoolExpressionException() {
        super("Error en algun dels passos de parseig o guardat de la expressió booleana");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public BoolExpressionException(String message) {
        super(message);
    }
    
}
