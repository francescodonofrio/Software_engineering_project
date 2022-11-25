package serializedIO;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {

    Pane drawingPane;

    public FileIO(Pane drawingPane) {
        this.drawingPane = drawingPane;
    }

    public void save(File file) {
        if (file == null) return;
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath())))) {

            ObservableList list = drawingPane.getChildren();
            out.writeInt(list.size());
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load(File file) {
        if (file == null) return;
        drawingPane.getChildren().clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())))) {

            int len = in.readInt();
            for (int i = 0; i < len; i++) {
                javafx.scene.shape.Shape temp = (javafx.scene.shape.Shape) in.readObject();
                drawingPane.getChildren().add(temp);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
