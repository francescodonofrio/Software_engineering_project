package action;

import exceptions.NotResizedException;
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
     */
    @Test
    public void testExecute() {
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

        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class ResizeAction.
     */
    @Test
    public void testOnMouseDragged() {
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

}
