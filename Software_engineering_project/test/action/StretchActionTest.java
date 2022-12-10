package action;

import exceptions.NotExecutedActionException;
import exceptions.NotStretchedException;
import exceptions.ShapeNullException;
import exceptions.ShapeWithNullWidthException;
import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class StretchActionTest {
    
    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private Event event;
    private Action instance;
    private double initialX, width, halfWidth, stretchOffset;
    
    public StretchActionTest() {
        System.out.println("Test StretchAction");
    }
    
    @Before
    public void setUp() {
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        lineShape = new LineShape();
        
        rectangleShape.setDim(15, 30, 50, 60);
        ellipseShape.setDim(40, 50, 100, 160);
        lineShape.setDim(150, 320, 500, 260);
    }

    /**
     * Test of execute method, of class StretchAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=ShapeWithNullWidthException.class)
    public void testExecute() throws ShapeNullException, Exception {
        System.out.println("execute");
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(rectangleShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(5-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(5 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(ellipseShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(10-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(10 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(lineShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(50-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(50 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(new RectangleShape());
        
        instance.execute(event);
        
        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class StretchAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=ShapeWithNullWidthException.class)
    public void testOnMouseDragged() throws ShapeNullException, Exception {
        System.out.println("onMouseDragged");
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(rectangleShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(5-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(5 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(ellipseShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(10-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(10 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(lineShape);
        width = rectangleShape.getDimX();
        halfWidth = width/2;
        stretchOffset = Math.abs((2*(50-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        instance.execute(event);
        if(50 > -Math.abs(width))
            assertEquals(rectangleShape.getShape().getScaleX(), 1, 0.1);
        else
            assertEquals(rectangleShape.getShape().getScaleX(), stretchOffset, 0.1);
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(new RectangleShape());
        
        instance.execute(event);
        
        System.out.println("Passed");
        
    }
    
    /**
     * Test of constructor method, of class StretchAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=ShapeNullException.class)
    public void testConstructorWithNullShape() throws ShapeNullException, Exception {
        System.out.println("constructor with null parameter: ");
        
        instance = new StretchAction(null);
        
        System.out.println("Passed");
    }

    /**
     * Test of onMouseReleased method, of class StretchAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=NotStretchedException.class)
    public void testOnMouseReleased() throws ShapeNullException, Exception {
        System.out.println("onMouseReleased");
        
        event = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(rectangleShape);
        
        instance.onMouseReleased(event);
        
        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class StretchAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=NotExecutedActionException.class)
    public void testUndo() throws ShapeNullException, Exception {
        System.out.println("undo");
        
        event = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 0, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new StretchAction(lineShape);
        
        double scaleX = lineShape.getScale().getX();
        instance.execute(event);
        instance.undo();
        
        assertEquals(lineShape.getScale().getX(), scaleX, 0.1);
        
        instance.undo();
        
        System.out.println("Passed");
    }
    
}
