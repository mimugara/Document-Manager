package domainlayer.datainterface;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import utils.exceptions.boolexpression.BoolExpressionCampNullException;
import utils.exceptions.boolexpression.BoolExpressionIdentificadorJaExisteixException;
import utils.exceptions.boolexpression.BoolExpressionIdentificadorNoExisteixException;
import utils.exceptions.boolexpression.InvalidLiteralException;
import utils.exceptions.boolexpression.InvalidSequenceException;
import utils.exceptions.boolexpression.InvalidSetException;
import utils.exceptions.boolexpression.WronglyParenthesizedException;
import utils.exceptions.database.DatabaseInconsistentException;

import domainlayer.model.BoolExpression;

/**
 * The interface of the controller of the BoolExpression
 */
public interface CtrlBoolExpression {

    /**
     * New BoolExpression
     * @param _nom Name of the BoolExpression
     * @param q Query of the BoolExpression
     * @return The new BoolExpression created
     * @throws BoolExpressionIdentificadorJaExisteixException
     * @throws BoolExpressionCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public BoolExpression alta_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorJaExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException;

    /**
     * Modify BoolExpression
     * @param _nom Name of the BoolExpression
     * @param q Query of the BoolExpression
     * @return The result of modifying the BoolExpression
     * @throws BoolExpressionIdentificadorNoExisteixException
     * @throws BoolExpressionCampNullException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public BoolExpression mod_BoolExpression(String _nom, String q) throws BoolExpressionIdentificadorNoExisteixException, BoolExpressionCampNullException, IOException, DatabaseInconsistentException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException ;

    /**
     * Delete BoolExpression
     * @param _nom Name of the BoolExpression
     * @throws BoolExpressionIdentificadorNoExisteixException
     * @throws IOException
     * @throws DatabaseInconsistentException
     */
    public void baixa_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException, IOException, DatabaseInconsistentException ;

    /**
     * Get BoolExpression
     * @param _nom Name of the BoolExpression
     * @return If it exists, the desired BoolExpression
     * @throws BoolExpressionIdentificadorNoExisteixException
     */
    public BoolExpression get_BoolExpression(String _nom) throws BoolExpressionIdentificadorNoExisteixException;

    /**
     * Reset Functionality. Deletes all BoolExpressions
     */
    public void reset() throws IOException, ParseException;
}
