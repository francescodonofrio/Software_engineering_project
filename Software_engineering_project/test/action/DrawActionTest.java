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

public class DrawActionTest {

    private double internalRed, internalGreen, internalBlue, internalOpacity, contourGreen, contourRed, contourBlue, contourOpacity, width, height, radiusX, radiusY, startX, startY, endX, endY;
    private Shape rectangleShape, ellipseShape, lineShape;
    private Color internalColor, contourColor;
    private Pane drawingPane;

    public DrawActionTest() {
        System.out.println("Test DrawAction");
    }

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
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        height = rectangle.getHeight();
        width = rectangle.getWidth();

        ellipseShape = new EllipseShape();
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        radiusX = ellipse.getRadiusX();
        radiusY = ellipse.getRadiusY();

        lineShape = new LineShape();
        Line line = (Line) lineShape.getShape();
        startX = line.getStartX();
        startY = line.getStartY();
        endX = line.getEndX();
        endY = line.getEndY();

        internalColor = new Color(internalRed, internalGreen, internalBlue, internalOpacity);
        contourColor = new Color(contourRed, contourGreen, contourBlue, contourOpacity);
        drawingPane = new Pane();
    }

    /**
     * Test of execute method, of class DrawAction.
     */
    @Test
    public void testExecute() {
        System.out.print("draw :");

        DrawAction instanceRectangle = new DrawAction(rectangleShape, 100, 150, internalColor, contourColor, drawingPane);
        instanceRectangle.execute();
        Rectangle rectangleRetrieved = (Rectangle) drawingPane.getChildren().get(0);
        Color colorFillRectangle = (Color) rectangleRetrieved.getFill();
        Color colorStrokeRectangle = (Color) rectangleRetrieved.getStroke();

        assertEquals(rectangleRetrieved.getLayoutX(), 100, 0.1);
        assertEquals(rectangleRetrieved.getLayoutY(), 150, 0.1);
        assertEquals(rectangleRetrieved.getHeight(), height, 0.1);
        assertEquals(rectangleRetrieved.getWidth(), width, 0.1);
        assertEquals(colorFillRectangle.getRed(), internalRed, 0.1);
        assertEquals(colorFillRectangle.getGreen(), internalGreen, 0.1);
        assertEquals(colorFillRectangle.getBlue(), internalBlue, 0.1);
        assertEquals(colorFillRectangle.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStrokeRectangle.getRed(), contourRed, 0.1);
        assertEquals(colorStrokeRectangle.getGreen(), contourGreen, 0.1);
        assertEquals(colorStrokeRectangle.getBlue(), contourBlue, 0.1);
        assertEquals(colorStrokeRectangle.getOpacity(), contourOpacity, 0.1);

        DrawAction instanceEllipse = new DrawAction(ellipseShape, 180, 200, internalColor, contourColor, drawingPane);
        instanceEllipse.execute();
        Ellipse shapeRetrievedEllipse = (Ellipse) drawingPane.getChildren().get(1);
        Color colorFillEllipse = (Color) shapeRetrievedEllipse.getFill();
        Color colorStrokeEllipse = (Color) shapeRetrievedEllipse.getStroke();

        assertEquals(shapeRetrievedEllipse.getLayoutX(), 180, 0.1);
        assertEquals(shapeRetrievedEllipse.getLayoutY(), 200, 0.1);
        assertEquals(shapeRetrievedEllipse.getRadiusX(), radiusX, 0.1);
        assertEquals(shapeRetrievedEllipse.getRadiusY(), radiusY, 0.1);
        assertEquals(colorFillEllipse.getRed(), internalRed, 0.1);
        assertEquals(colorFillEllipse.getGreen(), internalGreen, 0.1);
        assertEquals(colorFillEllipse.getBlue(), internalBlue, 0.1);
        assertEquals(colorFillEllipse.getOpacity(), internalOpacity, 0.1);
        assertEquals(colorStrokeEllipse.getRed(), contourRed, 0.1);
        assertEquals(colorStrokeEllipse.getGreen(), contourGreen, 0.1);
        assertEquals(colorStrokeEllipse.getBlue(), contourBlue, 0.1);
        assertEquals(colorStrokeEllipse.getOpacity(), contourOpacity, 0.1);

        DrawAction instanceLine = new DrawAction(lineShape, 100, 150, internalColor, contourColor, drawingPane);
        instanceLine.execute();
        Line shapeRetrievedLine = (Line) drawingPane.getChildren().get(2);
        Color colorStrokeLine = (Color) shapeRetrievedLine.getStroke();

        assertEquals(shapeRetrievedLine.getLayoutX(), 100, 0.1);
        assertEquals(shapeRetrievedLine.getLayoutY(), 150, 0.1);
        assertEquals(shapeRetrievedLine.getStartX(), startX, 0.1);
        assertEquals(shapeRetrievedLine.getStartY(), startY, 0.1);
        assertEquals(shapeRetrievedLine.getEndX(), endX, 0.1);
        assertEquals(shapeRetrievedLine.getEndY(), endY, 0.1);
        assertEquals(colorStrokeLine.getRed(), contourRed, 0.1);
        assertEquals(colorStrokeLine.getGreen(), contourGreen, 0.1);
        assertEquals(colorStrokeLine.getBlue(), contourBlue, 0.1);
        assertEquals(colorStrokeLine.getOpacity(), contourOpacity, 0.1);

        System.out.println("Passed");
    }

}
