package exception;

public class InvalidDataType extends InvalidInput{
    public InvalidDataType() {
        super("This Database Does Not Support This Type Of Data!");
    }
}
