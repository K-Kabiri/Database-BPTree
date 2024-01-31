package exception;

abstract public class InvalidInput extends Exception{
    public InvalidInput(String message)
    {
        super("Invalid input-"+message);
    }
}
