package utils.exceptions.boolexpression;

public class InvalidLiteralException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Invalid Literal Error]";
    }

    /**
     * Default Constructor
     */
    public InvalidLiteralException() {
        super("Algun dels literals que conformen la expressió està mal format. Un literal consisteix en un set o una seqüència de cerca");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public InvalidLiteralException(String message) {
        super(message);
    }
    
}
