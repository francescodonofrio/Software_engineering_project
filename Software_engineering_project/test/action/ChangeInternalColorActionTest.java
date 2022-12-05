package action;

import exceptions.NotCloseContourException;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class ChangeInternalColorActionTest {

    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private ObjectProperty<Color> internalColorProperty;
    private ChangeInternalColorAction action;
    private ActionEvent event;

    public ChangeInternalColorActionTest() {
        System.out.println("Test DrawAction");
    }

    @Before
    public void setUp() {
        // Shapes set up
        rectangleShape = new RectangleShape();
        ellipseShape = new EllipseShape();
        lineShape = new LineShape();

        internalColorProperty = new SimpleObjectProperty<>();
        internalColorProperty.set(Color.BLACK);
    }

    @Test
    public void testExecute() throws Exception {
        System.out.print("execute");

        event = new ActionEvent();

        action = new ChangeInternalColorAction(rectangleShape, internalColorProperty);
        action.execute(event);

        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getFill(), Color.BLACK);

        action = new ChangeInternalColorAction(ellipseShape, internalColorProperty);
        action.execute(event);

        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getFill(), Color.BLACK);

        action = new ChangeInternalColorAction(lineShape, internalColorProperty);
        action.execute(event);


        Line line = (Line) lineShape.getShape();
        assertEquals(line.getFill(), Color.TRANSPARENT);


    }
}
