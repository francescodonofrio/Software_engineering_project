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

    private Invoker invoker;
    private ShapeInterface selectedShape;
    private double initialDim1;
    private double initialDim2;
    private double finalDim1;
    private double finalDim2;
    private FileChooser chooser;
    private FileChooser.ExtensionFilter ext;
    private File file;
    private FileIO inputOutput;
    private Color internal;
    private Color contour;
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
        
        chooser = new FileChooser();
        ext = new FileChooser.ExtensionFilter("Binary File (*.bin)", "*.bin");
        chooser.getExtensionFilters().add(ext);
        inputOutput = new FileIO(this.drawingPane);
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
    }

    /**
     * Called when the save button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void saveWindow(ActionEvent event) {
        chooser.setTitle("Save File");
        file = chooser.showSaveDialog(drawingPane.getScene().getWindow());
        inputOutput.save(file);
    }

    /**
     * Called when the load button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void loadWindow(ActionEvent event) {
        chooser.setTitle("Open File");
        file = chooser.showOpenDialog(drawingPane.getScene().getWindow());
        inputOutput.load(file);
    }

    /**
     * Called when the mouse is released from the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void DrawingWindowOnMouseReleased(MouseEvent event) {
        drawingPane.setDisable(true);
    }

    /**
     * Called when the mouse is being dragged on the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void DrawingWindowOnMouseDragged(MouseEvent event) {
        finalDim1 = event.getX();
        finalDim2 = event.getY();
        selectedShape.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
    }

    /**
     * Called when the drawing pane is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void DrawingWindowOnMousePressed(MouseEvent event) {
        internal = colorPickerInternal.getValue();
        contour = colorPickerContour.getValue();
        initialDim1 = event.getX();
        initialDim2 = event.getY();
        action = new DrawAction(selectedShape, initialDim1, initialDim2, internal, contour, drawingPane);
        invoker.execute(action);
    }

}
