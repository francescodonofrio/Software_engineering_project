package action;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeInternalColorAction implements Action {
    private final ObjectProperty<Color> colorPickerInternal;
    private final ShapeInterface shape;
    private  Color initialColor;

    /**
     * Returns a new instance of ChangeContourColorAction
     *
     * @param selectedShape       the shape to change the contour color to
     * @param colorPickerInternal the new contour color
     */
    public ChangeInternalColorAction(ShapeInterface selectedShape, ObjectProperty<Color> colorPickerInternal) {
        this.shape = selectedShape;
        this.colorPickerInternal = colorPickerInternal;
    }

    /**
     * Executes the action specified by the calling class when the colorPicker in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        initialColor=shape.getInternalColor();
        shape.setInternalColor(colorPickerInternal.getValue());
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
     */
    @Override
    public void undo() {
        shape.setInternalColor(initialColor);
    }
}
