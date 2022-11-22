package shapes;

import javafx.scene.shape.Ellipse;

public class EllipseShape extends CloseContourShape{

    /**
     * The constructor of an EllipseShape
     */
    public EllipseShape(){
        this.shape=  new Ellipse();
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
