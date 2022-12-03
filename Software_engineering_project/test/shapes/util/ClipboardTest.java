/*
 */
package shapes.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.RectangleShape;
import shapes.ShapeInterface;

/**
 *
 * @author Paolo
 */
public class ClipboardTest {
    
    public ClipboardTest() {
        System.out.println("Test Clipboard");
    }
    
    @Before
    public void setUp() {
        
    }

    /**
     * Test of getClipboard method, of class Clipboard.
     */
    @Test
    public void testGetClipboard() {
        System.out.print("getClipboard: ");
        
        Clipboard c1=Clipboard.getClipboard(),c2=Clipboard.getClipboard();
        
        assertEquals(c1,c2);
        
        System.out.println("Passed");
    }

    /**
     * Test of getValue and setValue method, of class Clipboard.
     */
    @Test
    public void testClipboard() {
        System.out.print("setClipboard and getClipboard: ");
        
        Clipboard clipboard=Clipboard.getClipboard(); 
        ShapeInterface shape=new RectangleShape();
        
        clipboard.setValue(shape);
        ShapeInterface savedShape=clipboard.getValue();
        
        assertEquals(shape.getClass(),savedShape.getClass());
        assertEquals(shape.getShape().getStroke(),savedShape.getShape().getStroke());
        assertEquals(shape.getShape().getFill(),savedShape.getShape().getFill());
        assertEquals(shape.getShape().getLayoutX(),savedShape.getShape().getLayoutX(),0.1);
        assertEquals(shape.getShape().getLayoutY(),savedShape.getShape().getLayoutY(),0.1);

        System.out.println("Passed");
    }
   
}
