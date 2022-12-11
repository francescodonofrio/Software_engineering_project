package exceptions;

public class ShapeNullException extends Exception {

    /**
     * Creates a new instance of <code>ShapeNullException</code> without
     * detail message.
     */
    public ShapeNullException() {
    }

    /**
     * Constructs an instance of <code>ShapeNullException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ShapeNullException(String msg) {
        super(msg);
    }
}   