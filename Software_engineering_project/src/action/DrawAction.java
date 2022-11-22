/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javafx.scene.layout.Pane;
import shapes.Shape;

/**
 *
 * @author franc
 */
public class DrawAction implements Action{
    
    private final Shape shape;
    private final double X;
    private final double Y;
    private final Pane drawingPane;
    
    public DrawAction(Shape shape, double X, double Y, Pane drawingPane) {
        this.shape = shape;
        this.X = X;
        this.Y = Y;
        this.drawingPane = drawingPane;
    }
    
    @Override
    public void execute(){
        shape.setX(X);
        shape.setY(Y);    
        drawingPane.getChildren().add(shape.getShape());
    }
    
    @Override
    public void undo(){
        
    }
    
}
