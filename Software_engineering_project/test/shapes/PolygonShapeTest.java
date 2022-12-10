package shapes;

import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolygonShapeTest {

    private Polygon innerShape;
    private PolygonShape test;
    private Double[] vertices;

    public PolygonShapeTest() {
        System.out.println("Test PolygonShape");
    }

    @Before
    public void setUp() {
        Double[] originalVertices = new Double[]{
                0.0, 0.0,
                20.0, 10.0,
                10.0, 20.0};
        vertices = originalVertices.clone();
        test = new PolygonShape();
        ((Polygon) test.getShape()).getPoints().addAll(originalVertices);
        innerShape = (Polygon) test.getShape();
    }

    /**
     * Test of setDim method, of class PolygonShape.
     */
    @Test
    public void testSetDim() {
        System.out.print("setDim: ");

        double initialX = 32, initialY = 22, finalX = 100, finalY = 45;
        test.setDim(initialX, initialY, finalX, finalY);

        Bounds polygonBounds = innerShape.getBoundsInParent();
        double minX,
                minY,
                maxX = polygonBounds.getMaxX(),
                maxY = polygonBounds.getMaxY();

        assertEquals(finalX, maxX, 0.1);
        assertEquals(finalY, maxY, 0.1);

        finalX = -100;
        finalY = -45;
        test.setDim(initialX, initialY, finalX, finalY);
        polygonBounds = innerShape.getBoundsInParent();
        minX = polygonBounds.getMinX();
        minY = polygonBounds.getMinY();

        assertEquals(finalX, minX, 0.1);
        assertEquals(finalY, minY, 0.1);

        System.out.println("Passed");
    }

    /**
     * Test of setWidth method, of class PolygonShape.
     */
    @Test
    public void testSetWidth() {
        System.out.print("setWidth: ");

        test.setWidth(15, 30);
        assertEquals(test.getDimX(), 15, 0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of setHeight method, of class PolygonShape.
     */
    @Test
    public void testSetHeight() {
        System.out.print("setHeight: ");

        test.setHeight(20, 100);
        assertEquals(test.getDimY(), 80, 0.1);

        System.out.println("Passed");
    }

    /**
     * Test of getDimX method, of class PolygonShape.
     */
    @Test
    public void testGetDimX() {
        System.out.print("getDimX: ");

        test.setWidth(15, 30);
        assertEquals(test.getDimX(), 15, 0.1);

        System.out.println("Passed");
    }

    /**
     * Test of getDimY method, of class PolygonShape.
     */
    @Test
    public void testGetDimY() {
        System.out.print("getDimY: ");

        test.setHeight(20, 100);
        assertEquals(test.getDimY(), 80, 0.1);

        System.out.println("Passed");
    }

}
