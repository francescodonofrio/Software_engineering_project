package shapes;

import java.io.Serializable;

public class EllipseShape extends CloseContourShape{

    /**
     * The constructor of an EllipseShape
     */
    public EllipseShape(){
        this.shape=new SerializableEllipse();
    }
}
