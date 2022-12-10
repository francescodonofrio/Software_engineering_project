package shapes;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class TextShape extends OpenContourShape  {
    
    /**
     * Creates a new instance of TextShape
     */
    public TextShape() {
        
        this.shape = new Text();

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
        inserted = true;
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
     */
    public double getSizeFont(){
        return ((Text) shape).getFont().getSize();
    }
    
    @Override
    public void setDim(double initialX, double initialY, double finalX, double finalY) {
    }

    @Override
    public double getDimX() {
        return -this.shape.getBoundsInParent().getWidth();
    }

    @Override
    public double getDimY() {
        return this.shape.getBoundsInParent().getHeight();
    }
}