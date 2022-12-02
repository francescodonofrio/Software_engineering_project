package action;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeInternalColorAction implements Action{
     private final ObjectProperty<Color> colorPickerInternal;
    private final ShapeInterface shape;
    
     /**
     * Returns a new instance of ChangeContourColorAction
     * 
     * @param selectedShape the shape to change the contour color to
     * @param colorPickerInternal the new contour color
     */
    public ChangeInternalColorAction(ShapeInterface selectedShape, ObjectProperty<Color> colorPickerInternal){
        this.shape=selectedShape;
        this.colorPickerInternal = colorPickerInternal;
    }

    /**
     * Executes the action specified by the calling class when the colorPicker in clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) throws Exception {
        shape.setInternalColor(colorPickerInternal.getValue());
    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }
   
}
