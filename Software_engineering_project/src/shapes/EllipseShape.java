package shapes;

import serializedIO.SerializableEllipse;

public class EllipseShape extends CloseContourShape {

    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new SerializableEllipse();
    }

    @Override
    public void setDim(double initialDim1,double initialDim2, double finalDim1, double finalDim2) {
        javafx.scene.shape.Ellipse ellipse = (javafx.scene.shape.Ellipse)shape;
        double radiusX = finalDim1 - initialDim1;
        double radiusY = finalDim2 - initialDim2;
        if(radiusX < 0){
            radiusX = - radiusX;        
        }
        if(radiusY < 0){
            radiusY = - radiusY;           
        }
        
        ellipse.setRadiusX(radiusX);
        ellipse.setRadiusY(radiusY);
    }
    
}
