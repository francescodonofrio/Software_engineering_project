/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializedIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 *
 * @author vince
 */
public class FileIO {
    
    Pane drawingPane;

    public FileIO(Pane drawingPane) {
        this.drawingPane = drawingPane;        
    }
    
    public void save(File file) {
        if(file == null) return;
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath())))){
        
            ObservableList list = drawingPane.getChildren();
            out.writeInt(list.size());
            for(int i = 0 ; i < list.size(); i++){
                out.writeObject((javafx.scene.shape.Shape)list.get(i));
            }
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void load(File file) {
        if(file == null) return;
        drawingPane.getChildren().clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())))){
            
            int len = in.readInt();
            for(int i = 0; i < len; i++){
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
