package shapes.util;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import shapes.ShapeAbstract;
import shapes.ShapeInterface;
import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class ShapesIO {

    /**
     * Perform a save operation on a file, given a legal instance of file
     * where the shapes drawn in the actual list will be saved.
     *
     * @param file file to save to
     * @param listInsertedShapes list in which are stored the shapes to save
     * @throws java.io.IOException
     */
    public void saveFile(File file, ObservableList<ShapeInterface> listInsertedShapes) throws IOException {
        if (file == null) return;
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(file.toPath())))) {
            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(listInsertedShapes.toArray(new ShapeInterface[0]));
        }

    }

    /**
     * Perform a load operation on a file, given a legal instance of file,
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

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(Files.newInputStream(file.toPath())))) {
            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
            listInsertedShapes.setAll((ShapeInterface[]) decoder.readObject());
        }

        ShapeAbstract.finalizeLoad();
    }

    /**
     * Perform a save operation on a stream byte
     * 
     * @param stream the ByteArrayOutputStream in which the shape will be saved
     * @param shape the shape to be saved
     * @throws Exception 
     */
    public void saveStreamByte(ByteArrayOutputStream stream, ShapeInterface shape) throws Exception{
        
        try (XMLEncoder encoder = new XMLEncoder(stream)) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(shape);
        }
    }
    
    /**
     * Perform a load operation on a stream byte
     * 
     * @param content an array storing a byte stream
     * @param shape an ArrayLinkedList<> in which the shapes stored in content will be saved 
     * @throws java.lang.Exception 
     */
    public void loadStreamByte(byte[] content, LinkedList<ShapeInterface> shape) throws Exception{
        
        try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(content))) {
            shape.add((ShapeInterface) decoder.readObject());
        }  
    }
    
}
