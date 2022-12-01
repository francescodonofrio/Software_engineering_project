/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

/**
 *
 * @author Marta Corcione
 */
public class ChangeContourColorActionTest {
    
    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private ObjectProperty<Color>  contourColorProperty;
    private  ChangeContourColorAction action;
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
        
        contourColorProperty= new SimpleObjectProperty<>();
        contourColorProperty.set(Color.BLACK);
    }
    
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute");
        
        event = new ActionEvent();
    
        action = new ChangeContourColorAction(rectangleShape,contourColorProperty);
        action.execute(event);
       
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getStroke(), Color.BLACK);
        
        action = new ChangeContourColorAction(ellipseShape,contourColorProperty);
        action.execute(event);
       
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getStroke(), Color.BLACK);
        
        
        
        
        action = new ChangeContourColorAction(lineShape,contourColorProperty);
        action.execute(event);
       
        Line line = (Line) lineShape.getShape();
        assertEquals(line.getStroke(), Color.BLACK);
        
    
    
    }
    
    
    }
    
   
