package mainPackage;

import action.Action;
import action.ChangeContourColorAction;
import action.ChangeInternalColorAction;
import action.DrawAction;
import action.Invoker;
import action.MoveAction;
import action.ResizeAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import shapes.EllipseShape;
import shapes.IO.FileIO;
import shapes.LineShape;
import shapes.RectangleShape;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import shapes.ShapeAbstract;
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
    private ObservableList<ShapeInterface> selectedInsertedShape;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private FileIO shapesInputOutput;
    private Action action;
    private ObservableList<ShapeInterface> listInsertedShapes;
    
    private ShapeInterface forFocusShape;

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
        
        invoker = new Invoker();
        selectedInsertedShape=FXCollections.observableArrayList();
        selectedInsertedShape.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while(change.next()){
                change.getRemoved().forEach(remItem -> {
                    remItem.setDefocus();
                });
                change.getAddedSubList().forEach(addItem -> {
                    addItem.setFocus();
                });
            }
        });

        action = new MoveAction(selectedInsertedShape,listInsertedShapes);

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
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
    }

    /**
     * Called when the rectangle button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void rectangleSelection(ActionEvent event) {
        selectedShape = new RectangleShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
    }

    /**
     * Called when the ellipse button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void ellipseSelection(ActionEvent event) {
        selectedShape = new EllipseShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
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
        this.action = new MoveAction(selectedInsertedShape,listInsertedShapes);
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

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void resizeButtonOnClick(ActionEvent actionEvent) {
        action = new ResizeAction(selectedInsertedShape[0]);
    }

    @FXML
    private void copyButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void focusOnMouseClick(MouseEvent event) {
        selectedInsertedShape.clear();
        selectedInsertedShape.add(shapesTable.getSelectionModel().getSelectedItem());
    private void shapesTableOnMouseClicked(MouseEvent event) {
        selectedInsertedShape[0]=shapesTable.getSelectionModel().getSelectedItem();
        selectedInsertedShape[0].setFocus();
    }

    @FXML
    private void changeContourColorOnClick(ActionEvent event) {
        action= new ChangeContourColorAction(selectedInsertedShape[0],colorPickerContour.valueProperty());
        invoker.execute(action,event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);

    }

    @FXML
    private void changeInternalColorOnClick(ActionEvent event) {
        action= new ChangeInternalColorAction(selectedInsertedShape[0],colorPickerInternal.valueProperty());
        invoker.execute(action,event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }
}
