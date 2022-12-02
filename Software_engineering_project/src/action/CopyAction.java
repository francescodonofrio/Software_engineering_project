package action;

import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CopyAction implements Action {
    
    private Clipboard clipboard;
    private ShapeInterface shapeToCopy;

    public CopyAction(Clipboard clipboard, ShapeInterface shapeToCopy) {
        this.clipboard = clipboard;
        this.shapeToCopy = shapeToCopy;
    }

    @Override
    public void execute(Event event) throws Exception {
        clipboard.setValue(shapeToCopy);
    }

    @Override
    public void onMouseDragged(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
