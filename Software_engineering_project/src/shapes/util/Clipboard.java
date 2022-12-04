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
    private static Clipboard instance = null;
    private byte[] content;

    /**
     * Creates a new instance of Clipboard
     */
    private Clipboard() {
        Clipboard.instance = this;
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
        ShapeInterface shape;

        try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(content))) {
            shape = (ShapeInterface) decoder.readObject();
            shape.setName(shape.getName() + " - Copy");
        } catch (IllegalArgumentException ex) {
            return null;
        }
        shape.getShape().setEffect(null);
        return shape;
    }

    /**
     * Saves a new shape into the clipboard
     *
     * @param content the new shape
     */
    public void setContent(ShapeInterface content) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try (XMLEncoder encoder = new XMLEncoder(stream)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(content);
        } catch (IllegalArgumentException ex) {
            this.content = null;
            return;
        }

        this.content = stream.toByteArray();
    }
}
