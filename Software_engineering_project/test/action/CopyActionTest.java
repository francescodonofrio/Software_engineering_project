package action;

import exceptions.NotCloseContourException;
import exceptions.NotExecutedActionException;
import javafx.event.Event;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.RectangleShape;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CopyActionTest {
    
    private final Clipboard clipboard;
    private final ShapeInterface rectangleShape;
    
    public CopyActionTest() throws NotCloseContourException {
        System.out.println("Test CopyAction");
        clipboard = Clipboard.getClipboard();
        clipboard.setContent(null);
        
        rectangleShape = new RectangleShape();
        rectangleShape.setInternalColor(Color.BLUE);
        rectangleShape.setContourColor(Color.BLACK);
    }

    /**
     * Test of execute method, of class CopyAction.
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute: ");
        Event event = null;
        CopyAction instance;
        
        instance = new CopyAction(clipboard, rectangleShape);
        instance.execute(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        Color colorFillRectangle = (Color) rectangle.getFill();
        Color colorStrokeRectangle = (Color) rectangle.getStroke();
        
        assertNotNull(clipboard);
        Rectangle clipboardRectangle = (Rectangle) clipboard.getContent().getShape();
        
        assertEquals(clipboardRectangle.getLayoutX(), rectangle.getLayoutX(), 0.1);
        assertEquals(clipboardRectangle.getLayoutY(), rectangle.getLayoutY(), 0.1);
        assertEquals(clipboardRectangle.getHeight(), rectangle.getHeight(), 0.1);
        assertEquals(clipboardRectangle.getWidth(), rectangle.getWidth(), 0.1);
        assertEquals(colorFillRectangle.getRed(), Color.BLUE.getRed(), 0.1);
        assertEquals(colorFillRectangle.getGreen(), Color.BLUE.getGreen(), 0.1);
        assertEquals(colorFillRectangle.getBlue(), Color.BLUE.getBlue(), 0.1);
        assertEquals(colorFillRectangle.getOpacity(), Color.BLUE.getOpacity(), 0.1);
        assertEquals(colorStrokeRectangle.getRed(), Color.BLACK.getRed(), 0.1);
        assertEquals(colorStrokeRectangle.getGreen(), Color.BLACK.getGreen(), 0.1);
        assertEquals(colorStrokeRectangle.getBlue(), Color.BLACK.getBlue(), 0.1);
        assertEquals(colorStrokeRectangle.getOpacity(), Color.BLACK.getOpacity(), 0.1);        
        
        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class CopyAction.
     * @throws exceptions.NotShapeToCopyException
     * @throws exceptions.NotExecutedActionException
     */
    @Test(expected=NotExecutedActionException.class)
    public void testUndo() throws  NotExecutedActionException, Exception  {
        System.out.print("undo: ");
        Event event = null;
        CopyAction instance;

        instance = new CopyAction(clipboard, rectangleShape);
        boolean oldValue=clipboard.hasContent().get();

        instance.execute(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();


        instance.undo();

        assertEquals(oldValue,clipboard.hasContent().get());

        oldValue=clipboard.hasContent().get();
        instance.execute(event);
        instance.execute(event);

        assertNotEquals(!oldValue,clipboard.hasContent().get());
        
        instance.undo();
        instance.undo();

        System.out.println("Passed");
    }
}
