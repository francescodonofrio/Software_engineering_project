package shapes;

public interface Shape {
    public void focus();
    public void copy(javafx.scene.shape.Shape clipboard);
    public double getX();
    public double getY();
    public void setX(double X);
    public void setY(double Y);
}
