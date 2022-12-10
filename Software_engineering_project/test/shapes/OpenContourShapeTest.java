package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OpenContourShapeTest {

    private MockOpenContourShape shape;

    public OpenContourShapeTest() {
        System.out.println("Test OpenContourShape");
    }

    @Before
    public void setUp() {
        shape = new MockOpenContourShape();
    }

    /**
     * Test of setInternalColor method, of class OpenContourShape.
     * 
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");
        
        shape.setInternalColor(Color.CHOCOLATE);
        assertEquals(shape.getInternalColor().getBlue(), Color.TRANSPARENT.getBlue(),0.1);
        assertEquals(shape.getInternalColor().getRed(), Color.TRANSPARENT.getRed(),0.1);
        assertEquals(shape.getInternalColor().getGreen(), Color.TRANSPARENT.getGreen(),0.1);
        assertEquals(shape.getInternalColor().getOpacity(), Color.TRANSPARENT.getOpacity(),0.1);

        System.out.println("Passed");
    }

    public class MockOpenContourShape extends OpenContourShape {

        public MockOpenContourShape() {
            this.shape = new Line();
        }

        @Override
        public void setDim(double initialX, double initialY, double finalX, double finalY) {
        }

        @Override
        public double getDimX() {
            return 0;
        }

        @Override
        public double getDimY() {
            return 0;
        }
    }

}
