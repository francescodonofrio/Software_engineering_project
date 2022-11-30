package action;

import exceptions.VoidActionException;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class MoveAction implements Action {
    private double initialX, initialY, finalX, finalY, offsetX, offsetY;
    private Shape currentShape;

    public MoveAction(Shape currentShape) {
        this.currentShape = currentShape;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception
     */
    @Override
    public void execute(MouseEvent event) {
        initialX = -1;
        initialY = -1;

        Object actionTarget = event.getTarget();
        if (actionTarget instanceof Shape) {
            currentShape = (Shape) actionTarget;

            initialX = currentShape.getLayoutX();
            initialY = currentShape.getLayoutY();

            offsetX = currentShape.getLayoutX() - event.getX();
            offsetY = currentShape.getLayoutY() - event.getY();
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (initialY != -1 && initialX != -1) {
            currentShape.setLayoutX(offsetX + event.getX());
            currentShape.setLayoutY(offsetY + event.getY());
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseReleased(MouseEvent event) throws VoidActionException {
        if ((initialY == -1 && initialX == -1)) {
            throw new VoidActionException("Didn't drag any shape");
        }

        finalX = event.getX();
        finalY = event.getY();
        currentShape.setLayoutX(offsetX + event.getX());
        currentShape.setLayoutY(offsetY + event.getY());

        if (finalX == initialX && finalY == initialY)
            throw new VoidActionException("Starting position equals final one");
    }
}
