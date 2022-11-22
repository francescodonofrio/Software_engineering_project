package shapes;

import javafx.scene.shape.Line;

public class LineShape extends OpenContourShape{

    /**
     * The constructor of a LineShape
     */
    public LineShape(){
      //  this.shape=new Line(-100,0,100,0);
    }

    @Override
    public void setX(double X) {
        shape.setLayoutX(X);
    }

    @Override
    public void setY(double Y) {
        shape.setLayoutY(Y);
    }

    @Override
    public javafx.scene.shape.Shape getShape() {
        return shape;
    }
    
}
