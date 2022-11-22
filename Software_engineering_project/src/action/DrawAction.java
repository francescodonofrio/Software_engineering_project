/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.Shape;

/**
 *
 * @author franc
 */
public class DrawAction implements Action{
    
    private final Shape shape;
    private final Pane drawingPane;
    
    public DrawAction(Shape shape, double X, double Y, Color internalColor, Pane drawingPane) {
        this.shape = shape;
        this.shape.setX(X);
        this.shape.setY(Y);
        this.shape.setInternalColor(internalColor);
        this.drawingPane = drawingPane;
    }
    
    @Override
    public void execute(){
        drawingPane.getChildren().add(shape.getShape());
    }
    
    @Override
    public void undo(){
        
    }
    
}
