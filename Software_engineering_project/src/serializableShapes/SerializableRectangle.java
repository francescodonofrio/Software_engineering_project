package serializableShapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class SerializableRectangle extends Rectangle implements Serializable {
    public SerializableRectangle() {
    }

    public SerializableRectangle(double width, double height) {
        super(width, height);
    }

    public SerializableRectangle(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public SerializableRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
