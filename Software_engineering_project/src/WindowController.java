/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.DrawAction;
import action.Invoker;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.Shape;

/**
 *
 * @author franc
 */
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
        // TODO
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
        File file = chooser.showSaveDialog(new Stage());
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath())))){
                out.writeObject(drawingPane);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadWindow(ActionEvent event) {
    }
    
}
