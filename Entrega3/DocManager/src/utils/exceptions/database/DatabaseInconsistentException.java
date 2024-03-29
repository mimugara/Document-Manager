package utils.exceptions.database;

public class DatabaseInconsistentException extends DatabaseException{

    /**
     * @see utils.exceptions.database.DatabaseException#getType()
     */
    @Override
    public String getType() {
        return "[Database Inconsistent Error]";
    }

    /**
     * Default constructor
     */
    public DatabaseInconsistentException() {
        super("An inconsistency in the Database was found. Remember that all the Folders must be present and IDs cannot be repeated");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public DatabaseInconsistentException(String message) {
        super(message);
    }
    
}
