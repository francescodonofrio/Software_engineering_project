/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Marta Corcione
 */
public class RectangleShapeTest {
    private RectangleShape rectangleShape;

    /**
    * Test of constructor of RectangleShape.
    */
    @Test
    public void testEllipseShape() {
        System.out.println("execute");
        
        rectangleShape = new RectangleShape();
        assertEquals(rectangleShape.getClass(),RectangleShape.class);  
    }
    
    /**
     * Test of setDim method, of class RectangleShape.
     */
    @Test
    public void testSetDim() {
        System.out.println("setDim");
        double initialDim1 = 70.0;
        double initialDim2 = 130.0;
        double finalDim1 = 150.0;
        double finalDim2 = 300.0;
        double width = finalDim1 - initialDim1;
        double height = finalDim2 - initialDim2;
        double layoutX, layoutY;
        
        RectangleShape instance = new RectangleShape();
        javafx.scene.shape.Rectangle shape = (javafx.scene.shape.Rectangle)instance.getShape();
        
        layoutX = shape.getLayoutX();
        layoutY = shape.getLayoutY();
        
        // Test using finalDim1 > initialDim1 && finalDim2 > initialDim2
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using finalDim1 < initialDim1 && finalDim2 > initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 300.0;
        width = -(finalDim1 - initialDim1);
        height = finalDim2 - initialDim2;
        layoutX = finalDim1;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using finalDim1 > initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        width = finalDim1 - initialDim1;
        height = -(finalDim2 - initialDim2);
        layoutY = finalDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using finalDim1 < initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        width = -(finalDim1 - initialDim1);
        height = -(finalDim2 - initialDim2);
        layoutX = finalDim1;
        layoutY = finalDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && finalDim2 < initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        width = finalDim1 - initialDim1;
        height = -(finalDim2 - initialDim2);
        layoutY = finalDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && finalDim2 > initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 90.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        width = finalDim1 - initialDim1;
        height = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using finalDim1 < initialDim1 && initialDim2 = 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        width = -(finalDim1 - initialDim1);
        height = finalDim2 - initialDim2;
        layoutX = finalDim1;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using finalDim1 > initialDim1 && initialDim2 = 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 85.0;
        finalDim2 = 110.0;
        width = finalDim1 - initialDim1;
        height = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && initialDim2 = 0        
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        width = finalDim1 - initialDim1;
        height = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 > 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = 110.0;
        width = -(finalDim1 - initialDim1);
        height = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = -110.0;
        width = -(finalDim1 - initialDim1);
        height = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 > 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = -110.0;
        width = finalDim1 - initialDim1;
        height = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getWidth(),width,0.1);
        assertEquals(shape.getHeight(),height,0.1);
    }
    
}
