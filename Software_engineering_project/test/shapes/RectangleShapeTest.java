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
    
}
