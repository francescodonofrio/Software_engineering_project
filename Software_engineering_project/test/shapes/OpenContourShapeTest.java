/*
 */
package shapes;

import exceptions.NotCloseContourException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Paolo
 */
public class OpenContourShapeTest {

    private MockOpenContourShape shape;
    private Shape test;

    public OpenContourShapeTest() {
        System.out.println("Test OpenContourShape");
    }

    @Before
    public void setUp() {
        shape = new MockOpenContourShape();
        test = shape.getShape();
    }

    /**
     * Test of setInternalColor method, of class OpenContourShape.
     * @throws java.lang.Exception
     */
    @Test(expected = NotCloseContourException.class)
    public void testSetInternalColor() throws Exception {
        System.out.print("setInternalColor: ");

        Color internalColor = Color.CHOCOLATE;
        shape.setInternalColor(internalColor);
        assertEquals(test.getFill(), internalColor);

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
        assertEquals(test.getLayoutX(), 50, 0.1);
        assertEquals(test.getLayoutY(), 60, 0.1);


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
        public Color getInternalColor() {
            return null;
        }
    }

}
