/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

public class NotShapeToCutException extends Exception {

    /**
     * Creates a new instance of <code>NotShapeToCutException</code> without
     * detail message.
     */
    public NotShapeToCutException() {
    }

    /**
     * Constructs an instance of <code>NotShapeToCutException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotShapeToCutException(String msg) {
        super(msg);
    }
}
