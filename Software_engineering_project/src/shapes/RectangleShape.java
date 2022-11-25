package shapes;


import serializedIO.SerializableRectangle;

public class RectangleShape extends CloseContourShape {

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new SerializableRectangle();
    }

    /**
     * Updates the dimentions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    @Override
    public void setDim(double initialX, double initialY, double finalX, double finalY) {
        javafx.scene.shape.Rectangle rectangle = (javafx.scene.shape.Rectangle) shape;
        double width = finalX - initialX;
        double height = finalY - initialY;
        if (width < 0) {
            width = -width;
            rectangle.setLayoutX(finalX);
        }
        if (height < 0) {
            height = -height;
            rectangle.setLayoutY(finalY);
        }

        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }

}