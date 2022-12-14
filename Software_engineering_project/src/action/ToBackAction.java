package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class ToBackAction implements Action {

    private final Shape shapeToBack;
    private final ObservableList<Node> listInsertedShapes;
    private int initialPosition;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of ToBackAction
     *
     * @param selectedShape      the shape to be moved to back
     * @param listInsertedShapes the list containing the shape that has been inserted in the drawing window
     * @throws ShapeNullException if the selected shape is null
     */
    public ToBackAction(ShapeInterface selectedShape, ObservableList<Node> listInsertedShapes) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else
            this.shapeToBack = selectedShape.getShape();
        this.listInsertedShapes = listInsertedShapes;
        this.hasNotBeenExecuted = true;
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
        this.hasNotBeenExecuted = false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    /**
     * Undoes the action
     *
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();
        listInsertedShapes.remove(shapeToBack);
        listInsertedShapes.add(initialPosition, shapeToBack);
        this.hasNotBeenExecuted = true;
    }

}
