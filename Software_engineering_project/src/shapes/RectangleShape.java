package shapes;


import javafx.scene.shape.Rectangle;

public class RectangleShape extends CloseContourShape {

    private double width, height, angle = 0;
    private int conta = 0;

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new Rectangle();

        if (isBeingLoaded || hasBeenInserted) {
            RectangleShape.cont++;
            hasBeenInserted = false;
        }

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
        System.out.println("initialX : "+initialX);
        System.out.println("initialY : "+initialY);
        
//        if(shape.getRotate() != 0){
//            if((shape.getRotate() >= 0 && shape.getRotate() <= 90) || (shape.getRotate() >= -180 && shape.getRotate() < -135))
//                boundX = shape.getBoundsInParent().getMinX();
//            else
//                boundX = shape.getBoundsInParent().getMaxX();
//            shape.setLayoutX(boundX);
//            shape.setLayoutY(shape.getBoundsInParent().getMinY());
//            ((Line) shape).setEndX(finalX - boundX);
//            ((Line) shape).setEndY(finalY - shape.getBoundsInParent().getMinY());
//            shape.setRotate(0);
//        }
//        else{
//            ((Line) shape).setEndX(finalX - initialX);
//            ((Line) shape).setEndY(finalY - initialY);
//        }
        double boundX;
        if(shape.getRotate() != 0 && conta == 0){
            conta++;
            angle = shape.getRotate();
        }
        if(conta == 1){
            ((Rectangle) shape).setWidth(100);
            ((Rectangle) shape).setHeight(150);
        }
//        if(shape.getRotate() != 0 && conta == 0){
//            System.out.println("DENTRO");
//            if((shape.getRotate() >= 0 && shape.getRotate() <= 90) || (shape.getRotate() >= -180 && shape.getRotate() < -135))
//                boundX = shape.getBoundsInParent().getMinX();
//            else
//                boundX = shape.getBoundsInParent().getMaxX();
//            shape.setLayoutX(boundX);
//            shape.setLayoutY(shape.getBoundsInParent().getMinY());
//            initialX = boundX;
//            initialY = shape.getBoundsInParent().getMinY();
//            angle = shape.getRotate();
//            shape.setRotate(0);
//            conta++;
//            width = finalX - initialX;
//            height = finalY - initialY;
//            if (width < 0) 
//                width = -width;
//            if (height < 0) 
//                height = -height;
//        }else{
//            shape.setRotate(0);
//            width = finalX - initialX;
//            height = finalY - initialY;
//            if (width < 0) {
//                width = -width;
//                shape.setLayoutX(finalX);
//            }
//            if (height < 0) {
//                height = -height;
//                shape.setLayoutY(finalY);
//            }
//        }
        else{
            ((Rectangle) shape).setWidth(50);
            ((Rectangle) shape).setHeight(75);
        }
        shape.setRotate(angle);

//        width = finalX - initialX;
//        height = finalY - initialY;
//        if (width < 0) {
//            width = -width;
//            shape.setLayoutX(finalX);
//        }
//        if (height < 0) {
//            height = -height;
//            shape.setLayoutY(finalY);
//        }
//
//        ((Rectangle) shape).setWidth(width);
//        ((Rectangle) shape).setHeight(height);
//
//        hasBeenInserted = true;

        hasBeenInserted = true;
    }
}