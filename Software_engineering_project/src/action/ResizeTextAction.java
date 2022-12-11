
package action;

import exceptions.NotExecutedActionException;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.TextShape;

public class ResizeTextAction implements Action {
    private final ShapeInterface shape;
    private double newSize,oldSize; 
    private boolean hasNotBeenExecuted;
    /**
     * Returns a new instance of ResizeTextAction, given the shape and
     * its new size
     *
     * @param shape               the text to change size
     * @param size                 the new size for the text
     */
    public ResizeTextAction(ShapeInterface shape, double size) {
        this.shape = shape;
        this.newSize=size;
        this.hasNotBeenExecuted=true;
    }
    
    @Override
    public void execute(Event event) throws Exception {
        oldSize = ((TextShape)shape).getSizeFont();
        ((TextShape)shape).setSizeFont(newSize);
         this.hasNotBeenExecuted=false;
    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();

        ((TextShape)shape).setSizeFont(oldSize);
    }
}