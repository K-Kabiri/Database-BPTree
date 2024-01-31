package exception;

public class NonExistentKey extends DeleteException{
    public NonExistentKey() {
        super("Key Unable To Be Found!");
    }
}
