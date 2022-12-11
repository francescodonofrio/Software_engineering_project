package exceptions;

public class NotMovedException extends Exception {
    public NotMovedException() {
        super();
    }

    public NotMovedException(String message){
        super(message);
    }
}
