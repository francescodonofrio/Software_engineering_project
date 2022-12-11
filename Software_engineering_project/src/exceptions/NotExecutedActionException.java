package exceptions;

public class NotExecutedActionException extends Exception {

    public NotExecutedActionException() {
    }

    public NotExecutedActionException(String message) {
        super(message);
    }
}
