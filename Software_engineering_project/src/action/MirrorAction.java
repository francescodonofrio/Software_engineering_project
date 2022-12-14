package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.event.Event;
import shapes.ShapeInterface;

public class MirrorAction implements Action {

    private final ShapeInterface shape;
    private final boolean mirrorX;
    private final boolean mirrorY;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of MirrorAction, given the shape to mirror,
     * and booleans in order to mirror on not on X-axis and Y-axis
     *
     * @param shapeToMirror the shape that has to be mirrored
     * @param mirrorX       indicates if the shape has to be mirrored due the x-axis
     * @param mirrorY       indicates if the shape has to be mirrored due the y-axis
     * @throws ShapeNullException if the selected shape is null
     */
    public MirrorAction(ShapeInterface shapeToMirror, boolean mirrorX, boolean mirrorY) throws ShapeNullException {
        if (shapeToMirror == null)
            throw new ShapeNullException();
        else
            this.shape = shapeToMirror;
        this.mirrorX = mirrorX;
        this.mirrorY = mirrorY;
        this.hasNotBeenExecuted = true;
    }


    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click, in this case useless
     */
    @Override
    public void execute(Event event) throws Exception {
        if (mirrorX)
            this.shape.getShape().setScaleX(this.shape.getShape().getScaleX() * (-1));
        if (mirrorY)
            this.shape.getShape().setScaleY(this.shape.getShape().getScaleY() * (-1));

        hasNotBeenExecuted = false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     * In this case useless
     *
     * @param event the event of the mouse click, in this case useless
     */
    @Override
    public void onMouseDragged(Event event) {
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     * In this case useless
     *
     * @param event the event of the mouse click, in this case useless
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

        if (mirrorX)
            this.shape.getShape().setScaleX(this.shape.getShape().getScaleX() * (-1));
        if (mirrorY)
            this.shape.getShape().setScaleY(this.shape.getShape().getScaleY() * (-1));

        hasNotBeenExecuted = true;
    }

}
