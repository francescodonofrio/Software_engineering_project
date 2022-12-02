package shapes.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;
import javafx.scene.shape.Line;

public class Clipboard {
    private ShapeInterface value;
    private static Clipboard instance=null;

    /**
     * Creates a new instance of Clipboard
     */
    private Clipboard(){
        this.instance=this;
    }

    /**
     * Returns a reference to the clipboard, instantiating it if necessary
     * @return a reference to the clipboard
     */
    public static Clipboard getClipboard() {
        if(instance==null)
            return new Clipboard();
        return instance;
    }

    /**
     * Returns the shape memorized in the clipboard
     * @return the memorized shape
     */
    public ShapeInterface getValue(){
        System.out.println("getValue:");
        System.out.println(value);
        return value;
    }

    /**
     * Saves a new shape into the clipboard
     * @param value the new shape
     */
    public void setValue(ShapeInterface value){
        System.out.println("setValue:");
        System.out.println(value);
        Shape copiedShape = value.getShape();
        
        Shape empty = new Line();
//        empty.setLayoutX(copiedShape.getLayoutX());
//        empty.setLayoutY(copiedShape.getLayoutY());
        
        Shape change = Shape.union(copiedShape, empty);
        change.setStroke(Paint.valueOf("0f0f0f"));
        
        try {
            this.value= value.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Clipboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.value.setShape(change);
    }
}
