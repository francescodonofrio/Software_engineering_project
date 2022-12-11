package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class ToFrontAction implements Action {

    private final Shape shapeToFront;
    private final ObservableList<Node> listInsertedShapes;
    private int initialPosition;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of ToFrontAction
     *
     * @param selectedShape      the shape to be moved to front
     * @param listInsertedShapes the list containing the shape that has been inserted in the drawing window
     * @throws ShapeNullException if the selected shape is null
     */
    public ToFrontAction(ShapeInterface selectedShape, ObservableList<Node> listInsertedShapes) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else
            this.shapeToFront = selectedShape.getShape();

        this.listInsertedShapes = listInsertedShapes;
        this.hasNotBeenExecuted = true;
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
        listInsertedShapes.remove(shapeToFront);
        listInsertedShapes.add(initialPosition, shapeToFront);
        this.hasNotBeenExecuted = true;
    }

}
