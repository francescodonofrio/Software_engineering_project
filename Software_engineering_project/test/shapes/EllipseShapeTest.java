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

}
