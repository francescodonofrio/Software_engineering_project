package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.TextShape;

public class ResizeTextAction implements Action {
    private final ShapeInterface shape;
    private final double newSize;
    private double oldSize;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of ResizeTextAction, given the shape and
     * its new size
     *
     * @param shape the text to change size
     * @param size  the new size for the text
     * @throws ShapeNullException if the selected shape is null
     */
    public ResizeTextAction(ShapeInterface shape, double size) throws ShapeNullException {
        if (shape == null)
            throw new ShapeNullException();
        else
            this.shape = shape;
        this.newSize = size;
        this.hasNotBeenExecuted = true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        oldSize = ((TextShape) shape).getSizeFont();
        ((TextShape) shape).setSizeFont(newSize);
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

        hasNotBeenExecuted = true;
        ((TextShape) shape).setSizeFont(oldSize);
    }
}