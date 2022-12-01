package shapes;

import exceptions.NotCloseContourException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeAbstractTest {
    
    private Shape test;
    private MockShape shape;

    public ShapeAbstractTest() {
        System.out.println("Test ShapeAbstract");
    }

    @Before
    public void setUp() {
        this.test = new Rectangle();
        this.shape = new MockShape(this.test);
    }

    /**
     * Test of getShape method, of class ShapeAbstract.
     */
    @Test
    public void testGetShape() {
        System.out.print("getShape: ");

        assertEquals(test, shape.getShape());

        System.out.println("Passed");
    }
   
    /**
     * Test of setShape method, of class ShapeAbstract.
     */
    @Test
    public void testSetShape() {
        System.out.print("setShape: ");
        Shape testLine = new Line();
        shape.setShape(testLine);
        assertEquals(testLine, shape.getShape());

        System.out.println("Passed");
    }
    
    /**
     * Test of getName method, of class ShapeAbstract.
     */
    @Test
    public void testGetName() {
        System.out.print("getName: ");

        assertEquals("Rectangle1", shape.getName());

        System.out.println("Passed");
    }    

    /**
     * Test of setX method, of class ShapeAbstract.
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
     * Test of setY method, of class ShapeAbstract.
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
     * Test of move method, of class ShapeAbstract.
     */
    @Test
    public void testMove() {
        System.out.print("move: ");

        double newX=50, newY=60;
        shape.move(newX, newY);
        assertEquals(test.getLayoutX(), newX, 0.1);
        assertEquals(test.getLayoutY(), newY, 0.1);


        System.out.println("Passed");
    }

    public class MockShape extends ShapeAbstract {
        public MockShape(Shape shape) {
            this.shape = shape;
            this.name = "Rectangle1";
        }
        
        @Override
        public void setDim(double initialX, double initialY, double finalX, double finalY) {
        }

        @Override
        public void setInternalColor(Color newColor) throws NotCloseContourException {
        }

        @Override
        public void setProperties(double X, double Y, Color internalColor, Color contourColor) {
        }
        
        @Override
        public void setShape(Shape shape){
            this.shape = shape;
        }
        
        @Override
        public String getName(){
            return this.name;
        }
    }
    
}
