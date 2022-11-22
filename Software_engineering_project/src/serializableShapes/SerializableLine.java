package serializableShapes;

import javafx.scene.shape.Line;

import java.io.Serializable;

public class SerializableLine extends Line implements Serializable {

    /**
     * Creates a new instance of SerializableLine.
     * @param startX the horizontal coordinate of the start point of the line segment
     * @param startY the vertical coordinate of the start point of the line segment
     * @param endX the horizontal coordinate of the end point of the line segment
     * @param endY the vertical coordinate of the end point of the line segment
     */
    public SerializableLine(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }

    /**
     * Creates an empty instance of SerializableLine.
     */
    public SerializableLine() {
    }
}
