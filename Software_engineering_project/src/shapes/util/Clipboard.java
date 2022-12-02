package shapes.util;

import shapes.ShapeInterface;

public class Clipboard {
    private ShapeInterface value;
    private static Clipboard instance=null;

    /**
     * Creates a new instance of Clipboard
     */
    private Clipboard(){
        this.instance=this;
    }

    /**
     * Returns a reference to the clipboard, instantiating it if necessary
     * @return a reference to the clipboard
     */
    public static Clipboard getClipboard() {
        if(instance==null)
            return new Clipboard();
        return instance;
    }

    /**
     * Returns the shape memorized in the clipboard
     * @return the memorized shape
     */
    public ShapeInterface getValue(){
        return value;
    }

    /**
     * Saves a new shape into the clipboard
     * @param value the new shape
     */
    public void setValue(ShapeInterface value){
        this.value=value;
    }
}
