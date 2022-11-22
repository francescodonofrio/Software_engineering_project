package shapes;

import javafx.scene.shape.Rectangle;

public class RectangleShape extends CloseContourShape {

    /**
     * The constructor of a RectangleShape
     */
    public RectangleShape() {
        this.shape = new Rectangle(20, 50);
    }

    @Override
    public javafx.scene.shape.Shape getShape() {
        return shape;
    }

}