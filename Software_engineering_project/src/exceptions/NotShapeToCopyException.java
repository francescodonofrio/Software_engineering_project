/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

public class NotShapeToCopyException extends Exception {

    /**
     * Creates a new instance of <code>NotShapeToCopyException</code> without
     * detail message.
     */
    public NotShapeToCopyException() {
    }

    /**
     * Constructs an instance of <code>NotShapeToCopyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotShapeToCopyException(String msg) {
        super(msg);
    }
}
