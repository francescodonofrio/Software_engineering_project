package action;

import exceptions.NotExecutedActionException;
import exceptions.NotStretchedException;
import exceptions.ShapeNullException;
import exceptions.ShapeWithNullWidthException;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import shapes.ShapeInterface;

public class StretchAction implements Action{

    private final ShapeInterface selectedShape;
    private double halfWidth, width, previousStretch, stretchOffset;
    private boolean hasNotBeenExecuted;
    private MouseEvent mouseEvent;
    private final Scale scale;
    
    /**
     * Returns a new instance of StretchAction
     *
     * @param selectedShape the shape to be resized
     * @throws exceptions.ShapeNullException
     */
    public StretchAction(ShapeInterface selectedShape) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        this.selectedShape = selectedShape;
        this.scale = selectedShape.getScale();
        this.hasNotBeenExecuted=true;
        this.previousStretch = scale.getX();
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws exceptions.ShapeWithNullWidthException
     */
    @Override
    public void execute(Event event) throws ShapeWithNullWidthException {
        this.previousStretch = scale.getX();
        this.onMouseDragged(event);
        this.hasNotBeenExecuted=false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     * @throws exceptions.ShapeWithNullWidthException
     */
    @Override
    public void onMouseDragged(Event event) throws ShapeWithNullWidthException{
        mouseEvent = (MouseEvent) event;
        width = selectedShape.getDimX();
        if(width == 0)
            throw new ShapeWithNullWidthException();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(mouseEvent.getX()-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        if(mouseEvent.getX() > -Math.abs(width))
            scale.setX(stretchOffset);
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws exceptions.NotStretchedException
     */
    @Override
    public void onMouseReleased(Event event) throws NotStretchedException {
        if (previousStretch == scale.getX()){
            this.hasNotBeenExecuted=true;
            throw new NotStretchedException();
        }
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
        scale.setX(previousStretch);
        this.hasNotBeenExecuted=true;
    }
    
}