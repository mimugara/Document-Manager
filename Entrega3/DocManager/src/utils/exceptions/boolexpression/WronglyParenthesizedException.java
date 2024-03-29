package utils.exceptions.boolexpression;

public class WronglyParenthesizedException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Wrongly Parenthesized Error]";
    }

    /**
     * Default Constructor
     */
    public WronglyParenthesizedException() {
        super("La expressió presenta parentesis mal tancats o no tancats");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public WronglyParenthesizedException(String message) {
        super(message);
    }
    
}
