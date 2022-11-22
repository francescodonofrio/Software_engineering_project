package shapes;

import java.io.Serializable;

public abstract class OpenContourShape implements Shape, Serializable {
    protected javafx.scene.shape.Shape shape;

    /**
     * A function to make a shape focussed
     */
    @Override
    public void focus(){
        this.shape.setFocused(true);
    }

    /**
     * A function that copies the current shape on the clipboard passed as an argument.
     *
     * @param clipboard the clipboard where to save the current shape
     */
    @Override
    public void copy(javafx.scene.shape.Shape clipboard){
        clipboard=this.shape;
    }
}
