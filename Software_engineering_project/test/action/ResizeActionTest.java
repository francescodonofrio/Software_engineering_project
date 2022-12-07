package action;

import exceptions.NotExecutedActionException;
import exceptions.NotResizedException;
import exceptions.ShapeNullException;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

public class ResizeActionTest {

    private ShapeInterface rectangleShape, lineShape, ellipseShape;
    private ResizeAction action;
    private MouseEvent event;

    public ResizeActionTest() {
        System.out.println("Test ResizeAction");
    }

    @Before
    public void setUp() {
        rectangleShape = new RectangleShape();
        lineShape = new LineShape();
        ellipseShape = new EllipseShape();
    }

    /**
     * Test of execute method, of class ResizeAction.
     * @throws exceptions.ShapeNullException
     */
    @Test(expected = ShapeNullException.class)
    public void testExecute() throws ShapeNullException {
        System.out.print("execute: ");

        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        action = new ResizeAction(rectangleShape);
        action.execute(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getWidth(), event.getX(), 0.1);
        assertEquals(rectangle.getHeight(), event.getY(), 0.1);

        action = new ResizeAction(ellipseShape);
        action.execute(event);
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getRadiusX(), event.getX(), 0.1);
        assertEquals(ellipse.getRadiusY(), event.getY(), 0.1);

        action = new ResizeAction(lineShape);
        action.execute(event);
        Line line = (Line) lineShape.getShape();
        assertEquals(line.getEndX(), event.getX(), 0.1);
        assertEquals(line.getEndY(), event.getY(), 0.1);
        
        action = new ResizeAction(null);

        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class ResizeAction.
     * @throws exceptions.ShapeNullException
     */
    @Test
    public void testOnMouseDragged() throws ShapeNullException {
        System.out.print("onMouseDragged: ");

        event = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        action = new ResizeAction(rectangleShape);
        action.onMouseDragged(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getWidth(), event.getX(), 0.1);
        assertEquals(rectangle.getHeight(), event.getY(), 0.1);

        action = new ResizeAction(ellipseShape);
        action.onMouseDragged(event);
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getRadiusX(), event.getX(), 0.1);
        assertEquals(ellipse.getRadiusY(), event.getY(), 0.1);

        action = new ResizeAction(lineShape);
        action.onMouseDragged(event);
        Line line = (Line) lineShape.getShape();
        assertEquals(line.getEndX(), event.getX(), 0.1);
        assertEquals(line.getEndY(), event.getY(), 0.1);

        System.out.println("Passed");
    }

    /**
     * Test of onMouseReleased method, of class ResizeAction.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NotResizedException.class)
    public void testOnMouseReleased() throws Exception {
        System.out.print("onMouseReleased: ");

        event = new MouseEvent(MouseEvent.MOUSE_RELEASED, 100, 100, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        action = new ResizeAction(rectangleShape);
        action.onMouseReleased(event);

        System.out.println("Passed");
    }

    /**
     * Test of undo method, of class ResizeAction.
     *
     *
     * @throws exceptions.ShapeNullException
     * @throws exceptions.NotExecutedActionException
     */
    @Test
    public void testUndo() throws ShapeNullException, NotExecutedActionException {
        System.out.print("undo: ");

        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 10, 25, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        Rectangle rectangleTest = (Rectangle)rectangleShape.getShape();
        double width = rectangleTest.getWidth();
        double height = rectangleTest.getHeight();
        double layoutXRectangle = rectangleTest.getLayoutX();
        double layoutYRectangle = rectangleTest.getLayoutY();
        
        Ellipse ellipseTest = (Ellipse)ellipseShape.getShape();
        double radiusX = ellipseTest.getRadiusX();
        double radiusY = ellipseTest.getRadiusY();
        double layoutXEllipse = ellipseTest.getLayoutX();
        double layoutYEllipse = ellipseTest.getLayoutY();
        
        Line lineTest = (Line)lineShape.getShape();
        double startX = lineTest.getStartX();
        double startY = lineTest.getStartY();
        double endX = lineTest.getEndX();
        double endY = lineTest.getEndY();
        double layoutXLine = lineTest.getLayoutX();
        double layoutYLine = lineTest.getLayoutY();
        
        action = new ResizeAction(rectangleShape);
        action.execute(event);
        action.undo();
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getWidth(), width, 0.1);
        assertEquals(rectangle.getHeight(), height, 0.1);
        assertEquals(rectangle.getLayoutX(), layoutXRectangle, 0.1);
        assertEquals(rectangle.getLayoutY(), layoutYRectangle, 0.1);

        action = new ResizeAction(ellipseShape);
        action.execute(event);
        action.undo();
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getRadiusX(), radiusX, 0.1);
        assertEquals(ellipse.getRadiusY(), radiusY, 0.1);
        assertEquals(ellipse.getLayoutX(), layoutXEllipse, 0.1);
        assertEquals(ellipse.getLayoutY(), layoutYEllipse, 0.1);

        action = new ResizeAction(lineShape);
        action.execute(event);
        action.undo();
        Line line = (Line) lineShape.getShape();
        assertEquals(line.getStartX(), startX, 0.1);
        assertEquals(line.getStartY(), startY, 0.1);
        assertEquals(line.getEndX(), endX, 0.8);
        assertEquals(line.getEndY(), endY, 0.8);
        assertEquals(line.getLayoutX(), layoutXLine, 0.1);
        assertEquals(line.getLayoutY(), layoutYLine, 0.1);

        System.out.println("Passed");
    }

}
