/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.DrawAction;
import action.Invoker;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.Shape;

public class WindowController implements Initializable {

    @FXML
    private Pane drawingPane;
    @FXML
    private Button lineSegmentBtn;
    @FXML
    private Button rectangleBtn;
    @FXML
    private Button ellipseBtn;
    
    private final Invoker invoker = new Invoker();
    private Shape selectedShape;
    @FXML
    private ColorPicker colorPickerInternal;
    @FXML
    private ColorPicker colorPickerContour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawingPane.setDisable(true);
    }    

    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        selectedShape = new LineShape();
        drawingPane.setDisable(false);
    }

    @FXML
    private void rectangleSelection(ActionEvent event) {
        selectedShape = new RectangleShape();
        drawingPane.setDisable(false);
    }

    @FXML
    private void ellipseSelection(ActionEvent event) {
        selectedShape = new EllipseShape();
        drawingPane.setDisable(false);
    }

    @FXML
    private void DrawingWindowOnMouseClick(MouseEvent event) {
        double X = event.getX();
        double Y = event.getY();
        Color internal = colorPickerInternal.getValue();
        Color contour = colorPickerContour.getValue();
        Action action = new DrawAction(selectedShape, X, Y, internal, contour, drawingPane);
        invoker.execute(action);
        drawingPane.setDisable(true);
    }

    @FXML
    private void saveWindow(ActionEvent event) {
        
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Binary File (*.bin)", "*.bin");
        chooser.getExtensionFilters().add(ext);
        chooser.setTitle("Save File");
        File file = chooser.showSaveDialog(drawingPane.getScene().getWindow());
        if(file == null) return;
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath())))){
        
            ObservableList list = drawingPane.getChildren();
            out.writeInt(list.size());
            for(int i = 0 ; i < list.size(); i++){
                out.writeObject((javafx.scene.shape.Shape)list.get(i));
            }
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadWindow(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Binary File (*.bin)", "*.bin");
        chooser.getExtensionFilters().add(ext);
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(drawingPane.getScene().getWindow());
        if(file == null) return;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())))){
            
            int len = in.readInt();
            for(int i = 0; i < len; i++){
                javafx.scene.shape.Shape temp = (javafx.scene.shape.Shape) in.readObject();
                drawingPane.getChildren().add(temp);
            }

        } catch (FileNotFoundException ex) {    
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
