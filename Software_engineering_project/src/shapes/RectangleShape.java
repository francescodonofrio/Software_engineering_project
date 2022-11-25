package shapes;


import serializedIO.SerializableRectangle;

public class RectangleShape extends CloseContourShape {

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new SerializableRectangle();
    }

    @Override
    public void setDim(double initialDim1, double initialDim2, double finalDim1, double finalDim2) {
        javafx.scene.shape.Rectangle rectangle = (javafx.scene.shape.Rectangle)shape;
        double width = finalDim1 - initialDim1;
        double height = finalDim2 - initialDim2;
        if(width < 0){
            width = - width;        
            rectangle.setLayoutX(finalDim1);
        }
        if(height < 0){
            height = - height;           
            rectangle.setLayoutY(finalDim2);
        }
        
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }

}