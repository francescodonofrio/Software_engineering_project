package action;

import exceptions.NotExecutedActionException;
import exceptions.NotResizedException;
import exceptions.ShapeNullException;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import shapes.ShapeInterface;

public class ResizeAction implements Action {

    private final ShapeInterface selectedShape;
    private double initialX, initialY, finalX, finalY, previousFinalX, previousFinalY;
    private boolean hasNotBeenExecuted;
    /**
     * Returns a new instance of ResizeAction
     *
     * @param selectedShape the shape to be resized
     * @throws exceptions.ShapeNullException
     */
    public ResizeAction(ShapeInterface selectedShape) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else
            this.selectedShape = selectedShape;
        this.hasNotBeenExecuted=true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) {
        initialX = selectedShape.getShape().getLayoutX();
        initialY = selectedShape.getShape().getLayoutY();
        
        previousFinalX = selectedShape.getShape().getBoundsInParent().getMaxX();
        previousFinalY = selectedShape.getShape().getBoundsInParent().getMaxY();
        this.onMouseDragged(event);
        this.hasNotBeenExecuted=false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
        MouseEvent mouseEvent = (MouseEvent) event;
        finalX = mouseEvent.getX();
        finalY = mouseEvent.getY();
        selectedShape.setDim(initialX, initialY, finalX, finalY);
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws NotResizedException if the initial and final coordinates are the same
     */
    @Override
    public void onMouseReleased(Event event) throws NotResizedException {
        if (initialX == finalX && initialY == finalY)
            throw new NotResizedException();
    }

    /**
     * Undoes the action
     * 
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();
        selectedShape.setX(initialX);
        selectedShape.setY(initialY);
        selectedShape.setDim(initialX, initialY, previousFinalX, previousFinalY);
        hasNotBeenExecuted=true;
    }

}
