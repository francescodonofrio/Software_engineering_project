package action;

import exceptions.NotExecutedActionException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import shapes.PolygonShape;
import shapes.ShapeInterface;

import java.util.ArrayList;
import java.util.List;


public class DrawPolygonAction implements Action {
    private final ObservableList<ShapeInterface> insertedShapes;
    private final ObjectProperty<Color> internalColor, contourColor;
    private final SimpleBooleanProperty keepActionsDisabled;
    private final List<Double> insertedPoints;
    private ShapeInterface polygon;
    private boolean hasNotBeenExecuted;
    private double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE, minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;

    /**
     * Returns a new instance of DrwPolygon
     */
    public DrawPolygonAction(ObjectProperty<Color> internalColor, ObjectProperty<Color> contourColor, ObservableList<ShapeInterface> listInsertedShapes, SimpleBooleanProperty keepActionsDisabled) {
        this.contourColor = contourColor;
        this.internalColor = internalColor;
        this.insertedShapes = listInsertedShapes;
        this.keepActionsDisabled = keepActionsDisabled;
        this.hasNotBeenExecuted = true;
        this.insertedPoints = new ArrayList<>();
        this.polygon = null;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void execute(Event event) throws Exception {
        
        if (keepActionsDisabled.get()) {
            hasNotBeenExecuted = false;
            MouseEvent clickEvent = (MouseEvent) event;
            minX = Math.min(minX, clickEvent.getX());
            minY = Math.min(minY, clickEvent.getY());
            maxX = Math.max(maxX, clickEvent.getX());
            maxY = Math.max(maxY, clickEvent.getY());
            
            if (insertedPoints.size() < 4) {
                insertedPoints.add(clickEvent.getX());
                insertedPoints.add(clickEvent.getY());
            } else {
                if (polygon == null) {
                    polygon = new PolygonShape();
                    ((Polygon) polygon.getShape()).getPoints().addAll(insertedPoints.toArray(new Double[0]));
                    insertedShapes.add(polygon);
                }
                polygon.setContourColor(contourColor.get());
                polygon.setInternalColor(internalColor.get());
                ((PolygonShape) polygon).setWidth(minX, maxX);
                ((PolygonShape) polygon).setHeight(minY, maxY);

                ((Polygon) polygon.getShape()).getPoints().addAll(clickEvent.getX(), clickEvent.getY());
            }
        }
            
    }


    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    /**
     * Undoes the action
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();

        if (polygon == null) {
            insertedPoints.clear();
        } else
            insertedShapes.remove(polygon);
        hasNotBeenExecuted = true;
    }

}
