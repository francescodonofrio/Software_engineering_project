package action;

import exceptions.NotCloseContourException;
import exceptions.NotExecutedActionException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.RectangleShape;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class PasteActionTest {
    
    private final Clipboard clipboard;
    private final ShapeInterface rectangleShape;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private Pane drawingPane;
    private MouseEvent event;
    
    public PasteActionTest() throws NotCloseContourException {
        System.out.println("Test PasteAction");
        clipboard = Clipboard.getClipboard();
        
        rectangleShape = new RectangleShape();
        rectangleShape.setInternalColor(Color.BLUE);
        rectangleShape.setContourColor(Color.BLACK);
        
        clipboard.setContent(rectangleShape);
        
        drawingPane = new Pane();
        
        listInsertedShapes = FXCollections.observableArrayList();
        listInsertedShapes.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while(change.next()){
                change.getRemoved().forEach(remItem -> {
                    drawingPane.getChildren().remove(remItem.getShape());
                });
                change.getAddedSubList().forEach(addItem -> {
                    drawingPane.getChildren().add(addItem.getShape());
                });
            }
        });
    }

    /**
     * Test of execute method, of class PasteAction.
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception{
        System.out.print("execute: ");
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        
        PasteAction instance = new PasteAction(clipboard, listInsertedShapes);
        instance.execute(event);
        
        Rectangle rectangleRetrieved = (Rectangle) drawingPane.getChildren().get(0);
        Color colorFillRectangle = (Color) rectangleRetrieved.getFill();
        Color colorStrokeRectangle = (Color) rectangleRetrieved.getStroke();
        
        Rectangle clipboardRectangle = (Rectangle) clipboard.getContent().getShape();
        
        assertEquals(rectangleRetrieved.getLayoutX(), event.getX(), 0.1);
        assertEquals(rectangleRetrieved.getLayoutY(), event.getY(), 0.1);
        assertEquals(rectangleRetrieved.getHeight(), clipboardRectangle.getHeight(), 0.1);
        assertEquals(rectangleRetrieved.getWidth(), clipboardRectangle.getWidth(), 0.1);
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
     * Test of undo method, of class PasteAction.
     * @throws exceptions.NotExecutedActionException
     * @throws java.lang.Exception
     */
    @Test(expected=NotExecutedActionException.class)
    public void undo() throws NotExecutedActionException, Exception{
        System.out.print("undo: ");
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        int initialSize=drawingPane.getChildren().size();
        PasteAction instance = new PasteAction(clipboard, listInsertedShapes);
        instance.execute(event);
        instance.undo();
        
        assertEquals(drawingPane.getChildren().size(),initialSize);
        drawingPane.getChildren().clear();
        
        initialSize=drawingPane.getChildren().size();
        instance.execute(event);
        instance.execute(event);
        
        instance.undo();
        assertEquals(drawingPane.getChildren().size(),initialSize+1);
        
        instance.undo();
        
        System.out.println("Passed");
    }
    
}
