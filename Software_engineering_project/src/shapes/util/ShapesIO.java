package shapes.util;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import shapes.ShapeAbstract;
import shapes.ShapeInterface;
import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;


public class ShapesIO {

    /**
     * Performs a save operation on a file, given a legal instance of file
     * where the shapes drawn in the actual list will be saved.
     *
     * @param file file to save to
     * @param listInsertedShapes list in which are stored the shapes to save
     * @throws java.io.IOException
     */
    public void saveFile(File file, ObservableList<ShapeInterface> listInsertedShapes) throws IOException {
        if (file == null) return;
        this.encode(new BufferedOutputStream(Files.newOutputStream(file.toPath())),listInsertedShapes.toArray(new ShapeInterface[0]));
    }

    /**
     * Performs a load operation on a file, given a legal instance of file,
     * where the shapes previously saved will be loaded in the actual list.
     *
     * @param file file to load from
     * @param listInsertedShapes the list in which store the shapes loaded
     * @throws java.io.IOException
     */
    public void loadFile(File file, ObservableList<ShapeInterface> listInsertedShapes) throws IOException {
        if (file == null) return;
        listInsertedShapes.clear();
        ShapeAbstract.resetCont();
        ShapeAbstract.initializeLoad();

        listInsertedShapes.addAll((ShapeInterface[]) this.decode(new BufferedInputStream(Files.newInputStream(file.toPath()))));

        ShapeAbstract.finalizeLoad();
    }

    /**
     * Performs a save operation on a stream byte
     *
     * @param stream the ByteArrayOutputStream in which the shape will be saved
     * @param shape the shape to be saved
     * @throws Exception
     */
    public void saveStreamByte(ByteArrayOutputStream stream, ShapeInterface shape) throws Exception{
        shape.getShape().setEffect(null);
        this.encode(stream,shape);
    }

    /**
     * Performs a load operation on a stream byte
     *
     * @param content an array storing a byte stream
     * @param shape an ArrayLinkedList<> in which the shapes stored in content will be saved
     * @throws java.lang.Exception
     */
    public void loadStreamByte(byte[] content, LinkedList<ShapeInterface> shape) throws Exception{
        shape.add((ShapeInterface) this.decode(new ByteArrayInputStream(content)));
    }

    /**
     * Writes the XML equivalent of an object to a stream
     * @param stream the output stream
     * @param o the object to save
     */
    private void encode(OutputStream stream, Object o){
        try (XMLEncoder encoder = new XMLEncoder(stream)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(o);
        }
    }

    /**
     * Loads an object decoding its XML representation from a stream
     * @param stream the input stream
     * @return the read object
     */
    private Object decode(InputStream stream){
        try (XMLDecoder decoder = new XMLDecoder(stream)) {
            return decoder.readObject();
        }
    }

}
