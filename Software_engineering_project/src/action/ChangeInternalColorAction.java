package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeInternalColorAction implements Action {
    private final ObjectProperty<Color> colorPickerInternal;
    private final ShapeInterface shape;
    private Color initialColor;
    private boolean hasNotBeenExecuted;

    /**
     * Returns a new instance of ChangeContourColorAction
     *
     * @param selectedShape       the shape to change the contour color to
     * @param colorPickerInternal the new contour color
     * @throws exceptions.ShapeNullException
     */
    public ChangeInternalColorAction(ShapeInterface selectedShape, ObjectProperty<Color> colorPickerInternal) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else
            this.shape = selectedShape;
        this.colorPickerInternal = colorPickerInternal;
        this.hasNotBeenExecuted = true;
    }

    /**
     * Executes the action specified by the calling class when the colorPicker in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        initialColor = shape.getInternalColor();
        shape.setInternalColor(colorPickerInternal.getValue());
        this.hasNotBeenExecuted = false;
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
     *
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();
        shape.setInternalColor(initialColor);
        this.hasNotBeenExecuted = true;
    }
}
