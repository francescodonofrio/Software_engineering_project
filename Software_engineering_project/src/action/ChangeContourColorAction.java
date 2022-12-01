package action;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;
    
public class ChangeContourColorAction implements Action{
    
    private final ObjectProperty<Color> colorPickerContour;
    private final ShapeInterface shape;
    
    public ChangeContourColorAction(ShapeInterface shape, ObjectProperty<Color> colorPickerContour){
        this.shape=shape;
        this.colorPickerContour = colorPickerContour;
    }

    @Override
    public void execute(Event event) throws Exception {
        shape.setContourColor(colorPickerContour.getValue());

    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }
   
}























