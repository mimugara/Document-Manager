package utils.exceptions.database;

public class FolderNotFoundException extends DatabaseException{

    /**
     * @see utils.exceptions.database.DatabaseException#getType()
     */
    @Override
    public String getType() {
        return "[Folder Not Found Error]";
    }

    /**
     * Default constructor
     */
    public FolderNotFoundException() {
        super("One of the required folders in the Database not found (either DATA, DATA/RepVec, DATA/Contingut, DATA/BoolExpression)");
    }

    /**
     * Constructor with a message propagated
     * @param message
     */
    public FolderNotFoundException(String message) {
        super(message);
    }
    
}
