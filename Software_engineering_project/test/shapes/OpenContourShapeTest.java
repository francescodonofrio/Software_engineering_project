package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OpenContourShapeTest {
    
    public class MockShape extends OpenContourShape{
        public MockShape(javafx.scene.shape.Shape shape){
            this.shape=shape;
        }
    }
    
    private javafx.scene.shape.Shape test;
    private MockShape shape;
    
    public OpenContourShapeTest(){
        System.out.println("Test OpenContourShape");
    }
    
    @Before
    public void setUp() {        
        this.test=new Line();
        this.shape=new MockShape(this.test);
    }

    /**
     * Test of getShape method, of class OpenContourShape.
     */
    @Test
    public void testGetShape() {
        System.out.print("getShape: ");
        
        assertEquals(test,shape.getShape());
        
        System.out.println("Passed");
    }

    /**
     * Test of setInternalColor method, of class OpenContourShape.
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");
        
        Color selectedColor=Color.YELLOW;
        shape.setInternalColor(selectedColor);
        assertEquals(test.getFill(),null);
        
        System.out.println("Passed");
    }

    /**
     * Test of setX method, of class OpenContourShape.
     */
    @Test
    public void testSetX() {
        System.out.print("setX: ");
        
        double newX=32;
        shape.setX(newX);
        assertEquals(test.getLayoutX(),newX,0);
        
        System.out.println("Passed");
    }

    /**
     * Test of setY method, of class OpenContourShape.
     */
    @Test
    public void testSetY() {
        System.out.print("setY: ");
        
        double newY=57;
        shape.setY(newY);
        assertEquals(test.getLayoutY(),newY,0);
        
        System.out.println("Passed");
    }

    /**
     * Test of setContourColor method, of class OpenContourShape.
     */
    @Test
    public void testSetContourColor() {
        System.out.print("setContourColor: ");
        
        Color selectedColor=Color.CHOCOLATE;
        shape.setContourColor(selectedColor);
        assertEquals(test.getStroke(),selectedColor);
        
        System.out.println("Passed");
    }    
}
