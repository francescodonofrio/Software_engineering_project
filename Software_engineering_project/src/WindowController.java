/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.DrawAction;
import action.Invoker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private Menu saveBtn;
    @FXML
    private Menu loadBtn;
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
        Action action = new DrawAction(selectedShape, X, Y, internal, drawingPane);
        invoker.execute(action);
        drawingPane.setDisable(true);
    }

    @FXML
    private void saveWindow(ActionEvent event) {
        
    }

    @FXML
    private void loadWindow(ActionEvent event) {
    }
    
}
