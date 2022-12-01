package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CloseContourShapeTest {
    
    private MockCloseContourShape shape;
    private Shape test;
    
    public CloseContourShapeTest() {
        System.out.println("Test CloseContourShape");
    }
    
    @Before
    public void setUp() {
        shape=new MockCloseContourShape();
        test= shape.getShape();
    }

    /**
     * Test of setInternalColor method, of class CloseContourShape.
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");

        Color internalColor = Color.CHOCOLATE;
        shape.setInternalColor(internalColor);
        assertEquals(test.getFill(),internalColor);
        
        System.out.println("Passed");
    }

    /**
     * Test of setProperties method, of class CloseContourShape.
     */
    @Test
    public void testSetProperties() {
        System.out.print("setProperties: ");

        Color internalColor = Color.CHOCOLATE;
        Color contourColor = Color.YELLOW;
        shape.setProperties(50, 60, internalColor, contourColor);
        assertEquals(test.getStroke(), contourColor);
        assertEquals(test.getFill(), internalColor);
        assertEquals(test.getLayoutX(), 50, 0.1);
        assertEquals(test.getLayoutY(), 60, 0.1);


        System.out.println("Passed");
    }

    public class MockCloseContourShape extends CloseContourShape {
        
        public MockCloseContourShape(){
            this.shape=new Rectangle();
        }
        
        @Override
        public void setDim(double initialX, double initialY, double finalX, double finalY) {
        }
    }
    
}
