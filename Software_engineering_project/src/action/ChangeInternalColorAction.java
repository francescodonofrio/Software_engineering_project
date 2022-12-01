/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

/**
 *
 * @author Marta Corcione
 */
public class ChangeInternalColorAction implements Action{
     private final ObjectProperty<Color> colorPickerInternal;
    private final ShapeInterface shape;
    
    public ChangeInternalColorAction(ShapeInterface shape, ObjectProperty<Color> colorPickerInternal){
        this.shape=shape;
        this.colorPickerInternal = colorPickerInternal;
    }

    @Override
    public void execute(MouseEvent event) throws Exception {
    }
    
    @Override
    public void execute(ActionEvent event) throws Exception {
        shape.setInternalColor(colorPickerInternal.getValue());
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
    }

    @Override
    public void onMouseReleased(MouseEvent event) throws Exception {
    }
   
}
