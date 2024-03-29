package utils.exceptions.boolexpression;

public class InvalidSequenceException extends BoolExpressionException{

    /**
     * @see utils.exceptions.boolexpression.BoolExpressionException#getType()
     */
    @Override
    public String getType() {
        return "[Invalid Sequence Error]";
    }

    /**
     * Default Constructor
     */
    public InvalidSequenceException() {
        super("Alguna de les seqüències que conformen la expressió presenta caràcters no permesos dins d'una seqüència. Una seqüència de cerca consisteix en una sucessió ordenada de paraules separades per espais les quals es buscaran en el Document");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public InvalidSequenceException(String message) {
        super(message);
    }
}
