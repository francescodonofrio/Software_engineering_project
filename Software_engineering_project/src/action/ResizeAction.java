package action;

import exceptions.NotResizedException;
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
     * Execute the ResizeAction
     * 
     * @param event the mouse event of the click
     */
    @Override
    public void execute(MouseEvent event) {
        initialX = selectedShape.getShape().getLayoutX();
        initialY = selectedShape.getShape().getLayoutY();
        this.onMouseDragged(event);
    }

    /**
     * Update the dimension of the selectedShape
     * 
     * @param event the mouse event of the click
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        finalX = event.getX();
        finalY = event.getY();
        selectedShape.setDim(initialX, initialY, finalX, finalY);
    }

    /**
     * Check if the selectedShape has been resized through this action. 
     * If it hasn't, then a NotResizedException is thrown.
     * 
     * @param event the mouse event of the click
     * @throws NotResizedException
     */
    @Override
    public void onMouseReleased(MouseEvent event) throws NotResizedException {
        if(initialX == finalX && initialY == finalY)
            throw new NotResizedException();
    }
    
}
