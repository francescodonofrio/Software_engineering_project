package shapes;

import serializableShapes.SerializableEllipse;

public class EllipseShape extends CloseContourShape {

    /**
     * The constructor of an EllipseShape
     */
    public EllipseShape() {
        this.shape = new SerializableEllipse(60, 150);
    }
    
}
