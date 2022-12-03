package action;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeContourColorAction implements Action {

    private final ObjectProperty<Color> colorPickerContour;
    private final ShapeInterface shape;

    /**
     * Returns a new instance of ChangeContourColorAction
     *
     * @param selectedShape      the shape to change the contour color to
     * @param colorPickerContour the new contour color
     */
    public ChangeContourColorAction(ShapeInterface selectedShape, ObjectProperty<Color> colorPickerContour) {
        this.shape = selectedShape;
        this.colorPickerContour = colorPickerContour;
    }

    /**
     * Executes the action specified by the calling class when the colorPicker in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        shape.setContourColor(colorPickerContour.getValue());

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

}























