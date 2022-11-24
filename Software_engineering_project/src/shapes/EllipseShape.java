package shapes;

import serializedIO.SerializableEllipse;

public class EllipseShape extends CloseContourShape {

    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new SerializableEllipse(20, 45);
    }

    @Override
    public void setDim(double initialDim1,double initialDim2, double finalDim1, double finalDim2) {
        javafx.scene.shape.Ellipse ellipse = (javafx.scene.shape.Ellipse)shape;
        ellipse.setRadiusY(finalDim2-initialDim2);
        ellipse.setRadiusX(finalDim1-initialDim1);
    }
    
}
