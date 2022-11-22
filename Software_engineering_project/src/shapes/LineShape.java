package shapes;

import javafx.scene.shape.Line;

public class LineShape extends OpenContourShape{

    /**
     * The constructor of a LineShape
     */
    public LineShape(){
        this.shape=new SerializableLine();
    }

}
