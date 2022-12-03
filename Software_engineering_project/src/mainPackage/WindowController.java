package mainPackage;

import action.*;
import exceptions.NotShapeToCopyException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;
import shapes.util.Clipboard;
import shapes.util.FileIO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML
    private ContextMenu contextMenuTableView;

    private Invoker invoker;
    private ShapeInterface selectedShape;
    private ObservableList<ShapeInterface> selectedInsertedShape;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private FileIO shapesInputOutput;
    private Action action;
    private ObservableList<ShapeInterface> listInsertedShapes;
    private Clipboard clipboard;

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

        selectedInsertedShape = FXCollections.observableArrayList();
        selectedInsertedShape.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getRemoved().forEach(remItem -> {
                    if (remItem != null) {
                        remItem.setFocus(false);
                        int index = listInsertedShapes.indexOf(remItem);
                        shapesTable.getSelectionModel().clearSelection(index);
                    }
                });
                change.getAddedSubList().forEach(addItem -> {
                    if (addItem != null) {
                        addItem.setFocus(true);
                        int index = listInsertedShapes.indexOf(addItem);
                        shapesTable.getSelectionModel().select(index);
                        colorPickerContour.setValue((Color) addItem.getShape().getStroke());
                        colorPickerInternal.setValue((Color) addItem.getShape().getFill());
                    }
                });
            }
        });

        listInsertedShapes = FXCollections.observableArrayList();
        listInsertedShapes.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getRemoved().forEach(remItem -> {
                    drawingPane.getChildren().remove(remItem.getShape());
                });
                change.getAddedSubList().forEach(addItem -> {
                    drawingPane.getChildren().add(addItem.getShape());
                });
            }
        });

        contextMenuTableView.getItems().forEach(menuItem -> {
            menuItem.disableProperty().bind(Bindings.isEmpty(selectedInsertedShape));
        });

        selectedShape = new LineShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);


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

        clipboard = Clipboard.getClipboard();
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
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
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
     * @param actionEvent
     */
    @FXML
    public void resizeButtonOnClick(ActionEvent actionEvent) {
        action = new ResizeAction(selectedInsertedShape.get(0));
    }

    @FXML
    private void copyButtonOnClick(ActionEvent event) {
        ShapeInterface copiedShape = shapesTable.getSelectionModel().getSelectedItem();
        
        try {
            this.action = new CopyAction(clipboard, copiedShape);
        } catch (NotShapeToCopyException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        invoker.execute(this.action, event);
        
        this.action = new PasteAction(clipboard, listInsertedShapes);
    }

    @FXML
    private void shapesTableOnMouseClicked(MouseEvent event) {
        ShapeInterface lastSelectedShape = shapesTable.getSelectionModel().getSelectedItem();
        selectedInsertedShape.clear();

        if (lastSelectedShape != null)
            selectedInsertedShape.add(lastSelectedShape);

    }

    @FXML
    private void changeInternalColorOnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ChangeInternalColorAction(selectedInsertedShape.get(0), colorPickerInternal.valueProperty());
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    @FXML
    private void changeContourColorOnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ChangeContourColorAction(selectedInsertedShape.get(0), colorPickerContour.valueProperty());
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }
}
