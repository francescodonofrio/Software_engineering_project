package shapes;

import java.io.Serializable;
import javafx.scene.shape.Ellipse;

public class EllipseShape extends CloseContourShape{

    /**
     * The constructor of an EllipseShape
     */
    public EllipseShape(){
        this.shape=  new Ellipse();
    }
}
