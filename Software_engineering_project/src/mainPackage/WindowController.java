package mainPackage;

import action.*;
import com.sun.glass.ui.Screen;
import exceptions.*;
import javafx.beans.binding.Bindings;
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
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.input.SwipeEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private Button textBtn;
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
    private Group drawingPaneAndGrid;
    @FXML
    private CheckMenuItem gridCheckItem;
    @FXML
    private Slider gridSlider;
    @FXML
    private Label mainLabel;
    @FXML
    private TextField textFieldTextSize;
    
    private Invoker invoker;
    private ShapeInterface selectedShape;
    private ObservableList<ShapeInterface> selectedInsertedShape;
    private FileChooser fileChooser;
    private File file;
    private Action action;
    private ObservableList<ShapeInterface> listInsertedShapes;
    private Clipboard clipboard;
    private MouseEvent rightClickPane;
    private SimpleObjectProperty<Integer> zoomLevel;
    private ArrayDeque<Double> zoomQueue;
    private final double zoomOffset = 0.2;
    private Grid grid;
    private Font defaultFont, boldFont;
    private ObservableList<BooleanBinding> textShapeBinding;
    
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

        textFieldTextSize.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textFieldTextSize.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        textFieldTextSize.setDisable(true);
        
        textShapeBinding = FXCollections.observableArrayList();

        zoomQueue = new ArrayDeque<>();
        
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
                        BooleanBinding allValid = Bindings.createBooleanBinding(() -> textShapeBinding.stream().allMatch(BooleanBinding::get), textShapeBinding);
                        textFieldTextSize.disableProperty().bind(allValid);
                        resizeItem.visibleProperty().bind(allValid);
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
                        BooleanBinding allValid = Bindings.createBooleanBinding(() -> textShapeBinding.stream().allMatch(BooleanBinding::get), textShapeBinding);
                        textFieldTextSize.disableProperty().bind(allValid);
                        resizeItem.visibleProperty().bind(allValid);
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

        clipboard = Clipboard.getClipboard();

        pasteMenuItem.disableProperty().bind(clipboard.hasContent().not());
        
        drawingPane.setPrefSize(Screen.getMainScreen().getWidth(), Screen.getMainScreen().getHeight());
        drawingPane.setClip(new Rectangle (0,0, Screen.getMainScreen().getWidth(),Screen.getMainScreen().getHeight()));
        grid = new Grid(drawingPane, 1, false);
        drawingPaneAndGrid.getChildren().add(grid);
        
        defaultFont = mainLabel.getFont();
        boldFont = Font.font(mainLabel.getFont().getFamily(), FontWeight.BOLD, mainLabel.getFont().getSize());
        
    }

    /**
     * Called when the segment button is clicked
     *
     * @param event the event of the click
     */
    @FXML
    private void lineSegmentSelection(ActionEvent event) {
        mainLabel.setText("Draw a line on paper with left click");
        mainLabel.setFont(boldFont);
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
        mainLabel.setText("Draw a rectangle on paper with left click");
        mainLabel.setFont(boldFont);
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
        mainLabel.setText("Draw an ellipse on paper with left click");
        mainLabel.setFont(boldFont);
        selectedShape = new EllipseShape();
        action = new DrawAction(selectedShape, colorPickerInternal.valueProperty(), colorPickerContour.valueProperty(), listInsertedShapes);
    }
    
    @FXML
    private void textSelection(ActionEvent event) throws InterruptedException {
        mainLabel.setText("Insert a text on paper with left click, insert your text and to finish press enter (to resize select the text already inserted)");
        mainLabel.setFont(boldFont);
        selectedShape = new TextShape();
        action = new DrawTextAction(selectedShape, colorPickerContour.valueProperty(), colorPickerInternal.valueProperty(),listInsertedShapes,drawingPane);
        textShapeBinding.add(selectedShape.getShape().effectProperty().isNull());
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
            ShapesIO.saveFile(file, listInsertedShapes);
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
            ShapesIO.loadFile(file, listInsertedShapes);
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

            resetDefaultAction();
            resetMainLabel();
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
            invoker.executeOnMouseDragged(action, event);
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
        }
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
     * Called when the MenuItem Resize id being clicked
     *
     * @param actionEvent the event of the click
     */
    @FXML
    public void resizeButtonOnClick(ActionEvent actionEvent) {
        try {
            mainLabel.setText("Resize selected shape with left click or drag and drop");
            mainLabel.setFont(boldFont);
            action = new ResizeAction(selectedInsertedShape.get(0));
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            resetDefaultAction();
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
            action = new CopyAction(clipboard, copiedShape);
            invoker.execute(this.action, event);
        } catch (NotShapeToCopyException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetMainLabel();
        resetDefaultAction();
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
            action = new CutAction(clipboard, listInsertedShapes, cuttedShape);
            invoker.execute(this.action, event);
        } catch (NotShapeToCutException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetMainLabel();
        resetDefaultAction();
    }

    /**
     * Called when the MenuItem to front is being clicked
     * 
     * @param event the event of the click
     */
    @FXML
    private void toFrontOnClick(ActionEvent event) {
        action = new ToFrontAction(selectedInsertedShape.get(0), drawingPane.getChildren());
        invoker.execute(action, event);
        resetDefaultAction();
    }

    /**
     * Called when the MenuItem to back is being clicked
     * 
     * @param event the event of the click
     */
    @FXML
    private void toBackOnClick(ActionEvent event) {
        action = new ToBackAction(selectedInsertedShape.get(0), drawingPane.getChildren());
        invoker.execute(action, event);
        resetDefaultAction();
    }

    /**
     * Called when the MenuItem rotate is being clicked
     * 
     * @param event the event of the click
     */    
    @FXML
    private void rotateButtonOnClick(ActionEvent event) {
        try{
            mainLabel.setText("Rotate the selected shape with left click or drag and drop on ");
            mainLabel.setFont(boldFont);
            action = new RotateAction(selectedInsertedShape.get(0));
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            resetDefaultAction();
        }
    }

    /**
     * Called when the MenuItem stretch is being clicked
     * 
     * @param event the event of the click
     */  
    @FXML
    private void stretchButtonOnClick(ActionEvent event) {
        try {
            mainLabel.setText("Stretch the selected shape by dragging the mouse ");
            mainLabel.setFont(boldFont);
            action = new StretchAction(selectedInsertedShape.get(0));
        } catch (ShapeNullException ex) {
            Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            resetDefaultAction();
        }
    }
    
    /**
     * Called when the MenuItem mirror horizzontally is being clicked
     * 
     * @param event the event of the click
     */
    @FXML
    private void mirrorXButtonOnClick(ActionEvent event) {
        ShapeInterface shapeToMirror = shapesTable.getSelectionModel().getSelectedItem();
        this.action = new MirrorAction(shapeToMirror, true, false);
        invoker.execute(this.action, event);
        resetDefaultAction();
    }
    
    /**
     * Called when the MenuItem mirror vertically is being clicked
     * 
     * @param event the event of the click
     */
    @FXML
    private void mirrorYButtonOnClick(ActionEvent event) {
        ShapeInterface shapeToMirror = shapesTable.getSelectionModel().getSelectedItem();
        this.action = new MirrorAction(shapeToMirror, false, true);
        invoker.execute(this.action, event);
        resetDefaultAction();
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
            resetDefaultAction();
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
            resetDefaultAction();
        }
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

        resetDefaultAction();
    }

    /**
     * Called when the MenuItem Paste is being clicked.
     *
     * @param event the event of the click
     */
    @FXML
    private void pasteButtonOnClick(ActionEvent event) {
        action = new PasteAction(clipboard, listInsertedShapes);
        invoker.execute(action, rightClickPane);

        resetDefaultAction();
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
        drawingPane.setPrefHeight(zoomQueue.removeLast());
        drawingPane.setPrefWidth(zoomQueue.removeLast());
        zoomLevel.set(zoomLevel.getValue() - 1);
        
        drawingPaneAndGrid.getChildren().remove(grid);
        grid = new Grid(drawingPane, gridSlider.getValue(), gridCheckItem.isSelected());
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
        zoomQueue.add(drawingPane.getPrefWidth());
        zoomQueue.add(drawingPane.getPrefHeight());
        drawingPane.setPrefWidth(drawingPane.getPrefWidth() + drawingPane.getPrefWidth() * zoomOffset);
        drawingPane.setPrefHeight(drawingPane.getPrefHeight() + drawingPane.getPrefHeight() * zoomOffset);
        zoomLevel.set(zoomLevel.getValue() + 1);
        
        drawingPaneAndGrid.getChildren().remove(grid);
        grid = new Grid(drawingPane, gridSlider.getValue(), gridCheckItem.isSelected());
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
    
    /**
     * Called when on the grid slider is performed an on mouse dragged or on mouse pressed action
     * 
     * @param event the event of the click
     */
    @FXML
    private void gridSliderOnMouse(MouseEvent event) {
        if(gridSlider.getValue()>0)
            grid.resize(gridSlider.getValue());
    }
    
    /**
     * Called when on the grid slider is performed a swipe action
     * 
     * @param event the event of the click
     */
    @FXML
    private void gridSliderOnSwipe(SwipeEvent event) {
        if(gridSlider.getValue()>0)
            grid.resize(gridSlider.getValue());
    }
    
    /**
     * Called when the menu item of the buttonTextSize are being clicked
     * 
     * @param event the event of the click
     */
    @FXML
    private void textResizeOnAction(ActionEvent event) {
        if (!selectedInsertedShape.isEmpty()) {
            action = new ResizeTextAction(selectedInsertedShape.get(0), Double.parseDouble(textFieldTextSize.getText()));
            invoker.execute(action, event);
            resetDefaultAction();
        }
    }
    
    /**
     * Reset the main lebel to a default value
     */
    private void resetMainLabel(){
        mainLabel.setText("Select a shape on the left to draw it");
        mainLabel.setFont(defaultFont);
    }
    
    /**
     * Reset the action to the default one
     */
    private void resetDefaultAction(){
        action = new MoveAction(selectedInsertedShape, listInsertedShapes);
    }
}
