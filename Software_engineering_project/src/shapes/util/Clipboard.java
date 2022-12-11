package shapes.util;

import shapes.ShapeInterface;
import java.io.ByteArrayOutputStream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.LinkedList;

public class Clipboard {
    private static Clipboard instance = null;
    private byte[] content;
    private final BooleanProperty hasContent;
    
    /**
     * Creates a new instance of Clipboard
     */
    private Clipboard() {
        Clipboard.instance = this;
        hasContent = new SimpleBooleanProperty();
        hasContent.set(false);
    }

    /**
     * Returns a reference to the clipboard, instantiating it if necessary
     *
     * @return a reference to the clipboard
     */
    public static Clipboard getClipboard() {
        if (instance == null)
            return new Clipboard();
        return instance;
    }

    /**
     * Returns the shape memorized in the clipboard
     *
     * @return the memorized shape
     */
    public ShapeInterface getContent() {
        LinkedList<ShapeInterface> shape = new LinkedList<>();
        try {
            ShapesIO.loadStreamByte(content, shape);
            shape.get(0).setName(shape.get(0).getName() + " - Copy");
            shape.get(0).getShape().setEffect(null);  
            return shape.removeFirst();
        } catch (Exception ex) {
            return null;
        }
        
    }

    /**
     * Saves a new shape into the clipboard
     *
     * @param shape the shape to be save on the clipboard
     */
    public void setContent(ShapeInterface shape){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        try {
            ShapesIO.saveStreamByte(stream, shape);
            this.content = stream.toByteArray();
        } catch (Exception ex) {
            content = null;
        }
        hasContent.set(true);

    }
    
    /**
     * Return a property to know if the clipboard has a content
     *
     * @return hasContent 
     */
    public BooleanProperty hasContent() {
        return hasContent;
    }
    
}
