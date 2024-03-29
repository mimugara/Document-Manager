package utils.exceptions.database;

import utils.exceptions.CustomException;

public class DatabaseException extends CustomException{

    /**
     * @see utils.exceptions.CustomException#getType()
     */
    public String getType() {
        return "[Database Error]";
    }

    /**
     * Default constructor
     */
    public DatabaseException() {
        super("Database encountered an error");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public DatabaseException(String message) {
        super(message);
    }
}
