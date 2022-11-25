/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Marta Corcione
 */
public class EllipseShapeTest {
    
    private Shape ellipseShape;

    /**
    * Test of constructor of EllipseShape.
    */
    @Test
    public void testEllipseShape() {
        System.out.println("execute");
        
        ellipseShape = new EllipseShape();
        assertEquals(ellipseShape.getClass(),EllipseShape.class);
    }

    /**
     * Test of setDim method, of class EllipseShape.
     */
    @Test
    public void testSetDim() {
        System.out.println("setDim");
        double initialDim1 = 70.0;
        double initialDim2 = 130.0;
        double finalDim1 = 150.0;
        double finalDim2 = 300.0;
        double radiusX = finalDim1 - initialDim1;
        double radiusY = finalDim2 - initialDim2;
        
        EllipseShape instance = new EllipseShape();
        javafx.scene.shape.Ellipse shape = (javafx.scene.shape.Ellipse)instance.getShape();
        
        // Test using finalDim1 > initialDim1 && finalDim2 > initialDim2
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using finalDim1 < initialDim1 && finalDim2 > initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 300.0;
        radiusX = -(finalDim1 - initialDim1);
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using finalDim1 > initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using finalDim1 < initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        radiusX = -(finalDim1 - initialDim1);
        radiusY = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && initialDim1 > 0 && finalDim2 < initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && initialDim1 > 0 && finalDim2 > initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 90.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using finalDim1 < initialDim1 && initialDim2 = 0 && finalDim2 > 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        radiusX = -(finalDim1 - initialDim1);
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using finalDim1 > initialDim1 && initialDim2 = 0 && finalDim2 > 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 85.0;
        finalDim2 = 110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 > 0 && initialDim2 = 0 && finalDim2 > 0        
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 > 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = 110.0;
        radiusX = -(finalDim1 - initialDim1);
        radiusY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = -110.0;
        radiusX = -(finalDim1 - initialDim1);
        radiusY = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
        
        // Test using initialDim1 = 0 && finalDim1 > 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = -110.0;
        radiusX = finalDim1 - initialDim1;
        radiusY = -(finalDim2 - initialDim2);
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        
        assertEquals(shape.getRadiusX(),radiusX,0.1);
        assertEquals(shape.getRadiusY(),radiusY,0.1);
    }
}
