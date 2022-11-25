package shapes;

import serializedIO.SerializableLine;

public class LineShape extends OpenContourShape {

    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new SerializableLine();
    }

    @Override
    public void setDim(double initialDim1,double initialDim2, double finalDim1, double finalDim2) {
        javafx.scene.shape.Line line = (javafx.scene.shape.Line)shape;
        line.setEndX(finalDim1-initialDim1);
        line.setEndY(finalDim2-initialDim2);
    }

}