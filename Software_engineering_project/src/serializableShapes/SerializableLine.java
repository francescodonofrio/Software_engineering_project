package serializableShapes;

import javafx.scene.shape.Line;

import java.io.Serializable;

public class SerializableLine extends Line implements Serializable {
    public SerializableLine(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }

    public SerializableLine() {
    }
}
