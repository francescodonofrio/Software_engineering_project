package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeContourColorAction implements Action {

    private final ObjectProperty<Color> colorPickerContour;
    private final ShapeInterface shape;
    private Color oldColor;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of ChangeContourColorAction
     *
     * @param selectedShape      the shape to change the contour color to
     * @param colorPickerContour the new contour color
     * @throws exceptions.ShapeNullException
     */
    public ChangeContourColorAction(ShapeInterface selectedShape, ObjectProperty<Color> colorPickerContour) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else 
            this.shape = selectedShape;
        this.colorPickerContour = colorPickerContour;
        this.hasNotBeenExecuted=true;
    }

    /**
     * Executes the action specified by the calling class when the colorPicker in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        oldColor=shape.getCountourColor();
        shape.setContourColor(colorPickerContour.getValue());
        this.hasNotBeenExecuted=false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseReleased(Event event) {
    }

    /**
     * Undoes the action
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();

        shape.setContourColor(oldColor);
        this.hasNotBeenExecuted=true;
    }
}























