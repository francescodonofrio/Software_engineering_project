package shapes;

import javafx.scene.shape.Line;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineShapeTest {

    public LineShapeTest() {
        System.out.println("Test LineShape");
    }

    /**
     * Test of setDim method, of class LineShape.
     */
    @Test
    public void testSetDim() {
        System.out.print("setDim: ");

        double initialDim1 = 70.0;
        double initialDim2 = 130.0;
        double finalDim1 = 150.0;
        double finalDim2 = 300.0;
        double endX = finalDim1 - initialDim1;
        double endY = finalDim2 - initialDim2;

        LineShape instance = new LineShape();
        javafx.scene.shape.Line shape = (javafx.scene.shape.Line) instance.getShape();

        // Test using finalDim1 > initialDim1 && finalDim2 > initialDim2
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using finalDim1 < initialDim1 && finalDim2 > initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 300.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using finalDim1 > initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using finalDim1 < initialDim1 && finalDim2 < initialDim2
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && finalDim2 < initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 130.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && finalDim2 > initialDim2        
        initialDim1 = 0.0;
        initialDim2 = 90.0;
        finalDim1 = 150.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using finalDim1 < initialDim1 && initialDim2 = 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using finalDim1 > initialDim1 && initialDim2 = 0        
        initialDim1 = 70.0;
        initialDim2 = 0.0;
        finalDim1 = 85.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && initialDim2 = 0        
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 > 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = 110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && finalDim1 < 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = -50.0;
        finalDim2 = -110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        // Test using initialDim1 = 0 && finalDim1 > 0 && initialDim2 = 0 && finalDim2 < 0            
        initialDim1 = 0.0;
        initialDim2 = 0.0;
        finalDim1 = 50.0;
        finalDim2 = -110.0;
        endX = finalDim1 - initialDim1;
        endY = finalDim2 - initialDim2;
        instance.setDim(initialDim1, initialDim2, finalDim1, finalDim2);

        assertEquals(shape.getEndX(), endX, 0.1);
        assertEquals(shape.getEndY(), endY, 0.1);

        System.out.println("passed");
    }
}
