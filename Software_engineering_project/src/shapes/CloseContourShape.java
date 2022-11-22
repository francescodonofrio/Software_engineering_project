package shapes;

import java.io.Serializable;

public abstract class CloseContourShape implements Shape, Serializable {

    protected SerializableShape shape;

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
    public void copy(SerializableShape clipboard){
        clipboard=this.shape;
    }
}
