package action;

import exceptions.NotCloseContourException;
import exceptions.NotShapeToCutException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CutActionTest {
    
    private final Clipboard clipboard;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ShapeInterface rectangleShape, ellipseShape, lineShape;
    
    
    public CutActionTest() throws NotCloseContourException {
        System.out.println("Test CutAction");
        clipboard = Clipboard.getClipboard();
        
        listInsertedShapes = FXCollections.observableArrayList();
        
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        lineShape = new LineShape();
        rectangleShape.setInternalColor(Color.BLUE);
        rectangleShape.setContourColor(Color.BLACK);
        
        listInsertedShapes.add(rectangleShape);
    }

    /**
     * Test of execute method, of class CutAction.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute: ");
        Event event = null;
        CutAction instance;
        
        instance = new CutAction(clipboard, listInsertedShapes, rectangleShape);
        
        assertEquals(listInsertedShapes.size(), 1);
        
        instance.execute(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        Color colorFillRectangle = (Color) rectangle.getFill();
        Color colorStrokeRectangle = (Color) rectangle.getStroke();
        
        assertNotNull(clipboard);
        assertEquals(listInsertedShapes.size(), 0);
        
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
    
    public void testUndo() throws Exception {
        System.out.println("undo: ");
        
        Event event = null;
        CutAction instance = new CutAction(clipboard, listInsertedShapes, rectangleShape);
        instance.execute(event);
        assertEquals(listInsertedShapes.isEmpty(), true);
        
        instance.undo();
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(rectangleShape), true);
        
        listInsertedShapes.add(rectangleShape);
        listInsertedShapes.add(ellipseShape);
        listInsertedShapes.add(lineShape);
        
        instance = new CutAction(clipboard, listInsertedShapes, rectangleShape);
        instance.execute(event);
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(ellipseShape), true);
        assertEquals(listInsertedShapes.contains(lineShape), true);
        
        instance = new CutAction(clipboard, listInsertedShapes, ellipseShape);
        instance.execute(event);
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(rectangleShape), false);
        assertEquals(listInsertedShapes.contains(ellipseShape), true);
        assertEquals(listInsertedShapes.contains(lineShape), true);
        
        instance = new CutAction(clipboard, listInsertedShapes, lineShape);
        instance.execute(event);
        assertEquals(listInsertedShapes.isEmpty(), true);
        assertEquals(listInsertedShapes.contains(rectangleShape), false);
        assertEquals(listInsertedShapes.contains(ellipseShape), false);
        assertEquals(listInsertedShapes.contains(lineShape), false);
        
        instance.undo();
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(rectangleShape), false);
        assertEquals(listInsertedShapes.contains(ellipseShape), false);
        assertEquals(listInsertedShapes.contains(lineShape), true);
        
        instance.undo();
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(rectangleShape), false);
        assertEquals(listInsertedShapes.contains(ellipseShape), true);
        assertEquals(listInsertedShapes.contains(lineShape), true);
        
        instance.undo();
        assertEquals(listInsertedShapes.isEmpty(), false);
        assertEquals(listInsertedShapes.contains(rectangleShape), true);
        assertEquals(listInsertedShapes.contains(ellipseShape), true);
        assertEquals(listInsertedShapes.contains(lineShape), true);
        
        System.out.println("Passed");
    }
    
}
