package action;

import exceptions.NotExecutedActionException;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import shapes.EllipseShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class ToBackActionTest {
    
    private ShapeInterface rectangleShape,ellipseShape;
    private ActionEvent event;
    private ToBackAction action;
    private Pane drawingPane;
    private int startPositionRectangle,startPositionEllipse;
    
    public ToBackActionTest() {
        System.out.println("Test ToFrontAction");
    }
    
    @Before
    public void setUp() {
        // Shapes set up
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        
        drawingPane= new Pane();
        drawingPane.getChildren().add(rectangleShape.getShape());
        drawingPane.getChildren().add(ellipseShape.getShape()); 
        
        startPositionRectangle = drawingPane.getChildren().indexOf(rectangleShape.getShape());
        startPositionEllipse = drawingPane.getChildren().indexOf(ellipseShape.getShape());
        
        event = new ActionEvent();
    }
    
    /**
     * Test of execute method, of class ToBakAction.
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute");
        
        action = new ToBackAction(rectangleShape,drawingPane.getChildren());
        action.execute(event);
        
        assertEquals(drawingPane.getChildren().indexOf(rectangleShape.getShape()),0);
        
        System.out.println("Passed");
    }
    
    /**
     * Test of undo method, of class ToBackAction.
     * @throws exceptions.NotExecutedActionException
     */
    @Test(expected=NotExecutedActionException.class)
    public void testUndo() throws NotExecutedActionException, Exception  {
        System.out.print("execute");
        
        action = new ToBackAction(rectangleShape,drawingPane.getChildren());
        action.execute(event);
        
        action.undo();
        
        assertEquals(drawingPane.getChildren().indexOf(rectangleShape.getShape()),startPositionRectangle);
        assertEquals(drawingPane.getChildren().indexOf(ellipseShape.getShape()),startPositionEllipse);
        
        action.undo();
        
        System.out.println("Passed");
    }
    
}
