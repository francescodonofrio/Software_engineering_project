package shapes;

import serializableShapes.SerializableLine;

public class LineShape extends OpenContourShape {

    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new SerializableLine(-100, 0, 50, 0);
    }

}