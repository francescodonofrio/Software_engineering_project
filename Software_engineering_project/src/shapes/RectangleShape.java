package shapes;


import serializedIO.SerializableRectangle;

public class RectangleShape extends CloseContourShape {

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new SerializableRectangle(20, 50);
    }

    @Override
    public void setDim(double initialDim1, double initialDim2, double finalDim1, double finalDim2) {
        javafx.scene.shape.Rectangle rectangle = (javafx.scene.shape.Rectangle)shape;
        rectangle.setHeight(finalDim2-initialDim2);
        rectangle.setWidth(finalDim1-initialDim1);
    }

}