package action;

import exceptions.NotResizedException;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import shapes.ShapeInterface;

public class ResizeAction implements Action{

    private final ShapeInterface selectedShape;
    private double initialX, initialY, finalX, finalY;
    
    /**
     * Returns a new instance of ResizeAction
     * 
     * @param selectedShape the shape to be resized
     */
    public ResizeAction(ShapeInterface selectedShape) {
        this.selectedShape = selectedShape;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(MouseEvent event) {
        initialX = selectedShape.getShape().getLayoutX();
        initialY = selectedShape.getShape().getLayoutY();
        this.onMouseDragged(event);
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        finalX = event.getX();
        finalY = event.getY();
        selectedShape.setDim(initialX, initialY, finalX, finalY);
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws NotResizedException if the initial and final coordinates are the same
     */
    @Override
    public void onMouseReleased(MouseEvent event) throws NotResizedException {
        if(initialX == finalX && initialY == finalY)
            throw new NotResizedException();
    }

    @Override
    public void execute(ActionEvent event) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
