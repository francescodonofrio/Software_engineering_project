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
    
    
public class ChangeContourColorAction implements Action{
    
    private final ObjectProperty<Color> colorPickerContour;
    private final ShapeInterface shape;
    
    public ChangeContourColorAction(ShapeInterface shape, ObjectProperty<Color> colorPickerContour){
        this.shape=shape;
        this.colorPickerContour = colorPickerContour;
    }

    @Override
    public void execute(MouseEvent event) throws Exception {
    }
    
    @Override
    public void execute(ActionEvent event) throws Exception {
        shape.setContourColor(colorPickerContour.getValue());
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
    }

    @Override
    public void onMouseReleased(MouseEvent event) throws Exception {
    }
   
}























