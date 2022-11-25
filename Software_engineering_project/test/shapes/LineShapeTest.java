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
public class LineShapeTest {
    private LineShape lineShape;

    /**
    * Test of constructor of LineShape.
    */
    @Test
    public void testEllipseShape() {
        System.out.println("execute");
        
        lineShape = new LineShape();
        assertEquals(lineShape.getClass(),LineShape.class);  
    }
}
