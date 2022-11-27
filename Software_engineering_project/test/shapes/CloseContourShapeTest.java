package shapes;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CloseContourShapeTest {

    private Shape test;
    private MockShape shape;

    public CloseContourShapeTest() {
        System.out.println("Test CloseContourShape");
    }

    @Before
    public void setUp() {
        this.test = new Rectangle();
        this.shape = new MockShape(this.test);
    }

    /**
     * Test of getShape method, of class CloseContourShape.
     */
    @Test
    public void testGetShape() {
        System.out.print("getShape: ");

        assertEquals(test, shape.getShape());

        System.out.println("Passed");
    }

    /**
     * Test of setInternalColor method, of class CloseContourShape.
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");

        Color selectedColor = Color.YELLOW;
        shape.setInternalColor(selectedColor);
        assertEquals(test.getFill(), selectedColor);

        System.out.println("Passed");
    }

    /**
     * Test of setX method, of class CloseContourShape.
     */
    @Test
    public void testSetX() {
        System.out.print("setX: ");

        double newX = 32;
        shape.setX(newX);
        assertEquals(test.getLayoutX(), newX, 0);

        System.out.println("Passed");
    }

    /**
     * Test of setY method, of class CloseContourShape.
     */
    @Test
    public void testSetY() {
        System.out.print("setY: ");

        double newY = 57;
        shape.setY(newY);
        assertEquals(test.getLayoutY(), newY, 0);

        System.out.println("Passed");
    }

    /**
     * Test of setContourColor method, of class CloseContourShape.
     */
    @Test
    public void testSetContourColor() {
        System.out.print("setContourColor: ");

        Color selectedColor = Color.CHOCOLATE;
        shape.setContourColor(selectedColor);
        assertEquals(test.getStroke(), selectedColor);

        System.out.println("Passed");
    }

    /**
     * Test of draw method, of class CloseContourShape.
     */
    @Test
    public void testDraw() {
        System.out.print("draw: ");

        Color internalColor = Color.CHOCOLATE;
        Color contourColor = Color.YELLOW;
        shape.setProperties(50, 60, internalColor, contourColor);
        assertEquals(test.getStroke(), contourColor);
        assertEquals(test.getFill(), internalColor);
        assertEquals(test.getLayoutX(), 50, 0.1);
        assertEquals(test.getLayoutY(), 60, 0.1);


        System.out.println("Passed");
    }

    public class MockShape extends CloseContourShape {
        public MockShape(Shape shape) {
            this.shape = shape;
        }

        @Override
        public void setDim(double initialDim1, double initialDim2, double finalDim1, double finalDim2) {

        }
    }
}
