package serializedIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.shape.Line;

import java.io.Serializable;
import javafx.scene.paint.Color;

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
    
     private void writeObject(ObjectOutputStream output) throws IOException {

        output.defaultWriteObject();
        output.writeDouble(this.getLayoutX());
        output.writeDouble(this.getLayoutY());
        output.writeDouble(this.getStartX());
        output.writeDouble(this.getStartY());
        output.writeDouble(this.getEndX());
        output.writeDouble(this.getEndY());
        
        Color colorStroke = (Color)this.getStroke();
        output.writeDouble(colorStroke.getRed());
        output.writeDouble(colorStroke.getGreen());
        output.writeDouble(colorStroke.getBlue());
        output.writeDouble(colorStroke.getOpacity());
        
    }
    
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {

        input.defaultReadObject();
        this.setLayoutX(input.readDouble());
        this.setLayoutY(input.readDouble());
        this.setStartX(input.readDouble());
        this.setStartY(input.readDouble());
        this.setEndX(input.readDouble());
        this.setEndY(input.readDouble());
        this.setStroke(new Color(input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble()));
        
    }
}