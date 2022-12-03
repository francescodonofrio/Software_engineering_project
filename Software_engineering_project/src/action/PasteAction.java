package action;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

public class PasteAction implements Action {

    private final Clipboard clipboard;
    private final ObservableList<ShapeInterface> listInsertedShapes;

    /**
     * Returns a new instance of PasteAction
     *
     * @param clipboard          the clipboard where the shape will be saved
     * @param listInsertedShapes the list in which are stored the shapes
     */
    public PasteAction(Clipboard clipboard, ObservableList<ShapeInterface> listInsertedShapes) {
        this.clipboard = clipboard;
        this.listInsertedShapes = listInsertedShapes;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    @Override
    public void execute(Event event) throws Exception {
        MouseEvent mouseEvent = (MouseEvent) event;
        ShapeInterface shapeToPaste = clipboard.getContent();
        shapeToPaste.setX(mouseEvent.getX());
        shapeToPaste.setY(mouseEvent.getY());
        listInsertedShapes.add(shapeToPaste);
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

}
