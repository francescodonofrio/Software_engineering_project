package action;

import exceptions.NotExecutedActionException;
import javafx.event.Event;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class MirrorActionTest {
    
    private ShapeInterface rectangleShape;
    
    public MirrorActionTest() {
        System.out.println("Test MirrorAction");
        
        rectangleShape = new RectangleShape();
        rectangleShape.getShape().setScaleX(1);
        rectangleShape.getShape().setScaleY(1);
    }

    /**
     * Test of execute method, of class MirrorAction.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute: ");
        
        Event event = null;
        
        MirrorAction instanceOfMirror = new MirrorAction(rectangleShape, false, false);
        instanceOfMirror.execute(event);
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, false);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, false, true);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, true);
        instanceOfMirror.execute(event);
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, true);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, true);
        instanceOfMirror.execute(event);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class MirrorAction.
     */
    @Test(expected = NotExecutedActionException.class)
    public void testUndo() throws Exception {
        System.out.println("undo: ");
        
        Event event = null;
        
        MirrorAction instanceOfMirror = new MirrorAction(rectangleShape, false, false);
        instanceOfMirror.execute(event);
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror.undo();
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, false);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror.undo();
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, false, true);
        instanceOfMirror.execute(event);
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror.undo();
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, true, true);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror.undo();
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror = new MirrorAction(rectangleShape, false, true);
        instanceOfMirror.execute(event);
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror = new MirrorAction(rectangleShape, true, false);
        instanceOfMirror.execute(event);
        assertEquals(-1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(-1,rectangleShape.getShape().getScaleY(), 0.1);
        instanceOfMirror.undo();
        instanceOfMirror.undo();
        assertEquals(1,rectangleShape.getShape().getScaleX(), 0.1);
        assertEquals(1,rectangleShape.getShape().getScaleY(), 0.1);
        
        instanceOfMirror.undo();
        
        System.out.println("Passed");
    }
    
}
