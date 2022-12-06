package action;

import exceptions.NoActionsException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class ChangeContourColorActionTest {

    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private ObjectProperty<Color> contourColorProperty, startColor, contourColorUndo;
    private ChangeContourColorAction action;
    private ActionEvent event;

    public ChangeContourColorActionTest() {
        System.out.println("Test DrawAction");
    }

    @Before
    public void setUp() {
        // Shapes set up
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        lineShape = new LineShape();

        contourColorProperty = new SimpleObjectProperty<>();
        contourColorProperty.set(Color.AQUA);
        
        event = new ActionEvent();
    }

    @Test
    public void testExecute() throws Exception {
        System.out.print("execute");

        action = new ChangeContourColorAction(rectangleShape, contourColorProperty);
        action.execute(event);

        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getStroke(), Color.AQUA);

        action = new ChangeContourColorAction(ellipseShape, contourColorProperty);
        action.execute(event);

        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getStroke(), Color.AQUA);


        action = new ChangeContourColorAction(lineShape, contourColorProperty);
        action.execute(event);

        Line line = (Line) lineShape.getShape();
        assertEquals(line.getStroke(), Color.AQUA);
        
    }
    
    /**
     * Test of undo method, of class ChangeContourColorAction.
     * 
     */
    @Test(expected =NoActionsException.class)
    public void testUndo() throws Exception  {
        System.out.print("undo: ");
        
        action = new ChangeContourColorAction(rectangleShape, contourColorProperty);
        action.execute(event);
        Color startColor = rectangleShape.getCountourColor();
        
        contourColorUndo = new SimpleObjectProperty<>();
        contourColorUndo.set(Color.BURLYWOOD);
        
        action = new ChangeContourColorAction(rectangleShape, contourColorUndo);
        action.execute(event);
        
        Invoker invoker=new Invoker();
        
        invoker.execute(action, event);      
        
        invoker.undo();
        
        assertEquals(startColor.getGreen(),rectangleShape.getCountourColor().getGreen(),0.1);
        assertEquals(startColor.getBlue(),rectangleShape.getCountourColor().getBlue(),0.1);
        assertEquals(startColor.getRed(),rectangleShape.getCountourColor().getRed(),0.1);
        
        invoker.undo();

        System.out.println("Passed");
    }


}
    
   
