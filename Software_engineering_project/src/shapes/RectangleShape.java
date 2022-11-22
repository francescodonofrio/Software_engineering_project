package shapes;


import serializableShapes.SerializableRectangle;

public class RectangleShape extends CloseContourShape {

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new SerializableRectangle(20, 50);
    }

}