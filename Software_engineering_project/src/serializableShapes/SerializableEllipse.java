package serializableShapes;

import javafx.scene.shape.Ellipse;

import java.io.Serializable;

public class SerializableEllipse extends Ellipse implements Serializable {
    public SerializableEllipse() {
    }

    public SerializableEllipse(double radiusX, double radiusY) {
        super(radiusX, radiusY);
    }

    public SerializableEllipse(double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
    }
}
