package shapes.IO;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import shapes.ShapeAbstract;
import shapes.ShapeInterface;

public class FileIO {
    
    private final ObservableList<ShapeInterface> listInsertedShapes;
    
    /**
     * Returns a new instance of FileIO, given the Pane to save or load 
     * through the appropriate methods.
     *
     * @param listInsertedShapes  the list containing the shapes to save
     */

    public FileIO( ObservableList<ShapeInterface> listInsertedShapes) {
        this.listInsertedShapes = listInsertedShapes;
    }
    
    /**
     * Perform a save operation, given a legal istance of file 
     * where the shapes drawed in the actual list will saved.
     * 
     * @param file      file to save to
     * @throws java.io.IOException
     */
    public void save(File file) throws IOException {
        if (file == null) return;
        try ( XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(file.toPath())))) {
            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.setPersistenceDelegate(Shape.class, new DefaultPersistenceDelegate(new String[]{"shape"}));
            encoder.writeObject(listInsertedShapes.toArray(new ShapeInterface[0]));
        }

    }
    
    /**
     * Perform a load operation, given a legal istance of file, 
     * where the shapes previously saved will loaded in the actual list.
     * 
     * @param file      file to load from
     * @throws java.io.IOException
     */
    public void load(File file) throws IOException {
        if (file == null) return;
        listInsertedShapes.clear();
        ShapeAbstract.resetCont();
        try ( XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(Files.newInputStream(file.toPath())))) {
            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
        listInsertedShapes.setAll((ShapeInterface[]) decoder.readObject());
        }
    }

}
