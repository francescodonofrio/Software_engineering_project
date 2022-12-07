
package action;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class ToFrontAction implements Action {
    
     private final Shape shapeToFront;
     private ObservableList<Node> listInsertedShapes;
     private int initialPosition;
     
     /**
     * Returns a new instance of ToFrontAction
     *
     * @param selectedShape the shape to be move to front
     */
    public ToFrontAction(ShapeInterface selectedShape,ObservableList<Node> listInsertedShapes) {
        this.shapeToFront = selectedShape.getShape();
        this.listInsertedShapes=listInsertedShapes;
    }
    
    /**
     * Executes the action specified by the calling class when the move to front item in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        initialPosition = listInsertedShapes.indexOf(shapeToFront);
        shapeToFront.toFront();
    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    /**
     * Undoes the action
     */
    @Override
    public void undo() {
        listInsertedShapes.remove(shapeToFront);
        listInsertedShapes.add(initialPosition, shapeToFront);

    }
    
}
