package action;

import exceptions.NotExecutedActionException;
import exceptions.NotMovedException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class MoveAction implements Action {
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ObservableList<ShapeInterface> currentShape;
    private double initialX, initialY, offsetX, offsetY;
    private ShapeInterface movedShape;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of MoveAction
     *
     * @param currentShape       the current selected shape
     * @param listInsertedShapes the list of already inserted shapes
     */
    public MoveAction(ObservableList<ShapeInterface> currentShape, ObservableList<ShapeInterface> listInsertedShapes) {
        this.currentShape = currentShape;
        this.listInsertedShapes = listInsertedShapes;
        this.hasNotBeenExecuted = true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) {
        currentShape.clear();

        initialX = -1;
        initialY = -1;

        MouseEvent mouseEvent = (MouseEvent) event;

        Object actionTarget = event.getTarget();
        if (actionTarget instanceof Shape) {
            Shape selectedShape = (Shape)actionTarget;
            for (ShapeInterface current : listInsertedShapes) {
                if (current.getShape().equals(selectedShape)) {
                    movedShape = current;
                    currentShape.add(current);
                    break;
                }
            }

            initialX = selectedShape.getLayoutX();
            initialY = selectedShape.getLayoutY();

            offsetX = initialX - mouseEvent.getX();
            offsetY = initialY - mouseEvent.getY();

            hasNotBeenExecuted = false;
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
        if (initialY != -1 && initialX != -1) {
            MouseEvent mouseEvent = (MouseEvent) event;
            currentShape.get(0).move(offsetX + mouseEvent.getX(), offsetY + mouseEvent.getY());
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws NotMovedException if the initial and final coordinates are the same
     */
    @Override
    public void onMouseReleased(Event event) throws NotMovedException {
        if ((initialY == -1 && initialX == -1)) {
            throw new NotMovedException();
        }

        MouseEvent mouseEvent = (MouseEvent) event;

        double finalX = offsetX + mouseEvent.getX();
        double finalY = offsetY + mouseEvent.getY();
        currentShape.get(0).move(offsetX + mouseEvent.getX(), offsetY + mouseEvent.getY());

        if (finalX == initialX && finalY == initialY) {
            throw new NotMovedException();
        }
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
        movedShape.move(initialX, initialY);
        hasNotBeenExecuted = true;
    }
}
