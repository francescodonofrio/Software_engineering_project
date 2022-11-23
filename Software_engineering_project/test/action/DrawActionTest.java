/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javafx.scene.layout.Pane;
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
import shapes.Shape;

/**
 *
 * @author franc
 */
public class DrawActionTest {
    
    private double internalRed, internalGreen, internalBlue, internalOpacity, contourGreen, contourRed, contourBlue, contourOpacity , width, height, radiusX, radiusY, startX, startY, endX, endY;
    private Shape rectangleShape, ellipseShape, lineShape;
    private Color internalColor, contourColor;
    private Pane drawingPane;
    
    @Before
    public void setUp() {
        
        internalRed = 0.5;
        internalGreen = 0.2;
        internalBlue = 0.15;
        internalOpacity = 0.2;
        contourRed = 0.8;
        contourGreen = 0.1;
        contourBlue = 0.15;
        contourOpacity = 0.2;
        
        // Shapes set up
        rectangleShape = new RectangleShape();
        Rectangle rectangle = (Rectangle)rectangleShape.getShape();
        height = rectangle.getHeight();
        width = rectangle.getWidth();
        
        ellipseShape = new EllipseShape();
        Ellipse ellipse = (Ellipse)ellipseShape.getShape();
        radiusX = ellipse.getRadiusX();
        radiusY = ellipse.getRadiusY();
        
        lineShape = new LineShape();
        Line line = (Line)lineShape.getShape();
        startX = line.getStartX();
        startY = line.getStartY();
        endX = line.getEndX();
        endY = line.getEndY();

        internalColor = new Color(internalRed, internalGreen, internalBlue, internalOpacity);
        contourColor = new Color(contourRed, contourGreen, contourBlue, contourOpacity);
        drawingPane = new Pane();
    }

    /**
     * Test of execute method, of class DrawAction, using an instance of RectangleShape.
     */
    @Test
    public void testExecuteRectangle() {
        System.out.println("execute");
        
        DrawAction instance = new DrawAction(rectangleShape, 100, 150, internalColor, contourColor, drawingPane);
        instance.execute();
        Rectangle shapeRetrieved = (Rectangle)drawingPane.getChildren().get(0);
        Color colorFill = (Color)shapeRetrieved.getFill();
        Color colorStroke = (Color)shapeRetrieved.getStroke();
        
        assertEquals(shapeRetrieved.getLayoutX(),100, 0.1);
        assertEquals(shapeRetrieved.getLayoutY(),150, 0.1);
        assertEquals(shapeRetrieved.getHeight(), height, 0.1);
        assertEquals(shapeRetrieved.getWidth(), width, 0.1);
        assertEquals(colorFill.getRed(), internalRed, 0.1);
        assertEquals(colorFill.getGreen(), internalGreen, 0.1);
        assertEquals(colorFill.getBlue(), internalBlue, 0.1);
        assertEquals(colorFill.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStroke.getRed(), contourRed, 0.1);
        assertEquals(colorStroke.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke.getOpacity(), contourOpacity, 0.1);
        
    }
    
    /**
    * Test of execute method, of class DrawAction, using an instance of EllipseShape.
    */
    @Test
    public void testExecuteEllipse() {
        System.out.println("execute");
        
        DrawAction instance = new DrawAction(ellipseShape, 180, 200, internalColor, contourColor, drawingPane);
        instance.execute();
        Ellipse shapeRetrieved = (Ellipse)drawingPane.getChildren().get(0);
        Color colorFill = (Color)shapeRetrieved.getFill();
        Color colorStroke = (Color)shapeRetrieved.getStroke();
        
        assertEquals(shapeRetrieved.getLayoutX(),180, 0.1);
        assertEquals(shapeRetrieved.getLayoutY(),200, 0.1);
        assertEquals(shapeRetrieved.getRadiusX(), radiusX, 0.1);
        assertEquals(shapeRetrieved.getRadiusY(), radiusY, 0.1);
        assertEquals(colorFill.getRed(), internalRed, 0.1);
        assertEquals(colorFill.getGreen(), internalGreen, 0.1);
        assertEquals(colorFill.getBlue(), internalBlue, 0.1);
        assertEquals(colorFill.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStroke.getRed(), contourRed, 0.1);
        assertEquals(colorStroke.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke.getOpacity(), contourOpacity, 0.1);
        
    }
    
    /**
    * Test of execute method, of class DrawAction,  using an instance of LineShape.
    */
    @Test
    public void testExecuteLineSegment() {
        System.out.println("execute");
        
        DrawAction instance = new DrawAction(lineShape, 100, 150, internalColor, contourColor, drawingPane);
        instance.execute();
        Line shapeRetrieved = (Line)drawingPane.getChildren().get(0);
        Color colorStroke = (Color)shapeRetrieved.getStroke();
        
        assertEquals(shapeRetrieved.getLayoutX(),100, 0.1);
        assertEquals(shapeRetrieved.getLayoutY(),150, 0.1);
        assertEquals(shapeRetrieved.getStartX(), startX, 0.1);
        assertEquals(shapeRetrieved.getStartY(), startY, 0.1);
        assertEquals(shapeRetrieved.getEndX(), endX, 0.1);
        assertEquals(shapeRetrieved.getEndY(), endY, 0.1);
        assertEquals(colorStroke.getRed(), contourRed, 0.1);
        assertEquals(colorStroke.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke.getOpacity(), contourOpacity, 0.1);
        
    }
    
    /**
    * Test of execute method, of class DrawAction,  using an instance of LineShape.
    */
    @Test
    public void testExecuteMultipleShapes() {
        System.out.println("execute");
        
        DrawAction instance1 = new DrawAction(lineShape, 100, 150, internalColor, contourColor, drawingPane);
        instance1.execute();
        
        DrawAction instance2 = new DrawAction(rectangleShape, 130, 220, internalColor, contourColor, drawingPane);
        instance2.execute();
        
        DrawAction instance3 = new DrawAction(ellipseShape, 180, 200, internalColor, contourColor, drawingPane);
        instance3.execute();
        
        Line shapeRetrieved1 = (Line)drawingPane.getChildren().get(0);
        Color colorStroke1 = (Color)shapeRetrieved1.getStroke();
        
        Rectangle shapeRetrieved2 = (Rectangle)drawingPane.getChildren().get(1);
        Color colorFill2 = (Color)shapeRetrieved2.getFill();
        Color colorStroke2 = (Color)shapeRetrieved2.getStroke();
        
        Ellipse shapeRetrieved3 = (Ellipse)drawingPane.getChildren().get(2);
        Color colorFill3 = (Color)shapeRetrieved3.getFill();
        Color colorStroke3 = (Color)shapeRetrieved3.getStroke();
        
        assertEquals(shapeRetrieved1.getLayoutX(),100, 0.1);
        assertEquals(shapeRetrieved1.getLayoutY(),150, 0.1);
        assertEquals(shapeRetrieved1.getStartX(), startX, 0.1);
        assertEquals(shapeRetrieved1.getStartY(), startY, 0.1);
        assertEquals(shapeRetrieved1.getEndX(), endX, 0.1);
        assertEquals(shapeRetrieved1.getEndY(), endY, 0.1);
        assertEquals(colorStroke1.getRed(), contourRed, 0.1);
        assertEquals(colorStroke1.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke1.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke1.getOpacity(), contourOpacity, 0.1);
        
        assertEquals(shapeRetrieved2.getLayoutX(),130, 0.1);
        assertEquals(shapeRetrieved2.getLayoutY(),220, 0.1);
        assertEquals(shapeRetrieved2.getHeight(), height, 0.1);
        assertEquals(shapeRetrieved2.getWidth(), width, 0.1);
        assertEquals(colorFill2.getRed(), internalRed, 0.1);
        assertEquals(colorFill2.getGreen(), internalGreen, 0.1);
        assertEquals(colorFill2.getBlue(), internalBlue, 0.1);
        assertEquals(colorFill2.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStroke2.getRed(), contourRed, 0.1);
        assertEquals(colorStroke2.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke2.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke2.getOpacity(), contourOpacity, 0.1);
        
        assertEquals(shapeRetrieved3.getLayoutX(),180, 0.1);
        assertEquals(shapeRetrieved3.getLayoutY(),200, 0.1);
        assertEquals(shapeRetrieved3.getRadiusX(), radiusX, 0.1);
        assertEquals(shapeRetrieved3.getRadiusY(), radiusY, 0.1);
        assertEquals(colorFill3.getRed(), internalRed, 0.1);
        assertEquals(colorFill3.getGreen(), internalGreen, 0.1);
        assertEquals(colorFill3.getBlue(), internalBlue, 0.1);
        assertEquals(colorFill3.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStroke3.getRed(), contourRed, 0.1);
        assertEquals(colorStroke3.getGreen(), contourGreen, 0.1);
        assertEquals(colorStroke3.getBlue(), contourBlue, 0.1);
        assertEquals(colorStroke3.getOpacity(), contourOpacity, 0.1);
        
    }
    
}
