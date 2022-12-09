package shapes;

import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class RectangleShape extends CloseContourShape {

    private double width, height;

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new Rectangle();

        if (isBeingLoaded || inserted) {
            RectangleShape.cont++;
            inserted = false;
        }
        translate = new Translate();
        rotate = new Rotate();
        shape.getTransforms().add(translate);
        shape.getTransforms().add(rotate);

        this.name = "Rectangle " + RectangleShape.cont;
    }

    /**
     * Updates the dimentions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    @Override
    public void setDim(double initialX, double initialY, double finalX, double finalY) {
        if(!inserted){
            width = finalX - initialX;
            height = finalY - initialY;
            double signWidth = 1, signHeight = 1;
            if (width < 0) {
                signWidth = -1;
                translate.setX(width);
            }
            if (height < 0) {
                signHeight = -1;
                translate.setY(height);
            }
            ((Rectangle) shape).setWidth(signWidth*width);
            ((Rectangle) shape).setHeight(signHeight*height);
        }else{
            this.resize(initialX, initialY, finalX, finalY);
        }
    }

    /**
     * Resize the dimension of the shape
     * 
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    private void resize(double initialX, double initialY, double finalX, double finalY) {
        width = finalX - initialX;
        height = finalY - initialY;
        if (width < 0)
            width = 1;
        if (height < 0)
            height = 1;
        ((Rectangle) shape).setWidth(width);
        ((Rectangle) shape).setHeight(height);
    }
}