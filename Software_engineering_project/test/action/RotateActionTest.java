package action;

import exceptions.NotExecutedActionException;
import exceptions.NotRotatedException;
import exceptions.ShapeNullException;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class RotateActionTest {
    
    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private Event event;
    private double topLeftPointRectangleX, topLeftPointRectangleY, bottomRightPointRectangleX, bottomRightPointRectangleY;
    private double topLeftPointEllipseX, topLeftPointEllipseY, bottomRightPointEllipseX, bottomRightPointEllipseY;
    private double topLeftPointLineX, topLeftPointLineY, bottomRightPointLineX, bottomRightPointLineY;
    private double distanceX, distanceY;
    private Point2D.Double centerPointRectangle, centerPointEllipse, centerPointLine, clickedPoint;
    private Line2D.Double xLineRectangle, xLineEllipse, xLineLine, yLineRectangle, yLineEllipse, yLineLine;
    private Action instance;
    
    public RotateActionTest() {
        System.out.println("Test RotateAction");
    }
    
    @Before
    public void setUp() {
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        lineShape = new LineShape();
        
        rectangleShape.setDim(15, 30, 50, 60);
        ellipseShape.setDim(40, 50, 100, 160);
        lineShape.setDim(150, 320, 500, 260);
        
        topLeftPointRectangleX = rectangleShape.getShape().getBoundsInParent().getMinX();
        topLeftPointRectangleY = rectangleShape.getShape().getBoundsInParent().getMinY();
        bottomRightPointRectangleX = rectangleShape.getShape().getBoundsInParent().getMaxX();
        bottomRightPointRectangleY = rectangleShape.getShape().getBoundsInParent().getMaxY();
        centerPointRectangle = new Point2D.Double(topLeftPointRectangleX + (bottomRightPointRectangleX - topLeftPointRectangleX)/2, topLeftPointRectangleY + (bottomRightPointRectangleY - topLeftPointRectangleY)/2); 
        xLineRectangle = new Line2D.Double();
        yLineRectangle = new Line2D.Double();
        xLineRectangle.setLine(centerPointRectangle,new Point2D.Double(centerPointRectangle.x, 0));
        yLineRectangle.setLine(centerPointRectangle,new Point2D.Double(0,centerPointRectangle.y));
        
        
        ellipseShape.setX(1);
        ellipseShape.setY(2);
        topLeftPointEllipseX = ellipseShape.getShape().getBoundsInParent().getMinX();
        topLeftPointEllipseY = ellipseShape.getShape().getBoundsInParent().getMinY();
        bottomRightPointEllipseX = ellipseShape.getShape().getBoundsInParent().getMaxX();
        bottomRightPointEllipseY = ellipseShape.getShape().getBoundsInParent().getMaxY();
        centerPointEllipse = new Point2D.Double(topLeftPointEllipseX + (bottomRightPointEllipseX - topLeftPointEllipseX)/2, topLeftPointEllipseY + (bottomRightPointEllipseY - topLeftPointEllipseY)/2);
        xLineEllipse = new Line2D.Double();
        yLineEllipse = new Line2D.Double();
        xLineEllipse.setLine(centerPointEllipse,new Point2D.Double(centerPointEllipse.x, 0));
        yLineEllipse.setLine(centerPointEllipse,new Point2D.Double(0,centerPointEllipse.y));
        
        topLeftPointLineX = lineShape.getShape().getBoundsInParent().getMinX();
        topLeftPointLineY = lineShape.getShape().getBoundsInParent().getMinY();
        bottomRightPointLineX = lineShape.getShape().getBoundsInParent().getMaxX();
        bottomRightPointLineY = lineShape.getShape().getBoundsInParent().getMaxY();        
        centerPointLine = new Point2D.Double(topLeftPointLineX + (bottomRightPointLineX - topLeftPointLineX)/2, topLeftPointLineY + (bottomRightPointLineY - topLeftPointLineY)/2);
        xLineLine = new Line2D.Double();
        yLineLine = new Line2D.Double();
        xLineLine.setLine(centerPointLine,new Point2D.Double(centerPointLine.x, 0));
        yLineLine.setLine(centerPointLine,new Point2D.Double(0,centerPointLine.y));
        
    }

    /**
     * Test of execute method, of class RotateAction.
     * @throws exceptions.ShapeNullException
     */
    @Test
    public void testExecute() throws ShapeNullException, Exception {
        System.out.println("execute: ");
        
        // Test for RectangleShape
        
        // Test --> clickedPoint.getX() < centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(5, 200);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), 90-Math.toDegrees(-Math.atan(distanceX/distanceY)), 0.1);
        
        
        
        // Test --> clickedPoint.getX() < centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX > distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 1, 20, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(1, 20);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), 90-Math.toDegrees(-Math.atan(distanceX/distanceY)), 0.1);
        
        
                
        // Test --> clickedPoint.getX() < centerShape.getX()    clickedPoint.getY() < centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 5, 1, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(5, 1);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), -(90-Math.toDegrees(-Math.atan(distanceX/distanceY))), 0.1);
        
        
                
        // Test --> clickedPoint.getX() < centerShape.getX()    clickedPoint.getY() < centerShape.getY()    distanceX > distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 1, 12, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(1, 12);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), -(90-Math.toDegrees(-Math.atan(distanceX/distanceY))), 0.1);

              
        
        // Test --> clickedPoint.getX() > centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 20, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(20, 200);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), Math.toDegrees(Math.atan(distanceY/distanceX)), 0.1);
        
        
        
        // Test --> clickedPoint.getX() > centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX > distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 20, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(50, 20);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), 90-Math.toDegrees(Math.atan(distanceX/distanceY)), 0.1);
        
        
                
        // Test --> clickedPoint.getX() > centerShape.getX()    clickedPoint.getY() < centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 20, 1, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(20, 1);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), -Math.toDegrees(Math.atan(distanceY/distanceX)), 0.1);
        
        
                
        // Test --> clickedPoint.getX() > centerShape.getX()    clickedPoint.getY() < centerShape.getY()    distanceX > distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 30, 12, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        rectangleShape = new RectangleShape();
        rectangleShape.setDim(15, 30, 50, 60);
        instance = new RotateAction(rectangleShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(30, 12);
        
        distanceX = distancePointToLine(xLineRectangle,clickedPoint);
        distanceY = distancePointToLine(yLineRectangle,clickedPoint);
        
        assertEquals(rectangleShape.getRotate().getAngle(), -(90-Math.toDegrees(Math.atan(distanceX/distanceY))), 0.1);
        
        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class RotateAction.
     * @throws exceptions.ShapeNullException
     */
    @Test
    public void testOnMouseDragged() throws ShapeNullException, Exception {
        System.out.println("onMouseDragged: ");
        
        // Test for EllipseShape
        
        // Test --> clickedPoint.getX() > centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 8, 45, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new RotateAction(ellipseShape);
        instance.execute(event);
        instance.onMouseDragged(event);
        clickedPoint = new Point2D.Double(8, 45);
        
        distanceX = distancePointToLine(xLineEllipse,clickedPoint);
        distanceY = distancePointToLine(yLineEllipse,clickedPoint);
        
        assertEquals(ellipseShape.getRotate().getAngle(), Math.toDegrees(Math.atan(distanceY/distanceX)), 0.1);
        
        // Test for LineShape
        
        // Test --> clickedPoint.getX() < centerShape.getX()    clickedPoint.getY() > centerShape.getY()    distanceX < distanceY
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 170, 20, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new RotateAction(lineShape);
        instance.execute(event);
        instance.onMouseDragged(event);
        clickedPoint = new Point2D.Double(170, 20);
        
        distanceX = distancePointToLine(xLineLine,clickedPoint);
        distanceY = distancePointToLine(yLineLine,clickedPoint);
        System.out.println(centerPointLine.x +" "+centerPointLine.y + " "+clickedPoint.x+" "+clickedPoint.y+" "+distanceX+" "+ distanceY);
        
        assertEquals(lineShape.getRotate().getAngle(), 90-Math.toDegrees(-Math.atan(distanceX/distanceY)), 0.1);
        
        System.out.println("Passed");
    }
    
    /**
     * Test of constructor method, of class RotateAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=ShapeNullException.class)
    public void testConstructorWithNullShape() throws ShapeNullException, Exception {
        System.out.println("constructore with null parameter: ");
        
        instance = new RotateAction(null);
        
        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class RotateAction.
     * @throws exceptions.NotExecutedActionException
     * @throws exceptions.ShapeNullException
     */
    @Test
    public void testUndo() throws NotExecutedActionException, ShapeNullException, Exception {
        System.out.println("undo: ");
        
        double angle = lineShape.getShape().getRotate();
        
        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 500, -35, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        instance = new RotateAction(lineShape);
        instance.execute(event);
        clickedPoint = new Point2D.Double(500, -35);
        
        instance.undo();
        assertEquals(lineShape.getShape().getRotate(), angle, 0.1);
        
        System.out.println("Passed");
    }
    
        /**
     * Test of undo method, of class RotateAction.
     * @throws exceptions.NotExecutedActionException
     * @throws exceptions.ShapeNullException
     */
    @Test(expected=NotExecutedActionException.class)
    public void testUndoNotExecutedAction() throws NotExecutedActionException, ShapeNullException, Exception {
        System.out.println("undo while the action hasn't been executed: ");
        
        instance = new RotateAction(lineShape);
        instance.undo();
        
        System.out.println("Passed");
    }
    
    private double distancePointToLine(Line2D.Double line, Point2D.Double point){
        final Point2D l1 = line.getP1();
        final Point2D l2 = line.getP2();
        return Math.abs((l2.getX() - l1.getX()) * (l1.getY() - point.getY())- (l1.getX() - point.getX()) * (l2.getY() - l1.getY()))/ Math.sqrt(Math.pow(l2.getX() - l1.getX(), 2) + Math.pow(l2.getY() - l1.getY(), 2));
    }
    
}
