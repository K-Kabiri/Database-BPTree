package exception;

public class EmptyTree extends DeleteException{
    public EmptyTree() {
        super("The B+ Tree Is Currently Empty!");
    }
}
