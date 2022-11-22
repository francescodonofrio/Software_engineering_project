package shapes;

import javafx.scene.shape.Ellipse;

public class EllipseShape extends CloseContourShape{

    /**
     * The constructor of an EllipseShape
     */
    public EllipseShape(){
        this.shape=  new Ellipse(60,150);
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
