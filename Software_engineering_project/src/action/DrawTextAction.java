
package action;

import exceptions.NotExecutedActionException;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shapes.ShapeInterface;
import shapes.TextShape;

public class DrawTextAction implements Action{

    private final ShapeInterface shape;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ObjectProperty<Color> colorPickerContour;
    private final ObjectProperty<Color> colorPickerInternal;
    private boolean hasNotBeenExecuted;
    private final Pane drawingPane;


    /**
     * Returns a new instance of DrawTextAction, given the shape to draw,
     * its contour color and the pane where to draw at
     *
     * @param shape               the shape to draw
     * @param colorPickerContour  an ObjectProperty<Color> from whom the shape's contour color is taken
     * @param listInsertedShapes  the list in which are stored the shapes
     * @param drawingPane the drawing pane in which draw the shape
     */
    public DrawTextAction(ShapeInterface shape, ObjectProperty<Color> colorPickerContour, ObjectProperty<Color> colorPickerInternal, ObservableList<ShapeInterface> listInsertedShapes, Pane drawingPane) {
        this.shape = shape;
        this.listInsertedShapes = listInsertedShapes;
        this.colorPickerContour = colorPickerContour;
        this.colorPickerInternal = colorPickerInternal;
        this.hasNotBeenExecuted=true;
        this.drawingPane=drawingPane;
    }
    
    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
    listInsertedShapes.add(shape);
    ((TextShape)shape).setSizeFont(40);

    Text text= (Text) shape.getShape();
    text.setText("");
    MouseEvent mouseEvent = (MouseEvent) event;
        double initialX = mouseEvent.getX();
        double initialY = mouseEvent.getY();
    shape.setProperties(initialX, initialY,colorPickerInternal.getValue(), colorPickerContour.getValue());
    hasNotBeenExecuted=false;

    TextField temporaryText = new TextField();
    temporaryText.setLayoutX(initialX-20);
    temporaryText.setLayoutY(initialY-50);
    temporaryText.setText("write your text here...");
    temporaryText.setBackground(Background.EMPTY);
    temporaryText.setBorder(Border.EMPTY);
    temporaryText.setFont(Font.font(40));

    drawingPane.getChildren().add(temporaryText);
    temporaryText.setVisible(true);

    text.setVisible(false);

    temporaryText.setOnKeyPressed(ke -> {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String content =temporaryText.getText();
            text.setText(content);

            drawingPane.getChildren().remove(temporaryText);
            text.setVisible(true);
        }
    });
    

    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    /**
     * Undoes the action
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();

        listInsertedShapes.remove(shape);
    }

}
