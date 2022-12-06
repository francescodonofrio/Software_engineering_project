
package action;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class ToBackAction implements Action {
    
     private final Shape shapeToBack;
     private ObservableList<Node> listInsertedShapes;
     private int initialPosition;
     
     /**
     * Returns a new instance of ToBackAction
     *
     * @param selectedShape the shape to be move to back
     */
    public ToBackAction(ShapeInterface selectedShape,ObservableList<Node> listInsertedShapes) {
        this.shapeToBack = selectedShape.getShape();
        this.listInsertedShapes=listInsertedShapes;
    }
    
    /**
     * Executes the action specified by the calling class when the move to back item in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        initialPosition = listInsertedShapes.indexOf(shapeToBack);
        shapeToBack.toBack();
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
        listInsertedShapes.remove(shapeToBack);
        listInsertedShapes.add(initialPosition, shapeToBack);

    }
    
}
