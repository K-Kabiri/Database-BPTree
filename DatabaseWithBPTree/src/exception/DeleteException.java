package exception;

abstract public class DeleteException extends Exception{
    public DeleteException(String message)
    {
        super("Delete Exception-"+message);
    }
}
