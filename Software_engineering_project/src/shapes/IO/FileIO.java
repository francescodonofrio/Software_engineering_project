package shapes.IO;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileIO {

    private final Pane drawingPane;

    /**
     * Returns a new instance of FileIO, given the Pane to save or load
     * through the appropriate methods.
     *
     * @param drawingPane the drawing pane to save.
     */

    public FileIO(Pane drawingPane) {
        this.drawingPane = drawingPane;
    }

    /**
     * Perform a save operation, given a legal istance of file
     * where the shapes drawed in the actual pane will saved.
     *
     * @param file file to save to
     * @throws java.io.IOException
     */
    public void save(File file) throws IOException {
        if (file == null) return;
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(file.toPath())))) {
            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(new String[]{"red", "green", "blue", "opacity"}));
            encoder.writeObject(drawingPane.getChildren().toArray(new Node[0]));
        }

    }

    /**
     * Perform a load operation, given a legal istance of file,
     * where the shapes previously saved will loaded in the actual pane.
     *
     * @param file file to load from
     * @throws java.io.IOException
     */
    public void load(File file) throws IOException {
        if (file == null) return;
        drawingPane.getChildren().clear();
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(Files.newInputStream(file.toPath())))) {
            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            drawingPane.getChildren().setAll((Node[]) decoder.readObject());
        }
    }

}
