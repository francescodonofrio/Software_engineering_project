package exceptions;

public class VoidActionException extends Exception {
    public VoidActionException() {
        super();
    }

    public VoidActionException(String message) {
        super(message);
    }
}
