package action;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
import shapes.ShapeInterface;

public class DrawActionTest {

    private double width, height, radiusX, radiusY, startX, startY, endX, endY;
    private ShapeInterface rectangleShape, ellipseShape, lineShape;
    private Pane drawingPane;
    private ObservableList<ShapeInterface> listInsertedShapes;
    private ObjectProperty<Color> internalColorProperty, contourColorProperty;
    private MouseEvent event;
    private DrawAction instanceDrawActionLine, instanceDrawActionRectangle, instanceDrawActionEllipse;

    public DrawActionTest() {
        System.out.println("Test DrawAction");
    }

    @Before
    public void setUp() {

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

        drawingPane = new Pane();

        internalColorProperty = new SimpleObjectProperty<>();
        contourColorProperty = new SimpleObjectProperty<>();
        internalColorProperty.set(Color.BLUE);
        contourColorProperty.set(Color.BLACK);

        listInsertedShapes = FXCollections.observableArrayList();
        listInsertedShapes.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getRemoved().forEach(remItem -> {
                    drawingPane.getChildren().remove(remItem.getShape());
                });
                change.getAddedSubList().forEach(addItem -> {
                    drawingPane.getChildren().add(addItem.getShape());
                });
            }
        });

    }

    /**
     * Test of execute method, of class DrawAction.
     */
    @Test
    public void testExecute() {
        System.out.print("execute: ");

        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        instanceDrawActionRectangle = new DrawAction(rectangleShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionRectangle.execute(event);
        Rectangle rectangleRetrieved = (Rectangle) drawingPane.getChildren().get(0);
        Color colorFillRectangle = (Color) rectangleRetrieved.getFill();
        Color colorStrokeRectangle = (Color) rectangleRetrieved.getStroke();

        assertEquals(rectangleRetrieved.getLayoutX(), event.getX(), 0.1);
        assertEquals(rectangleRetrieved.getLayoutY(), event.getY(), 0.1);
        assertEquals(rectangleRetrieved.getHeight(), height, 0.1);
        assertEquals(rectangleRetrieved.getWidth(), width, 0.1);
        assertEquals(colorFillRectangle.getRed(), Color.BLUE.getRed(), 0.1);
        assertEquals(colorFillRectangle.getGreen(), Color.BLUE.getGreen(), 0.1);
        assertEquals(colorFillRectangle.getBlue(), Color.BLUE.getBlue(), 0.1);
        assertEquals(colorFillRectangle.getOpacity(), Color.BLUE.getOpacity(), 0.1);
        assertEquals(colorStrokeRectangle.getRed(), Color.BLACK.getRed(), 0.1);
        assertEquals(colorStrokeRectangle.getGreen(), Color.BLACK.getGreen(), 0.1);
        assertEquals(colorStrokeRectangle.getBlue(), Color.BLACK.getBlue(), 0.1);
        assertEquals(colorStrokeRectangle.getOpacity(), Color.BLACK.getOpacity(), 0.1);


        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 180, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        instanceDrawActionEllipse = new DrawAction(ellipseShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionEllipse.execute(event);
        Ellipse shapeRetrievedEllipse = (Ellipse) drawingPane.getChildren().get(1);
        Color colorFillEllipse = (Color) shapeRetrievedEllipse.getFill();
        Color colorStrokeEllipse = (Color) shapeRetrievedEllipse.getStroke();

        assertEquals(shapeRetrievedEllipse.getLayoutX(), event.getX(), 0.1);
        assertEquals(shapeRetrievedEllipse.getLayoutY(), event.getY(), 0.1);
        assertEquals(shapeRetrievedEllipse.getRadiusX(), radiusX, 0.1);
        assertEquals(shapeRetrievedEllipse.getRadiusY(), radiusY, 0.1);
        assertEquals(colorFillEllipse.getRed(), Color.BLUE.getRed(), 0.1);
        assertEquals(colorFillEllipse.getGreen(), Color.BLUE.getGreen(), 0.1);
        assertEquals(colorFillEllipse.getBlue(), Color.BLUE.getBlue(), 0.1);
        assertEquals(colorFillEllipse.getOpacity(), Color.BLUE.getOpacity(), 0.1);
        assertEquals(colorStrokeEllipse.getRed(), Color.BLACK.getRed(), 0.1);
        assertEquals(colorStrokeEllipse.getGreen(), Color.BLACK.getGreen(), 0.1);
        assertEquals(colorStrokeEllipse.getBlue(), Color.BLACK.getBlue(), 0.1);
        assertEquals(colorStrokeEllipse.getOpacity(), Color.BLACK.getOpacity(), 0.1);

        event = new MouseEvent(MouseEvent.MOUSE_PRESSED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        instanceDrawActionLine = new DrawAction(lineShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionLine.execute(event);
        Line shapeRetrievedLine = (Line) drawingPane.getChildren().get(2);
        Color colorStrokeLine = (Color) shapeRetrievedLine.getStroke();

        assertEquals(shapeRetrievedLine.getLayoutX(), event.getX(), 0.1);
        assertEquals(shapeRetrievedLine.getLayoutY(), event.getY(), 0.1);
        assertEquals(shapeRetrievedLine.getStartX(), startX, 0.1);
        assertEquals(shapeRetrievedLine.getStartY(), startY, 0.1);
        assertEquals(shapeRetrievedLine.getEndX(), endX, 0.1);
        assertEquals(shapeRetrievedLine.getEndY(), endY, 0.1);
        assertEquals(colorStrokeLine.getRed(), Color.BLACK.getRed(), 0.1);
        assertEquals(colorStrokeLine.getGreen(), Color.BLACK.getGreen(), 0.1);
        assertEquals(colorStrokeLine.getBlue(), Color.BLACK.getBlue(), 0.1);
        assertEquals(colorStrokeLine.getOpacity(), Color.BLACK.getOpacity(), 0.1);

        System.out.println("Passed");
    }

    /**
     * Test of onMouseDragged method, of class DrawAction.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.print("onMouseDragged: ");
        event = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 100, 150, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        instanceDrawActionRectangle = new DrawAction(rectangleShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionRectangle.onMouseDragged(event);
        Rectangle rectangle = (Rectangle) rectangleShape.getShape();
        assertEquals(rectangle.getWidth(), event.getX(), 0.1);
        assertEquals(rectangle.getHeight(), event.getY(), 0.1);

        instanceDrawActionEllipse = new DrawAction(ellipseShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionEllipse.onMouseDragged(event);
        Ellipse ellipse = (Ellipse) ellipseShape.getShape();
        assertEquals(ellipse.getRadiusX(), event.getX(), 0.1);
        assertEquals(ellipse.getRadiusY(), event.getY(), 0.1);

        instanceDrawActionLine = new DrawAction(lineShape, internalColorProperty, contourColorProperty, listInsertedShapes);
        instanceDrawActionLine.onMouseDragged(event);
        Line line = (Line) lineShape.getShape();
        assertEquals(line.getEndX(), event.getX(), 0.1);
        assertEquals(line.getEndY(), event.getY(), 0.1);

        System.out.println("Passed");
    }
}
