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
import javafx.scene.Node;
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
        lineSegmentBtn.setStyle("-fx-border-color: #ff0000; -fx-border-color : black;");
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
        rectangleBtn.setStyle("-fx-border-color: #ff0000; -fx-border-color : black;");
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
        ellipseBtn.setStyle("-fx-border-color: #ff0000; -fx-border-color : black;");
        drawingPane.setDisable(false);
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
    private void DrawingWindowOnMouseReleased(MouseEvent event) {
        drawingPane.setDisable(true);
        lineSegmentBtn.setStyle("-fx-background-color : white; -fx-border-color : black");
        ellipseBtn.setStyle("-fx-background-color : white; -fx-border-color : black");
        rectangleBtn.setStyle("-fx-background-color : white; -fx-border-color : black");
    }

    /**
     * Called when the mouse is being dragged on the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void DrawingWindowOnMouseDragged(MouseEvent event) {
        finalX = event.getX();
        finalY = event.getY();
        selectedShape.setDim(initialX, initialY, finalX, finalY);
    }

    /**
     * Called when the drawing pane is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void DrawingWindowOnMousePressed(MouseEvent event) {
        internalColor = colorPickerInternal.getValue();
        contourColor = colorPickerContour.getValue();
        initialX = event.getX();
        initialY = event.getY();
        action = new DrawAction(selectedShape, initialX, initialY, internalColor, contourColor, drawingPane);
        invoker.execute(action);
    }

}
