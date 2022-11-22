package shapes;

import javafx.scene.shape.Line;

public class LineShape extends OpenContourShape{

    /**
     * The constructor of a LineShape
     */
    public LineShape(){
        this.shape=new Line();
    }

    @Override
    public double getX() {
        return shape.getLayoutX();
    }

    @Override
    public double getY() {
        return shape.getLayoutY();
    }

    @Override
    public void setX(double X) {
        shape.setLayoutX(X);
    }

    @Override
    public void setY(double Y) {
        shape.setLayoutY(Y);
    }
    
}
