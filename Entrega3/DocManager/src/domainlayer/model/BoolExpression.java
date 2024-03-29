package domainlayer.model;

import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

import utils.exceptions.boolexpression.*;

/**
 * Class that represents a boolean expression
 */
public class BoolExpression {

    /**
     *  Auxiliar class to represent a node of the AST
     */
    private static class node {
        /**
         * Value of the node, which can be an operator or a literal
         */
        String value;
        /**
         * Reference to the left son of the node in the AST, can be null
         */
        node left_son;
        /**
         * Reference to the right son of the node in the AST, can be null
         */
        node right_son;
        /**
         * Boolean value that indicates if the node was parsed form a parenthesized expression, to preserve hierarchy
         */
        boolean nested_head = false;
    }

    /**
     * Field that indicates if the BoolExpression is parsed. It may not be yet parsed to save memory
     */
    private boolean parsed = false;
    /**
     * Node element that references the parent of the AST
     */
    private node parent;    
    /**
     * Boolean query from which the AST is derived
     */
    private String query;

    /**
     * Check if BoolExpression is parsed in memory
     * @return Whether the tree is parsed in memory or not
     */
    public boolean is_parsed() {
        return this.parsed;
    }

    /**
     * Check if a value is an operator <<'&', '!&', '|', '!', '!|'>>
     * @param a Value of the node
     * @return Whether the passed value is considered an operator
     */
    private static boolean isOp(String a) {
        // "*" means it comes from a nested parenthesis
        return (a.equals("&") || a.equals("|") || a.equals("!") || a.equals("!&") || a.equals("!|"));
    }

    /**
     * Check if a value is an special character <<'(', ')', '{', '}', '"'>> or is Op
     * @param a Character to check if is special or not
     * @return Whether the passed String is considered a special character
     */
    private static boolean specialChar(Character a) {
        return (a.equals('(') || a.equals(')') || a.equals('{') || a.equals('}') || a.equals('"') || isOp(Character.toString(a)));
    }
    
    /**
     * Check for precedence while parsing
     * @param a Next node
     * @param b Previous node
     * @return Returns whether the node a has precedence over the node b when we encounter a parsing situation ...b-a...
     */
    private static boolean precedes(node a, node b) {
        // Does a preceed b?
        if(b.nested_head) return false;
        if(isOp(a.value) && isOp(b.value)) {
            boolean result = (a.value.equals("&") && b.value.equals("|"));
            result = result || (a.value.equals("!&") && b.value.equals("!|"));
            result = result || (a.value.equals("&") && b.value.equals("!|"));
            result = result || (a.value.equals("!&") && b.value.equals("|"));

            
            return result;
        }
        else return false;
    }
    
    /**
     * Auxiliar class to iterate over a query
     */
    private static class Cursor {
        /**
         * Value of the cursor
         */
        Integer value;
        
        /**
         * Increment the cursor
         * @param x Value to increment the cursor
         */
        public void increment(int x) {
            value += x;
        }

        /**
         * Constructor of the cursor
         */
        public Cursor() {
            value = 0;
        }
    }

    /**
     * Treat an input expression before parsing
     * @param query Boolean query to be treated
     * @return The formated query which will be used for parsing. This function corrects any whitespaces and bad formatting
     * @throws InvalidLiteralException
     * @throws InvalidSetException
     * @throws InvalidSequenceException
     * @throws WronglyParenthesizedException
     */
    private static List<String> treatment(String query) throws InvalidLiteralException, InvalidSetException, InvalidSequenceException, WronglyParenthesizedException{
        List<String> formatedQuery = new ArrayList<String>();

        int parentCount = 0;
        boolean pastNeg = false;
        int init;
        String literal;

        for(int i = 0; i < query.length(); i++) {
            Character element = query.charAt(i);
            switch (element) {
                case ' ':
                    // Do nothing
                    break;

                case '(':
                    parentCount++;
                    formatedQuery.add(Character.toString(element));
                    break;
                
                case ')':
                    parentCount--;
                    formatedQuery.add(Character.toString(element));
                    break;

                case '!':
                Character next = query.charAt(i+1);
                    if(pastNeg) pastNeg = false; // double negation cancels out
                    else pastNeg = true;
                    if(next.equals('(')) {
                        if(pastNeg) formatedQuery.add(Character.toString(element));
                        pastNeg = false; // reset
                    }
                    
                    break;
                
                case '{':
                    // Init literal, Set of words
                    init = i;
                    if(pastNeg) {
                        init--;
                        pastNeg = false; // reset
                    }

                    i++;
                    element = query.charAt(i);
                    while(!specialChar(element)) {
                        i++;
                        element = query.charAt(i);
                    }
                    if(!element.equals('}')) throw new InvalidSetException(); // After '{' only spaced words can come  
                    literal = query.substring(init, i+1);
                    formatedQuery.add(literal);       

                    break;

                case '\"':
                    // Init literal, Sequence of words
                    init = i;
                    if(pastNeg) {
                        init--;
                        pastNeg = false;
                    }
                    
                    i++;
                    element = query.charAt(i);
                    while(!specialChar(element)) {
                        i++;
                        element = query.charAt(i);
                    }
                    if(!element.equals('\"')) throw new InvalidSequenceException(); // After '"' only spaced words can come  
                    literal = query.substring(init, i+1);
                    formatedQuery.add(literal);   

                    break;
            
                default:
                    if(!isOp(Character.toString(element))) throw new InvalidLiteralException();
                    formatedQuery.add(Character.toString(element));
                    break;
            }

            if(parentCount < 0) throw new WrongMethodTypeException();
            
        }
        if(parentCount != 0) throw new WronglyParenthesizedException();
        //List<String> formatedQuery = new ArrayList<String>(Arrays.asList(query.split("")));
        //for(String a : formatedQuery) System.out.println(a);
        return formatedQuery;
    }
    
    // Pre: Parenthesis well closed, complete expression with no unassigned operators
    /**
     * Parse an input expression
     * @param query Well treated query in a list of tokens
     * @param cursor Reference to memory slot with an integer for recursive purposes
     * @return The query in a boolean AST form assuming the entry is well formated
     * @throws InvalidLiteralException
     */
    private static node parse_BinTree(List<String> query, Cursor cursor) throws InvalidLiteralException {

        node currentParent;
        currentParent = new node();

        // First, solve all the parenthesis issues
        boolean first_negated = false;
        if(query.get(cursor.value).equals("!")) {
            // This case is for negation in before parethesis only,
            // ensured by treatment
            first_negated = true;
            cursor.increment(1);

        }
        if(query.get(cursor.value).equals("(")) {

            cursor.increment(1);
            currentParent = parse_BinTree(query,cursor);
            if(first_negated) {
                currentParent.value = "!" + currentParent.value;
                first_negated = false;
            }
            currentParent.nested_head = true;
        }
        else if (cursor.value < query.size() && !isOp(query.get(cursor.value))){
            currentParent.value = query.get(cursor.value); 
            cursor.increment(1);
        }

        // Now, next element is not an open parenthesis and at least we have
        // one element on the node structure.

        String op, elem;
        node next_op;
        node next_elem;

        while(cursor.value < query.size() && !query.get(cursor.value).equals(")")) {
            
            // Read
            op = query.get(cursor.value);
            elem = query.get(cursor.value+1);
            // Build
            next_op = new node(); 
            next_elem = new node();

            boolean nested = false;

            next_op.value = op;

            if(query.get(cursor.value).equals("!")) {
                // This case is for negation in before parethesis only,
                // ensured by treatment
                first_negated = true;
                cursor.increment(1);
            }
            if(elem.equals("(")) {
                cursor.increment(2);
                next_elem = parse_BinTree(query,cursor);
                if(first_negated) {
                    next_elem.value = "!"+next_elem.value;
                    first_negated = false;
                }
                nested = true;
                next_elem.nested_head = true;
            }
            else next_elem.value = elem;

            // Possible Situations:
            if(!isOp(currentParent.value) && !isOp(next_elem.value)) {
                // lit op lit
                next_op.left_son = currentParent;
                next_op.right_son = next_elem;
                currentParent = next_op;
            }
            else if(!isOp(currentParent.value) && isOp(next_elem.value)) {
                // lit op op  (always comes from nested second element)
                next_op.left_son = currentParent;
                next_op.right_son = next_elem;
                currentParent = next_op;
            }
            else if(isOp(currentParent.value) && !isOp(next_elem.value)) {
                // op op lit
                if(precedes(next_op, currentParent)) {
                    next_op.left_son = currentParent.right_son;
                    next_op.right_son = next_elem;
                    currentParent.right_son = next_op;
                }
                else {
                    next_op.left_son = currentParent;
                    next_op.right_son = next_elem;
                    currentParent = next_op;
                }
            }
            else {
                // op op op
                next_op.left_son = currentParent;
                next_op.right_son = next_elem;
                currentParent = next_op;
            }
            // Next iteration baby :)
            if(!nested) cursor.increment(2);
        }
        cursor.increment(1);
        return currentParent;
    }

    /**
     * Auxiliar function that enables the evaluation of a Document at a node level
     * @param n Node to evaluate
     * @param a Document to evaluate with this BoolExpression
     * @return The evaluation of node n in Document a
     * @throws InvalidLiteralException
     */
    private boolean evaluate_rec(node n, Document a) throws InvalidLiteralException {
        boolean result = false;
        if(isOp(n.value)) {
            if(n.value.equals("&")) result = evaluate_rec(n.left_son, a) && evaluate_rec(n.right_son, a);
            else if(n.value.equals("|")) result = evaluate_rec(n.left_son, a) || evaluate_rec(n.right_son, a);
            else if(n.value.equals("!&")) result = !(evaluate_rec(n.left_son, a) && evaluate_rec(n.right_son, a));
            else if(n.value.equals("!|")) result = !(evaluate_rec(n.left_son, a) || evaluate_rec(n.right_son, a));
        }
        else {
            result = a.evaluate_bool(n.value);
        }

        return result;
    }

    /**
     * Evaluate a Document
     * @param a Document that wants to be evaluated
     * @return The result of evaluating Document a by this boolean expression, check if it fulfills the BoolExpression
     * @throws InvalidLiteralException
     * @throws InvalidSequenceException
     * @throws InvalidSetException
     * @throws WronglyParenthesizedException
     */
    public boolean evaluate(Document a) throws InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException{
        this.parse();
        return evaluate_rec(parent, a);
    }

    /**
     * Principal constructor that recieves a query as an input
     * @param _query Query that parses this BoolExpression
     * @throws BoolExpressionCampNullException
     * @throws WronglyParenthesizedException
     * @throws InvalidSetException
     * @throws InvalidSequenceException
     * @throws InvalidLiteralException
     */
    public BoolExpression(String _query) throws BoolExpressionCampNullException, InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException{
        if ("".equals(_query)) throw new BoolExpressionCampNullException();
        query = _query;
        parsed = false;
        parent = null;
        this.parse();
    }

    // Only parsed when evaluated
    /**
     * This function parses the BoolExpression into an AST using boolean logic
     * @throws InvalidLiteralException
     * @throws InvalidSequenceException
     * @throws InvalidSetException
     * @throws WronglyParenthesizedException
     */
    private void parse() throws InvalidLiteralException, InvalidSequenceException, InvalidSetException, WronglyParenthesizedException {
        if(!this.parsed) {
            List<String> formatedQuery = treatment(this.query);
            Cursor cursor = new Cursor();
            parent = parse_BinTree(formatedQuery,cursor);
            parsed = true;
        }
    }


    /**
     * Auxiliar function to print an AST
     * @param root Node to print
     * @param space Space left between nodes
     * @return A representation of the AST
     */
    private String print2DUtil(node root, int space)
    {
        int COUNT = 5;
        // Base case
        if (root == null)
            return "";
    
        // Increase distance between levels
        space += COUNT;
        String res = "";
    
        // Process right child first
        res += print2DUtil(root.right_son, space);
    
        // Print current node after space
        // count
        res += "\n";
        for (int i = COUNT; i < space; i++)
            res += " ";
        res += root.value + "\n";
    
        // Process left child
        res += print2DUtil(root.left_son, space);

        return res;
    }

    /**
     * Visual representation of BoolExpression
     * @return The boolean AST in a visual format for better comprehension of how it is evaluated
     * @throws BoolExpressionException
     */
    public String print() throws BoolExpressionException
    {
        String res = "";
        res += query;
        this.parse();
        res += print2DUtil(parent, 0);
        return res;
    }
}
