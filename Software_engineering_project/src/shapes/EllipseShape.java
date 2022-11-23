package shapes;

import serializableShapes.SerializableEllipse;

public class EllipseShape extends CloseContourShape {

    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new SerializableEllipse(20, 45);
    }
    
}
