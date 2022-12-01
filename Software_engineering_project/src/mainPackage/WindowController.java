package mainPackage;

import action.Action;
import action.DrawAction;
import action.Invoker;
import action.ResizeAction;
import action.MoveAction;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import shapes.EllipseShape;
import shapes.IO.FileIO;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView<ShapeInterface> shapesTable;
    @FXML
    private TableColumn<ShapeInterface, String> shapesColumn;

    private Invoker invoker;
    private ShapeInterface selectedShape;
    private Shape[] selectedInsertedShape;

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
    private ObservableList<ShapeInterface> listInsertedShapes;

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
        
        listInsertedShapes = FXCollections.observableArrayList();
        listInsertedShapes.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while(change.next()){
                change.getRemoved().forEach(remItem -> {
                    drawingPane.getChildren().remove(remItem.getShape());
                });
                change.getAddedSubList().forEach(addItem -> {
                    drawingPane.getChildren().add(addItem.getShape());
                });
            }
        });

        shapesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shapesTable.setItems(listInsertedShapes);

        this.selectedInsertedShape=new Shape[1];
        this.action = new MoveAction(selectedInsertedShape);

        shapesTable.setItems(drawingPane.getChildren());
        shapesColumn.setCellValueFactory((CellDataFeatures<Node, String> p) -> new ReadOnlyObjectWrapper(p.getValue().toString().split("\\[")[0]) // p.getValue() returns the Person instance for a particular TableView row
        );

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        colorPickerInternal.setValue(Color.TRANSPARENT);
        colorPickerContour.setValue(Color.BLACK);
        
        fileChooser = new FileChooser();
        extensionFilter = new FileChooser.ExtensionFilter("XML File (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        shapesInputOutput = new FileIO(this.listInsertedShapes);
    }

    /**
     * Called when the segment button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        selectedShape = new LineShape();
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
        try {
            shapesInputOutput.save(file);
        } catch (IOException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            shapesInputOutput.load(file);
        } catch (IOException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Called when the mouse is released from the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMouseReleased(MouseEvent event) {
        invoker.executeOnMouseReleased(action, event);

        // Here we reset the default action to move action
        this.action = new MoveAction(selectedInsertedShape);
    }

    /**
     * Called when the mouse is being dragged on the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMouseDragged(MouseEvent event) {
        invoker.executeOnMouseDragged(action, event);
    }

    /**
     * Called when the drawing pane is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMousePressed(MouseEvent event) {
        invoker.execute(action, event);
    }

}
