package serializedIO;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableEllipse extends Ellipse implements Serializable {

    /**
     * Creates an empty instance of SerializableEllipse.
     */
    public SerializableEllipse() {
    }

    /**
     * Creates an instance of Ellipse of the given size.
     *
     * @param radiusX the horizontal radius of the ellipse in pixels
     * @param radiusY the vertical radius of the ellipse in pixels
     */
    public SerializableEllipse(double radiusX, double radiusY) {
        super(radiusX, radiusY);
    }

    /**
     * Creates an instance of Ellipse of the given position and size.
     *
     * @param centerX the horizontal position of the center of the ellipse in pixels
     * @param centerY the vertical position of the center of the ellipse in pixels
     * @param radiusX the horizontal radius of the ellipse in pixels
     * @param radiusY the vertical radius of the ellipse in pixels
     */
    public SerializableEllipse(double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
    }

    /**
     * Write this object to the ObjectOutputStream.
     *
     * @param output an ObjectOutpuStream used to write on an OutputStream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream output) throws IOException {

        output.defaultWriteObject();
        output.writeDouble(this.getLayoutX());
        output.writeDouble(this.getLayoutY());
        output.writeDouble(this.getCenterX());
        output.writeDouble(this.getCenterY());
        output.writeDouble(this.getRadiusX());
        output.writeDouble(this.getRadiusY());

        Color colorStroke = (Color) this.getStroke();
        output.writeDouble(colorStroke.getRed());
        output.writeDouble(colorStroke.getGreen());
        output.writeDouble(colorStroke.getBlue());
        output.writeDouble(colorStroke.getOpacity());

        Color colorFill = (Color) this.getFill();
        output.writeDouble(colorFill.getRed());
        output.writeDouble(colorFill.getGreen());
        output.writeDouble(colorFill.getBlue());
        output.writeDouble(colorFill.getOpacity());

    }

    /**
     * Write this object to the ObjectInputStream.
     *
     * @param input an ObjectInputStream used to read on an InputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {

        input.defaultReadObject();
        this.setLayoutX(input.readDouble());
        this.setLayoutY(input.readDouble());
        this.setCenterX(input.readDouble());
        this.setCenterY(input.readDouble());
        this.setRadiusX(input.readDouble());
        this.setRadiusY(input.readDouble());
        this.setStroke(new Color(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble()));
        this.setFill(new Color(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble()));

    }
}
