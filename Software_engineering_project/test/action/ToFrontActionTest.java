/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import shapes.EllipseShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

/**
 *
 * @author Marta Corcione
 */
public class ToFrontActionTest {
    
    private ShapeInterface rectangleShape,ellipseShape;
    private ActionEvent event;
    private ToFrontAction action;
    private Pane drawingPane;
    private int startPositionRectangle,startPositionEllipse;
    
    public ToFrontActionTest() {
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
     * Test of execute method, of class ToFrontAction.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute");
        
        action = new ToFrontAction(rectangleShape,drawingPane.getChildren());
        action.execute(event);
        
        assertEquals(drawingPane.getChildren().indexOf(rectangleShape.getShape()),drawingPane.getChildren().size()-1);
        
        System.out.println("Passed");
    }
    
    /**
     * Test of undo method, of class ToFrontAction.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.print("execute");
        
        action = new ToFrontAction(rectangleShape,drawingPane.getChildren());
        action.execute(event);
        
        action.undo();
        
        assertEquals(drawingPane.getChildren().indexOf(rectangleShape.getShape()),startPositionRectangle);
        assertEquals(drawingPane.getChildren().indexOf(ellipseShape.getShape()),startPositionEllipse);
        
        System.out.println("Passed");
    }
    
}
