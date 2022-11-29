package mainPackage;

import action.Action;
import action.DrawAction;
import action.Invoker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import serializedIO.FileIO;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import shapes.ShapeInterface;

public class WindowController implements Initializable {

    @FXML
    private Pane drawingPane;
    @FXML
    private ColorPicker colorPickerInternal;
    @FXML
    private ColorPicker colorPickerContour;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button lineSegmentBtn;
    @FXML
    private Button rectangleBtn;
    @FXML
    private Button ellipseBtn;

    private Invoker invoker;
    private ShapeInterface selectedShape;
    private double initialX;
    private double initialY;
    private double finalX;
    private double finalY;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private FileIO shapesInputOutput;
    private Color internalColor;
    private Color contourColor;
    private Action action;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param url The location used to resolve relative paths for the root object, or
     *            null if the location is not known.
     * @param rb  The resources used to localize the root object, or null if
     *            the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawingPane.setDisable(true);
        
        this.invoker = new Invoker();
        
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        colorPickerInternal.setValue(Color.TRANSPARENT);
        colorPickerContour.setValue(Color.BLACK);
        
        fileChooser = new FileChooser();
        extensionFilter = new FileChooser.ExtensionFilter("Binary File (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extensionFilter);
        shapesInputOutput = new FileIO(this.drawingPane);
    }

    /**
     * Called when the segment button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        selectedShape = new LineShape();
        drawingPane.setDisable(false);
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), drawingPane);
    }

    /**
     * Called when the rectangle button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void rectangleSelection(ActionEvent event) {
        selectedShape = new RectangleShape();
        drawingPane.setDisable(false);
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), drawingPane);
    }

    /**
     * Called when the ellipse button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void ellipseSelection(ActionEvent event) {
        selectedShape = new EllipseShape();
        drawingPane.setDisable(false);
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), drawingPane);
    }

    /**
     * Called when the save button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void saveWindow(ActionEvent event) {
        fileChooser.setTitle("Save File");
        file = fileChooser.showSaveDialog(drawingPane.getScene().getWindow());
        shapesInputOutput.save(file);
    }

    /**
     * Called when the load button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void loadWindow(ActionEvent event) {
        fileChooser.setTitle("Open File");
        file = fileChooser.showOpenDialog(drawingPane.getScene().getWindow());
        shapesInputOutput.load(file);
    }

    /**
     * Called when the mouse is released from the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMouseReleased(MouseEvent event) {
        action.onMouseReleased(event);
    }

    /**
     * Called when the mouse is being dragged on the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMouseDragged(MouseEvent event) {
        action.onMouseDragged(event);
    }

    /**
     * Called when the drawing pane is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMousePressed(MouseEvent event) {
        invoker.execute(action);
        action.onMousePressed(event);
        
    }

}
