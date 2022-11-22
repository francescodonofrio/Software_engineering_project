package shapes;

import serializableShapes.SerializableLine;

public class LineShape extends OpenContourShape {

    /**
     * The constructor of a LineShape
     */
    public LineShape() {
        this.shape = new SerializableLine(-100, 0, 100, 0);
    }

}