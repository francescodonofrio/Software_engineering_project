package shapes.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Clipboard {
    private String value;
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
        ShapeInterface shape;

        try(XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(value.getBytes()))) {
            shape=(ShapeInterface) decoder.readObject();
            shape.setName(shape.getName()+" - Copia");
        }catch(IllegalArgumentException ex) {
            return null;
        }
        return shape;
    }

    /**
     * Saves a new shape into the clipboard
     * @param value the new shape
     */
    public void setValue(ShapeInterface value){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        try(XMLEncoder encoder = new XMLEncoder(stream)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.setPersistenceDelegate(Shape.class, new DefaultPersistenceDelegate(new String[]{"shape"}));
            encoder.writeObject(value);
        }catch(IllegalArgumentException ex){
            this.value=null;
            return;
        }

        this.value=new String(stream.toByteArray());
    }
}
