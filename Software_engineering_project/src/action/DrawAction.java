package action;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.Shape;

public class DrawAction implements Action {

    private final Shape shape;
    private final Pane drawingPane;

    /**
     * Returns a new instance of DrawAction, given the shape to draw, its coordinates,
     * its internal and contour color and the pane where to draw at
     *
     * @param shape         the shape to draw
     * @param X             the X coordinate of the shape
     * @param Y             the Y coordinate of the shape
     * @param internalColor the internal color of the shape
     * @param contourColor  the contour color of the shape
     * @param drawingPane   the drawing pane
     */
    public DrawAction(Shape shape, double X, double Y, Color internalColor, Color contourColor, Pane drawingPane) {
        this.shape = shape;
        this.drawingPane = drawingPane;
        shape.draw(X, Y, internalColor, contourColor);
    }

    /**
     * Executes the draw action as instantiated before
     */
    @Override
    public void execute() {
        drawingPane.getChildren().add(shape.getShape());
    }

}
