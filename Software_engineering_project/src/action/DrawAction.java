/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import shapes.Shape;

/**
 *
 * @author franc
 */
public class DrawAction implements Action{
    
    private final Shape shape;
    private final double X;
    private final double Y;
    

    public DrawAction(Shape shape, double X, double Y) {
        this.shape = shape;
        this.X = X;
        this.Y = Y;
    }
    
    @Override
    public void execute(){
        shape.setX(X);
        shape.setY(Y);
    }
    
    @Override
    public void undo(){
        
    }
    
}
