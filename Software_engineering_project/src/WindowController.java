import action.Action;
import action.DrawAction;
import action.Invoker;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
import shapes.Shape;

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
    private Shape selectedShape;
    private double initialDim1;
    private double initialDim2;
    private double finalDim1;
    private double finalDim2;
    
    /**
    * Called to initialize a controller after its root element has been
    * completely processed.
    *
    * @param url
    * The location used to resolve relative paths for the root object, or
    * null if the location is not known.
    *
    * @param rb
    * The resources used to localize the root object, or null if
    * the root object was not localized.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawingPane.setDisable(true);
        this.invoker = new Invoker();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }    
    
    /**
     * Executed when the segment button is clicked
     * @param event
     */
    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        selectedShape = new LineShape();
        drawingPane.setDisable(false);
    }
    
    /**
     * Executed when the segment button is clicked
     * @param event 
     */
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
        FileIO out = new FileIO(this.drawingPane);
        out.save(file);          
    }

    @FXML
    private void loadWindow(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Binary File (*.bin)", "*.bin");
        chooser.getExtensionFilters().add(ext);
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(drawingPane.getScene().getWindow());
        FileIO in = new FileIO(this.drawingPane);
        in.load(file);
    }
    
    @FXML
    private void DrawingWindowOnMouseReleased(MouseEvent event) {
        drawingPane.setDisable(true);
    }

    @FXML
    private void DrawingWindowOnMouseDragged(MouseEvent event) {
        finalDim1 = event.getX();
        finalDim2 = event.getY();
        selectedShape.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
    }

    @FXML
    private void DrawingWindowOnMousePressed(MouseEvent event) {
        Color internal = colorPickerInternal.getValue();
        Color contour = colorPickerContour.getValue();
        initialDim1 = event.getX();
        initialDim2 = event.getY();
        Action action = new DrawAction(selectedShape, initialDim1, initialDim2, internal, contour, drawingPane);
        invoker.execute(action);
    }
    
}
