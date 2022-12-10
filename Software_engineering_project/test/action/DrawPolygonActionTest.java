package action;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import shapes.ShapeInterface;

public class DrawPolygonActionTest {
    private ObjectProperty<Color> internalColor, contourColor;
    private ObservableList<ShapeInterface> drawingPane;
    private DrawPolygonAction action;
    private SimpleBooleanProperty paneEnabled;

    public DrawPolygonActionTest() {
        System.out.println("Test DrawPolygonAction");
    }

    @Before
    public void setUp() {
        this.drawingPane = FXCollections.observableArrayList();
        this.internalColor = new SimpleObjectProperty(Color.BLACK);
        this.contourColor = new SimpleObjectProperty(Color.YELLOW);
        this.paneEnabled = new SimpleBooleanProperty(true);
        this.action = new DrawPolygonAction(internalColor, contourColor, drawingPane, paneEnabled);
    }

    /**
     * Test of execute method, of class DrawPolygonAction.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute: ");

        Invoker invoker = new Invoker();
        Double[] points = new Double[]{1.5, 2.0, 45.0, 6.9, 10.0, 15.0, 7.8, 0.0, 1.1, 9.0};
        Event event;

        for (int i = 0; i < points.length; i += 2) {
            event = new MouseEvent(null, null, MouseEvent.MOUSE_CLICKED, points[i], points[i + 1], points[i], points[i + 1], MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
            invoker.execute(action, event);
        }
        assertEquals(drawingPane.size(), 1, 0.1);

        PolygonShape polygon = (PolygonShape) drawingPane.get(0);

        assertEquals(internalColor.get(), polygon.getInternalColor());
        assertEquals(contourColor.get(), polygon.getCountourColor());
        assertEquals(43.9, polygon.getDimX(), 0.1);
        assertEquals(15, polygon.getDimY(), 0.1);
        assertEquals(((Polygon) polygon.getShape()).getPoints().size(), points.length, 0.1);

        for (int i = 0; i < points.length; i++) {
            assertEquals(points[i], ((Polygon) polygon.getShape()).getPoints().get(i), 0.1);
        }

        System.out.println("Passed");
    }


    /**
     * Test of undo method, of class DrawPolygonAction.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.print("undo: ");

        Invoker invoker = new Invoker();
        Double[] points = new Double[]{1.5, 2.0, 45.0, 6.9, 10.0, 15.0, 7.8, 0.0, 1.1, 9.0};
        Event event;

        for (int i = 0; i < points.length; i += 2) {
            event = new MouseEvent(null, null, MouseEvent.MOUSE_CLICKED, points[i], points[i + 1], points[i], points[i + 1], MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
            invoker.execute(action, event);
        }
        assertEquals(drawingPane.size(), 1, 0.1);

        invoker.undo();
        assertEquals(drawingPane.size(), 0, 0.1);


        System.out.println("Passed");
    }

}
