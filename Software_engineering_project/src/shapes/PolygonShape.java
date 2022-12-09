package shapes;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class PolygonShape extends CloseContourShape {

    private double width, height;
    
    /**
     * Creates a new instance of PolygonShape
     */
    public PolygonShape() {
        this.shape = new Polygon();

        boolean updatedCont=false;

        if(PolygonShape.inserted) {
            PolygonShape.cont++;
            updatedCont=true;
        }
        this.width = 0;
        this.height = 0;

        this.name = "Polygon " + PolygonShape.cont;
        rotate = new Rotate();
        translate = new Translate();
        scale = new Scale();
        shape.getTransforms().add(rotate);
        shape.getTransforms().add(scale);
        shape.getTransforms().add(translate);

        if(!updatedCont)
            PolygonShape.cont++;


        this.name = "Polygon " + RectangleShape.cont;
        PolygonShape.cont++;
        PolygonShape.inserted = false;
    }

    /**
     * Updates the dimensions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    @Override
    public void setDim(double initialX, double initialY, double finalX, double finalY) {
        width=finalX-initialX;
        height=finalY-initialY;

        if(width<=20)
            setWidth(20);
        else
            setWidth(width);

        if(height<=20)
            setHeight(20);
        else
            setHeight(height);

        PolygonShape.inserted = true;
    }


    /**
     * Sets the width of the shape
     * @param width the width to set
     */
    private void setWidth(double width) {
        double minX = getMin(((Polygon)shape).getPoints(),0),
                maxX = getMax(((Polygon)shape).getPoints(), 0);

        for (int i=0; i < ((Polygon)shape).getPoints().size(); i+=2){
            double currentX = ((Polygon)shape).getPoints().get(i);
            //if(currentX != minX){
                currentX = width*((currentX-minX)/(maxX-minX))+minX;
                ((Polygon)shape).getPoints().set(i, currentX);
            //}
        }

    }


    /**
     *Sets the height of the shape
     *     @param height the height to set
     */
    private void setHeight(double height) {
        double minY = getMin(((Polygon)shape).getPoints(),1),
               maxY = getMax(((Polygon)shape).getPoints(), 1);

        for (int i=1; i < ((Polygon)shape).getPoints().size(); i+=2){
            double currentY = ((Polygon)shape).getPoints().get(i);
            //if(currentY != minY){
                currentY = height*((currentY-minY)/(maxY-minY))+minY;
                ((Polygon)shape).getPoints().set(i, currentY);
            //}
        }

    }

    /**
     * Searches the minimum value from a list of points, given the index from which to start the research
     * @param points the list of points
     * @param start the index
     * @return the value found
     */
    private double getMin(ObservableList<Double> points, int start) {
        double min = points.get(start);
        for(int i=start+2; i<points.size(); i+=2){
            if (points.get(i) < min) { min = points.get(i); }
        }
        return min;
    }

    /**
     * Searches the maximum value from a list of points, given the index from which to start the research
     * @param points the list of points
     * @param start the index
     * @return the value found
     */
    private double getMax(ObservableList<Double> points, int start) {
        double max = points.get(start);
        for(int i=start+2; i<points.size(); i+=2){
            if (points.get(i) > max) { max = points.get(i); }
        }
        return max;
    }

    @Override
    public double getDimX() {
        return this.width;
    }

    @Override
    public double getDimY() {
        return this.height;
    }
}
