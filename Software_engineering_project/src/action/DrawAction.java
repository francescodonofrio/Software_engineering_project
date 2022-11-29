package action;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.ShapeInterface;

public class DrawAction implements Action {

    private final ShapeInterface shape;
    private final Pane drawingPane;
    private final Color colorPickerInternal;
    private final Color colorPickerContour;
    private double initialX;
    private double initialY;
    

    /**
     * Returns a new instance of DrawAction, given the shape to draw, its coordinates,
     * its internal and contour color and the pane where to draw at
     *
     * @param shape         the shape to draw
     * @param colorPickerInternal   an ObjectProperty<Color> from whom the shape's internal color is taken
     * @param colorPickerContour    an ObjectProperty<Color> from whom the shape's contour color is taken
     * @param drawingPane   the drawing pane
     */
    public DrawAction(ShapeInterface shape, ObjectProperty<Color> colorPickerInternal, ObjectProperty<Color> colorPickerContour, Pane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        this.colorPickerInternal = colorPickerInternal.getValue();
        this.colorPickerContour = colorPickerContour.getValue();
    }

    /**
     * Executes the draw action as instantiated before
     */
    @Override
    public void execute(MouseEvent event) {
        drawingPane.getChildren().add(shape.getShape());
        initialX = event.getX();
        initialY = event.getY();
        shape.setProperties(initialX, initialY, colorPickerInternal, colorPickerContour);
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
        drawingPane.setDisable(true);
    }
    
    

}
