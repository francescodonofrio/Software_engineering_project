package serializableShapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class SerializableRectangle extends Rectangle implements Serializable {

    /**
     * Creates an empty instance of SerializableRectangle.
     */
    public SerializableRectangle() {
    }

    /**
     * Creates a new instance of SerializableRectangle with the given size.
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public SerializableRectangle(double width, double height) {
        super(width, height);
    }

    /**
     * Creates a new instance of SerializableRectangle with the given size and fill.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param fill determines how to fill the interior of the rectangle
     */
    public SerializableRectangle(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    /**
     * Creates a new instance of SerializableRectangle with the given position and size.
     * @param x horizontal position of the rectangle
     * @param y vertical position of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public SerializableRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
