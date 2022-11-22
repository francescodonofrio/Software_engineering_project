package serializableShapes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import javafx.scene.paint.Color;

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
    
    private void writeObject(ObjectOutputStream output) throws IOException {

        output.defaultWriteObject();
        output.writeDouble(this.getLayoutX());
        output.writeDouble(this.getLayoutY());
        output.writeDouble(this.getHeight());
        output.writeDouble(this.getWidth());
        
        Color colorStroke = (Color)this.getStroke();
        output.writeDouble(colorStroke.getRed());
        output.writeDouble(colorStroke.getGreen());
        output.writeDouble(colorStroke.getBlue());
        output.writeDouble(colorStroke.getOpacity());
        
        Color colorFill = (Color)this.getFill();
        output.writeDouble(colorFill.getRed());
        output.writeDouble(colorFill.getGreen());
        output.writeDouble(colorFill.getBlue());
        output.writeDouble(colorFill.getOpacity());
        
    }
    
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {

        input.defaultReadObject();
        this.setLayoutX(input.readDouble());
        this.setLayoutY(input.readDouble());
        this.setHeight(input.readDouble());
        this.setWidth(input.readDouble());
        this.setStroke(new Color(input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble()));
        this.setFill(new Color(input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble()));
        
    }
}
