package action;

import exceptions.NotMovedException;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import shapes.ShapeInterface;

public class MoveAction implements Action {
    private double initialX, initialY, finalX, finalY, offsetX, offsetY;
    private ObservableList<ShapeInterface> listInsertedShapes,currentShape;

    /**
     * Returns a new instance of MoveAction
     *
     * @param currentShape the current selected shape
     * @param listInsertedShapes the list of already inserted shapes
     */
    public MoveAction(ObservableList<ShapeInterface> currentShape, ObservableList<ShapeInterface> listInsertedShapes) {
        this.currentShape = currentShape;
        this.listInsertedShapes=listInsertedShapes;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void execute(MouseEvent event){
        currentShape.clear();

        initialX = -1;
        initialY = -1;

        Object actionTarget = event.getTarget();
        if (actionTarget instanceof Shape ) {
            Shape selectedShape=(Shape) actionTarget;
            for(ShapeInterface current:listInsertedShapes) {
                if(current.getShape().equals(selectedShape)){
                    currentShape.add(current);
                    break;
                }
            }

            initialX = event.getX();
            initialY = event.getY();

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
            currentShape.get(0).move(offsetX + event.getX(),offsetY + event.getY());
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws NotMovedException if the initial and final coordinates are the same
     */
    @Override
    public void onMouseReleased(MouseEvent event) throws NotMovedException {
        if ((initialY == -1 && initialX == -1)) {
            throw new NotMovedException();
        }

        finalX = event.getX();
        finalY = event.getY();
        currentShape.get(0).move(offsetX + event.getX(),offsetY + event.getY());

        if (finalX == initialX && finalY == initialY) {
            throw new NotMovedException();
        }
    }
}
