package shapes;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;

public class PolygonShape extends CloseContourShape {

    /**
     * Creates a new instance of PolygonShape
     */
    public PolygonShape() {
        this.shape = new Polygon();


        this.name = "Polygon " + RectangleShape.cont;
        PolygonShape.cont++;
        PolygonShape.hasBeenInserted = false;
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
        Bounds polygonBounds = shape.getBoundsInParent();
        double minX = polygonBounds.getMinX(),
                minY = polygonBounds.getMinY(),
                maxX = polygonBounds.getMaxX(),
                maxY = polygonBounds.getMaxY(),
                centerX = (minX + maxX) / 2,
                centerY = (minY + maxY) / 2,
                offsetX, offsetY;

        if (finalX > centerX) {
            offsetX = finalX - maxX;
        } else {
            offsetX = minX - finalX;
        }

        if (finalY > centerY) {
            offsetY = finalY - maxY;
        } else {
            offsetY = minY - finalY;
        }

        // This array contains x and y values for each vertex of the shape
        ObservableList<Double> listOfPoints = ((Polygon) shape).getPoints();

        for (int i = 0; i < listOfPoints.size(); i += 2) {
            double originalX = listOfPoints.get(i),
                    originalY = listOfPoints.get(i + 1);

            listOfPoints.remove(i, i + 2);
            if (originalX > centerX) {
                listOfPoints.add(i, originalX + offsetX);
            } else {
                listOfPoints.add(i, originalX - offsetX);
            }

            if (originalY > centerY) {
                listOfPoints.add(i + 1, originalY + offsetY);
            } else {
                listOfPoints.add(i + 1, originalY - offsetY);
            }
        }
    }
}
