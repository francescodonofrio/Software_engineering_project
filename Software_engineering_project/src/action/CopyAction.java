package action;

import exceptions.NotShapeToCopyException;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CopyAction implements Action {
    
    private final Clipboard clipboard;
    private ShapeInterface shapeToCopy;

    public CopyAction(Clipboard clipboard, ShapeInterface shapeToCopy) throws NotShapeToCopyException {
        this.clipboard = clipboard;
        if(shapeToCopy == null)
            throw new NotShapeToCopyException();
        this.shapeToCopy = shapeToCopy;
    }

    @Override
    public void execute(Event event) throws Exception {
        clipboard.setContent(shapeToCopy);
    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }
    
}
