package action;

import exceptions.NotExecutedActionException;
import exceptions.NotShapeToCutException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class CutAction implements Action {
    
    private final Clipboard clipboard;
    private final ObservableList<ShapeInterface> listInsertedShapes;
    private final ShapeInterface shapeToCut;
    private boolean hasNotBeenExecuted;
    /**
     * Returns a new instance of CutAction
     *
     * @param clipboard   the clipboard containing the saved shape
     * @param listInsertedShapes the list that so the shape will removed
     * @param shapeToCut the shape that has to be cutted
     * @throws NotShapeToCutException
     */
    public CutAction(Clipboard clipboard, ObservableList<ShapeInterface> listInsertedShapes, ShapeInterface shapeToCut) throws NotShapeToCutException {
        this.clipboard = clipboard;
        this.listInsertedShapes = listInsertedShapes;
        if (shapeToCut == null)
            throw new NotShapeToCutException();
        else
            this.shapeToCut = shapeToCut;
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
        clipboard.setContent(shapeToCut);
        listInsertedShapes.remove(shapeToCut);
        hasNotBeenExecuted=false;
    }

    @Override
    public void onMouseDragged(Event event) { 
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }

    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();
        listInsertedShapes.add(shapeToCut);
    }
    
    
    
}