package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class DrawAction implements Action {

    private final ShapeInterface shape;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ObjectProperty<Color> colorPickerInternal;
    private final ObjectProperty<Color> colorPickerContour;
    private double initialX, initialY, finalX, finalY;
    private boolean hasNotBeenExecuted;


    /**
     * Returns a new instance of DrawAction, given the shape to draw,
     * its internal and contour color and the pane where to draw at
     *
     * @param shape               the shape to draw
     * @param colorPickerInternal an ObjectProperty<Color> from whom the shape's internal color is taken
     * @param colorPickerContour  an ObjectProperty<Color> from whom the shape's contour color is taken
     * @param listInsertedShapes  the list in which are stored the shapes
     * @throws exceptions.ShapeNullException
     */
    public DrawAction(ShapeInterface shape, ObjectProperty<Color> colorPickerInternal, ObjectProperty<Color> colorPickerContour, ObservableList<ShapeInterface> listInsertedShapes) throws ShapeNullException {
        if (shape == null)
            throw new ShapeNullException();
        else
            this.shape = shape;
        this.listInsertedShapes = listInsertedShapes;
        this.colorPickerInternal = colorPickerInternal;
        this.colorPickerContour = colorPickerContour;
        this.hasNotBeenExecuted = true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) {
        listInsertedShapes.add(shape);
        MouseEvent mouseEvent = (MouseEvent) event;
        initialX = mouseEvent.getX();
        initialY = mouseEvent.getY();
        shape.setProperties(initialX, initialY, colorPickerInternal.getValue(), colorPickerContour.getValue());
        hasNotBeenExecuted = false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
        MouseEvent mouseEvent = (MouseEvent) event;
        finalX = mouseEvent.getX();
        finalY = mouseEvent.getY();
        shape.setDim(initialX, initialY, finalX, finalY);
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseReleased(Event event) {
        shape.setInserted(true);
    }

    /**
     * Undoes the action
     *
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();

        listInsertedShapes.remove(shape);
        hasNotBeenExecuted = true;
    }
}
