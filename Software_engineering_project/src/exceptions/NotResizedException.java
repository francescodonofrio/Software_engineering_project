package exceptions;

public class NotResizedException extends Exception {
    public NotResizedException() {
        super();
    }

    public NotResizedException(String message){
        super(message);
    }
}
