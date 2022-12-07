package action;

import exceptions.NoActionsException;
import exceptions.NotExecutedActionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class MoveActionTest {
    private ShapeInterface rectangleShape;
    private MouseEvent event;
    private Rectangle rectangle;
    private MoveAction test;
    private ObservableList<ShapeInterface> listInsertedShapes, focussedShape;
    
    
    public MoveActionTest() {
    }
    
    @Before
    public void setUp() {
        rectangleShape = new RectangleShape();
        rectangleShape.setProperties(0, 0, Color.GREENYELLOW, Color.CHOCOLATE);
        
        rectangle = (Rectangle) rectangleShape.getShape();                
        event = new MouseEvent(null, rectangle, MouseEvent.MOUSE_CLICKED,100, 100, 100, 100, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        listInsertedShapes=FXCollections.observableArrayList();
        listInsertedShapes.add(rectangleShape);
        
        focussedShape=FXCollections.observableArrayList();
        test=new MoveAction(focussedShape,listInsertedShapes);
    }

    /**
     * Test of execute method, of class MoveAction.
     */
    @Test
    public void testExecute() {
        System.out.print("execute: ");
        
        double startX=rectangle.getLayoutX(),
               startY=rectangle.getLayoutY();
        
        Invoker invoker=new Invoker();
        
        invoker.execute(test, event);
        
        assertEquals(focussedShape.size(),1);
        
        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class MoveAction.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.print("onMouseDragged: ");
        
        double startX=rectangle.getLayoutX(),
               startY=rectangle.getLayoutY();
        focussedShape.add(rectangleShape);

        Invoker invoker=new Invoker();
        
        invoker.executeOnMouseDragged(test, event);
        
        assertEquals(rectangle.getLayoutX(),event.getX(),0.1);
        assertEquals(rectangle.getLayoutY(),event.getY(),0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of onMouseReleased method, of class MoveAction.
     * @throws java.lang.Exception
     */
    @Test
    public void testOnMouseReleased() throws Exception {
        System.out.print("onMouseReleased: ");
        
        double startX=rectangle.getLayoutX(),
               startY=rectangle.getLayoutY();
        focussedShape.add(rectangleShape);

        Invoker invoker=new Invoker();
        
        invoker.executeOnMouseDragged(test, event);
        
        assertEquals(rectangle.getLayoutX(),event.getX(),0.1);
        assertEquals(rectangle.getLayoutY(),event.getY(),0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class MoveAction.
     * @throws exceptions.NoActionsException
     */
    @Test(expected =NoActionsException.class)
    public void testUndo() throws NoActionsException, NotExecutedActionException {
        System.out.print("execute: ");
        
        double startX=rectangle.getLayoutX(),
               startY=rectangle.getLayoutY();
        
        Invoker invoker=new Invoker();
        
        invoker.execute(test, event);      
        invoker.executeOnMouseDragged(test, event);
        
        invoker.undo();
        
        assertEquals(startX,rectangle.getLayoutX(),0.1);
        assertEquals(startY,rectangle.getLayoutY(),0.1);
        
        invoker.undo();

        System.out.println("Passed");
    }
    
}
