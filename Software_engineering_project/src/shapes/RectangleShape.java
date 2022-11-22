package shapes;

import javafx.scene.shape.Rectangle;

public class RectangleShape extends CloseContourShape{

    /**
     * The constructor of a RectangleShape
     */
    public RectangleShape(){
        this.shape= new Rectangle();
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