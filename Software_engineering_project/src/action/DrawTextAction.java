
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
import shapes.ShapeInterface;
import shapes.TextShape;

public class DrawTextAction implements Action{

    private final ShapeInterface shape;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ObjectProperty<Color> colorPickerContour;
    private double initialX, initialY;
    private boolean hasNotBeenExecuted;
    private final Pane drawingPane;
    private String newText;


    /**
     * Returns a new instance of DrawTextAction, given the shape to draw,
     * its contour color and the pane where to draw at
     *
     * @param shape               the shape to draw
     * @param colorPickerContour  an ObjectProperty<Color> from whom the shape's contour color is taken
     * @param listInsertedShapes  the list in which are stored the shapes
     * @param drawingPane the drawing pane in which draw the shape
     */
    public DrawTextAction(ShapeInterface shape, ObjectProperty<Color> colorPickerContour, ObservableList<ShapeInterface> listInsertedShapes, Pane drawingPane) {
        this.shape = shape;
        this.listInsertedShapes = listInsertedShapes;
        this.colorPickerContour = colorPickerContour;
        this.hasNotBeenExecuted=true;
        this.drawingPane=drawingPane;
    }
    
    @Override
    public void execute(Event event) throws Exception {
    System.out.println(event.getEventType().toString());
    listInsertedShapes.add(shape);
    ((TextShape) shape).setText("");//this line fix bug
    MouseEvent mouseEvent = (MouseEvent) event;
    initialX = mouseEvent.getX();
    initialY = mouseEvent.getY();
    shape.setProperties(initialX, initialY,null, colorPickerContour.getValue());
    hasNotBeenExecuted=false;
   
    TextField t = new TextField();
    t.setLayoutX(initialX);
    t.setLayoutY(initialY);
    String style ="-fx-text-fill: #"+colorPickerContour.get().toString().substring(2,colorPickerContour.get().toString().length()-2 )+";";
    t.setStyle(style);
    t.setText("edit your text here...");
    t.setBackground(Background.EMPTY);
    t.setBorder(Border.EMPTY);
    shape.getShape().setVisible(false);
    drawingPane.getChildren().add(t);
    t.setVisible(true);
    
    t.setOnKeyPressed(new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            newText =t.getText();
            ((TextShape) shape).setText(newText);
            t.setVisible(false);
            drawingPane.getChildren().remove(t);
            shape.getShape().setVisible(true);
        }
    }
    });
    

    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();

        listInsertedShapes.remove(shape);
    }

}
