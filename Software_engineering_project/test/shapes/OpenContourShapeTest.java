package shapes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OpenContourShapeTest {
    
    class MockShape extends OpenContourShape{
        public MockShape(){
            this.shape=new RectangleShape();
        }
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getShape method, of class OpenContourShape.
     */
    @Test
    public void testGetShape() {
    }

    /**
     * Test of setInternalColor method, of class OpenContourShape.
     */
    @Test
    public void testSetInternalColor() {
    }

    /**
     * Test of setX method, of class OpenContourShape.
     */
    @Test
    public void testSetX() {
    }

    /**
     * Test of setY method, of class OpenContourShape.
     */
    @Test
    public void testSetY() {
    }

    /**
     * Test of setContourColor method, of class OpenContourShape.
     */
    @Test
    public void testSetContourColor() {
    }

    public class OpenContourShapeImpl extends OpenContourShape {
    }
    
}
