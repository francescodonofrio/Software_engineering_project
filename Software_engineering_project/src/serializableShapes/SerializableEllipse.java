package serializableShapes;

import javafx.scene.shape.Ellipse;

import java.io.Serializable;

public class SerializableEllipse extends Ellipse implements Serializable {

    /**
     * Creates an empty instance of SerializableEllipse.
     */
    public SerializableEllipse() {
    }

    /**
     * Creates an instance of Ellipse of the given size.
     * @param radiusX the horizontal radius of the ellipse in pixels
     * @param radiusY the vertical radius of the ellipse in pixels
     */
    public SerializableEllipse(double radiusX, double radiusY) {
        super(radiusX, radiusY);
    }

    /**
     * Creates an instance of Ellipse of the given position and size.
     * @param centerX the horizontal position of the center of the ellipse in pixels
     * @param centerY the vertical position of the center of the ellipse in pixels
     * @param radiusX the horizontal radius of the ellipse in pixels
     * @param radiusY the vertical radius of the ellipse in pixels
     */
    public SerializableEllipse(double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
    }
}
