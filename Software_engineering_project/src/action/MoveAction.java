package action;

import exceptions.NotMovedException;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class MoveAction implements Action {
    private double initialX, initialY, finalX, finalY, offsetX, offsetY;
    private Shape[] currentShape;
    private Shape selectedShape;

    public MoveAction(Shape[] currentShape) {
        this.currentShape = currentShape;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(MouseEvent event) {
        currentShape[0]=null;
        initialX = -1;
        initialY = -1;

        Object actionTarget = event.getTarget();
        if (actionTarget instanceof Shape) {
            selectedShape=(Shape) actionTarget;
            currentShape[0]=selectedShape;

            initialX = selectedShape.getLayoutX();
            initialY = selectedShape.getLayoutY();

            offsetX = selectedShape.getLayoutX() - event.getX();
            offsetY = selectedShape.getLayoutY() - event.getY();
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
            selectedShape.setLayoutX(offsetX + event.getX());
            selectedShape.setLayoutY(offsetY + event.getY());
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws exceptions.NotMovedException
     */
    @Override
    public void onMouseReleased(MouseEvent event) throws NotMovedException {
        if ((initialY == -1 && initialX == -1)) {
            throw new NotMovedException();
        }

        finalX = event.getX();
        finalY = event.getY();
        selectedShape.setLayoutX(offsetX + event.getX());
        selectedShape.setLayoutY(offsetY + event.getY());

        if (finalX == initialX && finalY == initialY)
            throw new NotMovedException();
    }
}
