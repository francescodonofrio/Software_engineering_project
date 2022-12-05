package action;

import exceptions.NotShapeToCopyException;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CopyAction implements Action {

    private final Clipboard clipboard;
    private final ShapeInterface shapeToCopy;

    /**
     * Returns a new instance of CopyAction
     *
     * @param clipboard   the clipboard containing the saved shape
     * @param shapeToCopy the shape that has to be copied
     * @throws NotShapeToCopyException
     */
    public CopyAction(Clipboard clipboard, ShapeInterface shapeToCopy) throws NotShapeToCopyException {
        this.clipboard = clipboard;
        if (shapeToCopy == null)
            throw new NotShapeToCopyException();
        else
            this.shapeToCopy = shapeToCopy;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void execute(Event event) throws Exception {
        clipboard.setContent(shapeToCopy);
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseReleased(Event event) {
    }

    /**
     * Undoes the action
     */
    @Override
    public void undo() {

    }

}
