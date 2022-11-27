package serializedIO;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Shape;

public class FileIO {

    private final Pane drawingPane;
    
    /**
     * Returns a new instance of FileIO, given the Pane to save or load 
     * through the appropriate methods.
     *
     * @param drawingPane         the drawing pane to save.
     */

    public FileIO(Pane drawingPane) {
        this.drawingPane = drawingPane;
    }
    
    /**
     * Perform a save operation, given a legal istance of file 
     * where the shapes drawed in the actual pane will saved.
     * 
     * @param file      file to save to
     */
    public void save(File file) {
        if (file == null) return;
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath())))) {
        
            ObservableList list = drawingPane.getChildren();
            int len = list.size();
            out.writeInt(len);
            for (int i = 0; i < len; i++) {
                out.writeObject(list.get(i));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Perform a load operation, given a legal istance of file, 
     * where the shapes previously saved will loaded in the actual pane.
     * 
     * @param file      file to load from
     */
    public void load(File file) {
        if (file == null) return;
        drawingPane.getChildren().clear();
        Shape temp;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())))) {

            int len = in.readInt();
            for (int i = 0; i < len; i++) {
                temp = (Shape) in.readObject();
                drawingPane.getChildren().add(temp);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
