package shapes;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class TextShape extends CloseContourShape  {
    
    /**
     * Creates a new instance of TextShape
     */
    public TextShape() {
        
        this.shape = new Text();
        this.shape.setStrokeWidth(1.5);

        if (isBeingLoaded || inserted) {
            TextShape.cont++;
            inserted = false;
        }
        rotate = new Rotate();
        translate = new Translate();
        scale = new Scale();
        shape.getTransforms().add(rotate);
        shape.getTransforms().add(scale);
        shape.getTransforms().add(translate);

        this.name = "Text " + TextShape.cont;
    }
    
    /**
     * Sets a new text for a TextShape
     *
     * @param newText the new text to set
     */
    public void setText(String newText){
        ((Text) shape).setText(newText);
    }
    
    /**
     * Sets a new size font for a TextShape
     *
     * @param sizeText the new size to set
     */
    public void setSizeFont(double sizeText){
        ((Text) shape).setFont(Font.font(sizeText));
    }
    
    /**
     * Get a size font of a TextShape
     *
     * @return the size font of the text shape
     */
    public double getSizeFont(){
        return ((Text) shape).getFont().getSize();
    }

    //16px = 12pt    
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
        Text text= (Text) shape;

        double originalWidth=getDimX(),
                originalHeight=getDimY(),
                newWidth=finalX-initialX,
                newHeight=finalY-initialY;
        if(newHeight<=20)
            newHeight=20;
        if(newWidth<=20)
            newWidth=20;

        Double newScaleX= text.getScaleX() *newWidth/originalWidth,
                newScaleY=text.getScaleY() *newHeight/originalHeight;
        if(newScaleX<0)
            newScaleX*=-1;
        if(newScaleY<0)
            newScaleY*=-1;

        text.setScaleX(newScaleX);
        text.setScaleY(newScaleY);
    }
    
    /**
     * Return the dimension along the x-axis
     * 
     * @return the dimension of the shape along the x-axis
     */    
    @Override
    public double getDimX() {
        return -this.shape.getBoundsInParent().getWidth();
    }
    
    /**
     * Return the dimension along the y-axis
     * 
     * @return the dimension of the shape along the y-axis
     */
    @Override
    public double getDimY() {
        return this.shape.getBoundsInParent().getHeight();
    }
}