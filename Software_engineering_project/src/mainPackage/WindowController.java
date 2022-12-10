package mainPackage;

import action.*;
import com.sun.glass.ui.Screen;
import exceptions.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;
import shapes.util.Clipboard;
import shapes.util.ShapesIO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.input.SwipeEvent;
import javafx.scene.shape.Rectangle;
import shapes.TextShape;
import shapes.util.Grid;

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
    @FXML
    private MenuItem resizeItem;
    @FXML
    private Button undoBtn;
    @FXML
    private Button lessZoomBtn;
    @FXML
    private Button moreZoomBtn;
    @FXML
    private ContextMenu contextMenuDrawingPane;
    @FXML
    private MenuItem pasteMenuItem;
    @FXML
    private Button polygonBtn1;
    @FXML
    private Group drawingPaneAndGrid;
    
    private Invoker invoker;
    private ShapeInterface selectedShape;
    private ObservableList<ShapeInterface> selectedInsertedShape;
    private FileChooser fileChooser;
    private File file;
    private ShapesIO shapesInputOutput;
    private Action action;
    private ObservableList<ShapeInterface> listInsertedShapes;
    private Clipboard clipboard;
    private MouseEvent rightClickPane;
    private SimpleObjectProperty<Integer> zoomLevel;
    private ArrayDeque<Double> queue;
    private final double zoomOffset = 0.2;
    private final SimpleBooleanProperty disableClick = new SimpleBooleanProperty(false);
    private Grid grid;
    @FXML
    private CheckMenuItem gridCheckItem;
    @FXML
    private Slider gridSlider;
    @FXML
    private Button textBtn;
    @FXML
    private Label mainLabel;
    @FXML
    private MenuButton buttonTextSize;
    
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

        queue = new ArrayDeque<>();
        
        invoker = new Invoker();
        undoBtn.disableProperty().bind(invoker.emptyQueueProperty());

        zoomLevel = new SimpleObjectProperty<>();
        zoomLevel.set(1);
        lessZoomBtn.disableProperty().bind(zoomLevel.isEqualTo(1));
        moreZoomBtn.disableProperty().bind(zoomLevel.isEqualTo(9));

        selectedInsertedShape = FXCollections.observableArrayList();
        selectedInsertedShape.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getRemoved().forEach(remItem -> {
                    if (remItem != null) {
                        remItem.setFocus(false);
                        int index = listInsertedShapes.indexOf(remItem);
                        shapesTable.getSelectionModel().clearSelection(index);
                        resetMainLabel();
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
                change.getRemoved().forEach(remItem -> drawingPane.getChildren().remove(remItem.getShape()));
                change.getAddedSubList().forEach(addItem -> drawingPane.getChildren().add(addItem.getShape()));
            }
        });

        contextMenuTableView.getItems().forEach(menuItem -> menuItem.disableProperty().bind(Bindings.isEmpty(selectedInsertedShape)));

        selectedShape = new LineShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);


        shapesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shapesTable.setItems(listInsertedShapes);

        colorPickerInternal.setValue(Color.TRANSPARENT);
        colorPickerContour.setValue(Color.BLACK);

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML File (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        shapesInputOutput = new ShapesIO();

        clipboard = Clipboard.getClipboard();

        pasteMenuItem.disableProperty().bind(clipboard.hasContent().not());
        
        drawingPane.setPrefSize(Screen.getMainScreen().getWidth(), Screen.getMainScreen().getHeight());
        drawingPane.setClip(new Rectangle (0,0, Screen.getMainScreen().getWidth(),Screen.getMainScreen().getHeight()));
        grid = new Grid(drawingPane, 1, false);
        drawingPaneAndGrid.getChildren().add(grid);
        
        selectedInsertedShape.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getAddedSubList().forEach(addItem -> {
                    if (addItem instanceof TextShape)
                        buttonTextSize.setDisable(false);
                    else
                        buttonTextSize.setDisable(true);
                    });
                }
        });
        
        
        
    }

    /**
     * Called when the segment button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        mainLabel.setText("Draw a line on paper with left click");
        selectedShape = new LineShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
        disableClick.set(false);
    }

    /**
     * Called when the rectangle button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void rectangleSelection(ActionEvent event) {
        mainLabel.setText("Draw a rectangle on paper with left click");
        selectedShape = new RectangleShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
        disableClick.set(false);
    }

    /**
     * Called when the ellipse button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void ellipseSelection(ActionEvent event) {
        mainLabel.setText("Draw an ellipse on paper with left click");
        selectedShape = new EllipseShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
        disableClick.set(false);
    }

    /**
     * Called when the polygon button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void polygonSelection(ActionEvent event) {
        if (disableClick.get()) {
            resetMainLabel();
            disableClick.set(false);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        } else {
            mainLabel.setText("Draw a polygon on paper with multiples left click, click again Polygon button for ending the draw");
            disableClick.set(true);
            action = new DrawPolygonAction(colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes, disableClick);
        }

    }

    /**
     * Called when the save button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void saveWindow(ActionEvent event) {
        fileChooser.setTitle("Save File");
        selectedInsertedShape.clear();
        file = fileChooser.showSaveDialog(drawingPane.getScene().getWindow());
        try {
            shapesInputOutput.saveFile(file, listInsertedShapes);
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
            shapesInputOutput.loadFile(file, listInsertedShapes);
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
        if (event.getButton() == MouseButton.SECONDARY)
            rightClickPane = event;
        else {
            invoker.executeOnMouseReleased(action, event);
            if (!disableClick.get()) {
                // Here we reset the default action to move action if a polygon has been inserted
                action = new MoveAction(selectedInsertedShape, listInsertedShapes);
                resetMainLabel();
            }
        }
    }

    /**
     * Called when the mouse is being dragged on the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMouseDragged(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY)
            rightClickPane = event;
        else{
            try {
                invoker.executeOnMouseDragged(action, event);
            } catch (Exception ex) {
                Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
            resetMainLabel();
        }
    }

    /**
     * Called when the drawing pane is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void drawingWindowOnMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY)
            rightClickPane = event;
        else{
            invoker.execute(action, event);
            resetMainLabel();
        }
    }

    /**
     * Called when the MenuItem Resize id being clicked
     *
     * @param actionEvent the event of the click
     */
    @FXML
    public void resizeButtonOnClick(ActionEvent actionEvent) {
        try {
            mainLabel.setText("Resize selected shape with left click or drag and drop");
            action = new ResizeAction(selectedInsertedShape.get(0));
            disableClick.set(false);
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    /**
     * Called when the MenuItem Copy is being clicked
     *
     * @param event the event of the click
     */
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
        resetMainLabel();
    }

    /**
     * Called when the table of inserted shapes is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void shapesTableOnMouseClicked(MouseEvent event) {
        ShapeInterface lastSelectedShape = shapesTable.getSelectionModel().getSelectedItem();
        selectedInsertedShape.clear();

        if (lastSelectedShape != null)
            selectedInsertedShape.add(lastSelectedShape);

    }

    /**
     * Called when the internal color picker is being used
     *
     * @param event the event of the use
     */
    @FXML
    private void changeInternalColorOnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ChangeInternalColorAction(selectedInsertedShape.get(0), colorPickerInternal.valueProperty());
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    /**
     * Called when the contour color picker is being used
     *
     * @param event the event of the use
     */
    @FXML
    private void changeContourColorOnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ChangeContourColorAction(selectedInsertedShape.get(0), colorPickerContour.valueProperty());
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    /**
     * Called when the MenuItem Cut is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void cutButtonOnClick(ActionEvent event) {
        ShapeInterface cuttedShape = shapesTable.getSelectionModel().getSelectedItem();

        try {
            this.action = new CutAction(clipboard, listInsertedShapes, cuttedShape);
        } catch (NotShapeToCutException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        invoker.execute(this.action, event);
    }

    /**
     * Called when one of the shapes button is being released
     *
     * @param event the event of the release
     */
    @FXML
    private void shapesButtonsOnMouseReleased(MouseEvent event) {
        selectedInsertedShape.clear();
    }

    /**
     * Called when the undo button is being clicked
     *
     * @param event the event of the click
     * @throws NoActionsException if there are no undoable actions
     */
    @FXML
    private void undoBtnOnAction(ActionEvent event) throws NoActionsException, NotExecutedActionException {
        invoker.undo();
        if (disableClick.get()) {
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
            disableClick.set(false);
        }
    }

    /**
     * Called when the MenuItem Paste is being clicked.
     *
     * @param event the event of the click
     */
    @FXML
    private void pasteButtonOnClick(ActionEvent event) {
        this.action = new PasteAction(clipboard, listInsertedShapes);
        invoker.execute(action, rightClickPane);
        // Here we reset the default action to move action
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }

    /**
     * Executed when the less zoom button is clicked.
     * It decreases the level of zoom of the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void lessZoomBtnOnAction(ActionEvent event) {
        drawingPane.getTransforms().remove(drawingPane.getTransforms().size() - 1);
        drawingPane.setPrefHeight(queue.removeLast());
        drawingPane.setPrefWidth(queue.removeLast());
        zoomLevel.set(zoomLevel.getValue() - 1);
        
        drawingPaneAndGrid.getChildren().remove(grid);
        grid = new Grid(drawingPane, gridSlider.getValue(), true);
        drawingPaneAndGrid.getChildren().add(grid);
    }

    /**
     * Executed when the more zoom button is clicked.
     * It increases the level of zoom of the drawing pane
     *
     * @param event the event of the click
     */
    @FXML
    private void moreZoomBtnOnAction(ActionEvent event) {
        Scale newScale = new Scale();
        newScale.setX(drawingPane.getScaleX() + zoomOffset);
        newScale.setY(drawingPane.getScaleY() + zoomOffset);
        newScale.setPivotX(drawingPane.getScaleX());
        newScale.setPivotY(drawingPane.getScaleY());
        drawingPane.getTransforms().add(newScale);
        queue.add(drawingPane.getPrefWidth());
        queue.add(drawingPane.getPrefHeight());
        drawingPane.setPrefWidth(drawingPane.getPrefWidth() + drawingPane.getPrefWidth() * zoomOffset);
        drawingPane.setPrefHeight(drawingPane.getPrefHeight() + drawingPane.getPrefHeight() * zoomOffset);
        zoomLevel.set(zoomLevel.getValue() + 1);
        
        drawingPaneAndGrid.getChildren().remove(grid);
        grid = new Grid(drawingPane, gridSlider.getValue(), true);
        drawingPaneAndGrid.getChildren().add(grid);
    }

    /**
     * Called when the toggle grid button is being clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void toggleGrid(ActionEvent event) {
        grid.setVisible(gridCheckItem.isSelected());
    }

    @FXML
    private void toFrontOnClick(ActionEvent event) {
        action = new ToFrontAction(selectedInsertedShape.get(0), drawingPane.getChildren());
        invoker.execute(action, event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }

    @FXML
    private void toBackOnClick(ActionEvent event) {
        action = new ToBackAction(selectedInsertedShape.get(0), drawingPane.getChildren());
        invoker.execute(action, event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }
    
    @FXML
    private void rotateButtonOnClick(ActionEvent event) {
        try{
            mainLabel.setText("Rotate the selected shape with left click or drag and drop on ");
            action = new RotateAction(selectedInsertedShape.get(0));
            disableClick.set(false);
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }
    
    @FXML
    private void gridSliderOnMouse(MouseEvent event) {
        if(gridSlider.getValue()>0)
            grid.resize(gridSlider.getValue());
    }

    @FXML
    private void gridSliderOnSwipe(SwipeEvent event) {
        if(gridSlider.getValue()>0)
            grid.resize(gridSlider.getValue());
    }


    @FXML
    private void stretchButtonOnClick(ActionEvent event) {
        try {
            action = new StretchAction(selectedInsertedShape.get(0));
            disableClick.set(false);
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    @FXML
    private void mirrorXButtonOnClick(ActionEvent event) {
        ShapeInterface shapeToMirror = shapesTable.getSelectionModel().getSelectedItem();
        this.action = new MirrorAction(shapeToMirror, true, false);
        invoker.execute(this.action, event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }

    @FXML
    private void mirrorYButtonOnClick(ActionEvent event) {
        ShapeInterface shapeToMirror = shapesTable.getSelectionModel().getSelectedItem();
        this.action = new MirrorAction(shapeToMirror, false, true);
        invoker.execute(this.action, event);
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }
    
    @FXML
    private void textSelection(ActionEvent event) throws InterruptedException {
        mainLabel.setText("Insert a text on paper with left click");
        selectedShape = new TextShape();
        action = new DrawTextAction(selectedShape, colorPickerContour.valueProperty(), listInsertedShapes,drawingPane);
    }

    @FXML
    private void textSize20OnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ResizeTextAction(selectedInsertedShape.get(0),20.0);
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }
    }

    @FXML
    private void textSize40OnAction(ActionEvent event) {
     if (!selectedInsertedShape.isEmpty()) {
            action = new ResizeTextAction(selectedInsertedShape.get(0),40.0);
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }}

    @FXML
    private void textSize60OnAction(ActionEvent event) {
    if (!selectedInsertedShape.isEmpty()) {
            action = new ResizeTextAction(selectedInsertedShape.get(0),60.0);
            invoker.execute(action, event);
            action = new MoveAction(selectedInsertedShape, listInsertedShapes);
        }}
    
    private void resetMainLabel(){
        mainLabel.setText("Geometrical Drawing");
    }
}
