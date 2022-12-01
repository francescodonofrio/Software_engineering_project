package action;

import exceptions.NotCloseContourException;
import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class ChangeInternalColorAction implements Action{
     private final ObjectProperty<Color> colorPickerInternal;
    private final ShapeInterface shape;
    
    public ChangeInternalColorAction(ShapeInterface shape, ObjectProperty<Color> colorPickerInternal){
        this.shape=shape;
        this.colorPickerInternal = colorPickerInternal;
    }

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
