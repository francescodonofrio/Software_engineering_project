package action;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shapes.RectangleShape;
import shapes.ShapeInterface;

public class DrawAction implements Action {

    private final ShapeInterface shape;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ObjectProperty<Color> colorPickerInternal;
    private final ObjectProperty<Color> colorPickerContour;
    private double initialX;
    private double initialY;
    

    /**
     * Returns a new instance of DrawAction, given the shape to draw, its coordinates,
     * its internal and contour color and the pane where to draw at
     *
     * @param shape         the shape to draw
     * @param colorPickerInternal   an ObjectProperty<Color> from whom the shape's internal color is taken
     * @param colorPickerContour    an ObjectProperty<Color> from whom the shape's contour color is taken
     * @param listInsertedShapes    the list in which are stored the shapes
     */
    public DrawAction(ShapeInterface shape, ObjectProperty<Color> colorPickerInternal, ObjectProperty<Color> colorPickerContour, ObservableList<ShapeInterface> listInsertedShapes) {
        this.shape = shape;
        this.listInsertedShapes = listInsertedShapes;
        this.colorPickerInternal = colorPickerInternal;
        this.colorPickerContour = colorPickerContour;
    }

    /**
     * Executes the draw action as instantiated before
     */
    @Override
    public void execute(MouseEvent event) {
        listInsertedShapes.add(shape);
        initialX = event.getX();
        initialY = event.getY();
        shape.setProperties(initialX, initialY, colorPickerInternal.getValue(), colorPickerContour.getValue());
    }

    /**
     * Set the dimension of the shape
     * 
     * @param event the mouse event of the click
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        shape.setDim(initialX, initialY, event.getX(), event.getY());
    }

    /**
     * Disable the drawing pane
     * 
     * @param event the mouse event of the click
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
    }
    
    

}
