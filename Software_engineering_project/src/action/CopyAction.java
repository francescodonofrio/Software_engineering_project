package action;

import exceptions.NotExecutedActionException;
import exceptions.ShapeNullException;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CopyAction implements Action {

    private final Clipboard clipboard;
    private final ShapeInterface shapeToCopy;
    private boolean hasNotBeenExecuted;
    private ShapeInterface lastContent;
    /**
     * Returns a new instance of CopyAction
     *
     * @param clipboard   the clipboard containing the saved shape
     * @param shapeToCopy the shape that has to be copied
     * @throws ShapeNullException
     */
    public CopyAction(Clipboard clipboard, ShapeInterface shapeToCopy) throws ShapeNullException {
        this.clipboard = clipboard;
        if (shapeToCopy == null)
            throw new ShapeNullException();
        else
            this.shapeToCopy = shapeToCopy;
        this.hasNotBeenExecuted=true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void execute(Event event) throws Exception {
        lastContent=null;
        if(clipboard.hasContent().get())
            lastContent=clipboard.getContent();

        clipboard.setContent(shapeToCopy);
        hasNotBeenExecuted=false;
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
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if (hasNotBeenExecuted)
            throw new NotExecutedActionException();

        clipboard.setContent(lastContent);
        hasNotBeenExecuted=true;
    }

}
