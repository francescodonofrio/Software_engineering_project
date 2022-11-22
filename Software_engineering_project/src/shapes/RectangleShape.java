package shapes;

public class RectangleShape extends CloseContourShape{

    /**
     * The constructor of a RectangleShape
     */
    public RectangleShape(){
        this.shape=new SerializableRectangle();
    }

}