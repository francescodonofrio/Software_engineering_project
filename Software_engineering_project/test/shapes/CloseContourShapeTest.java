package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CloseContourShapeTest {

    private MockCloseContourShape shape;

    public CloseContourShapeTest() {
        System.out.println("Test CloseContourShape");
    }

    @Before
    public void setUp() {
        shape = new MockCloseContourShape();
    }

    /**
     * Test of setInternalColor method, of class CloseContourShape.
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");

        shape.setInternalColor(Color.CHOCOLATE);
        assertEquals(shape.getInternalColor().getBlue(), Color.CHOCOLATE.getBlue(),0.1);
        assertEquals(shape.getInternalColor().getRed(), Color.CHOCOLATE.getRed(),0.1);
        assertEquals(shape.getInternalColor().getGreen(), Color.CHOCOLATE.getGreen(),0.1);
        assertEquals(shape.getInternalColor().getOpacity(), Color.CHOCOLATE.getOpacity(),0.1);

        System.out.println("Passed");
    }

    public class MockCloseContourShape extends CloseContourShape {

        public MockCloseContourShape() {
            this.shape = new Rectangle();
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
