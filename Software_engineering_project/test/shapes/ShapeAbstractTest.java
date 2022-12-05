package shapes;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
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

        double newX = 50, newY = 60;
        shape.move(newX, newY);
        assertEquals(test.getLayoutX(), newX, 0.1);
        assertEquals(test.getLayoutY(), newY, 0.1);


        System.out.println("Passed");
    }
    
    /**
     * Test of setProperties method, of class ShapeAbstract.
     */
    @Test
    public void testSetProperties() {
        System.out.print("setProperties: ");

        shape.setProperties(20, 100, Color.GREENYELLOW, Color.CHOCOLATE);
        
        assertEquals(shape.getShape().getLayoutX(),20,0.1);
        assertEquals(shape.getShape().getLayoutY(),100,0.1);
        assertEquals(shape.getInternalColor().getBlue(),Color.GREENYELLOW.getBlue(),0.1);
        assertEquals(shape.getInternalColor().getRed(),Color.GREENYELLOW.getRed(),0.1);
        assertEquals(shape.getInternalColor().getGreen(),Color.GREENYELLOW.getGreen(),0.1);
        assertEquals(shape.getInternalColor().getOpacity(),Color.GREENYELLOW.getOpacity(),0.1);
        assertEquals(shape.getCountourColor().getBlue(),Color.CHOCOLATE.getBlue(),0.1);
        assertEquals(shape.getCountourColor().getRed(),Color.CHOCOLATE.getRed(),0.1);
        assertEquals(shape.getCountourColor().getGreen(),Color.CHOCOLATE.getGreen(),0.1);
        assertEquals(shape.getCountourColor().getOpacity(),Color.CHOCOLATE.getOpacity(),0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of setInternalColor method, of class ShapeAbstract.
     */
    @Test
    public void testSetInternalColor() {
        System.out.print("setInternalColor: ");

        shape.setInternalColor(Color.YELLOW);
        
        assertEquals(shape.getInternalColor().getBlue(),Color.YELLOW.getBlue(),0.1);
        assertEquals(shape.getInternalColor().getRed(),Color.YELLOW.getRed(),0.1);
        assertEquals(shape.getInternalColor().getGreen(),Color.YELLOW.getGreen(),0.1);
        assertEquals(shape.getInternalColor().getOpacity(),Color.YELLOW.getOpacity(),0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of setContourColor method, of class ShapeAbstract.
     */
    @Test
    public void testsetContourColor() {
        System.out.print("setContourColor: ");
        
        shape.setContourColor(Color.YELLOW);
        
        assertEquals(shape.getCountourColor(),Color.YELLOW);

        System.out.println("Passed");
    }

    /**
     * Test of getContourColor method, of class ShapeAbstract.
     */
    @Test
    public void testGetContourColor() {
        System.out.print("getContourColor: ");
        
        shape.setContourColor(Color.YELLOW);
        
        assertEquals(shape.getCountourColor(),Color.YELLOW);

        System.out.println("Passed");
    }

    /**
     * Test of setName method, of class ShapeAbstract.
     */
    @Test
    public void testSetName() {
        System.out.print("setName: ");
        
        shape.setName("Test");
        
        assertEquals(shape.getName(),"Test");

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
        public void setInternalColor(Color newColor){
            shape.setFill(newColor);
        }
    }
}
