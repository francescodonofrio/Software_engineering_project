package action;

import exceptions.VoidActionException;
import javafx.scene.input.MouseEvent;

public interface Action {
    /**
     * Executes the action specified by the calling class
     *
     * @param event the event of the mouse click
     * @throws java.lang.Exception
     */
    void execute(MouseEvent event) throws Exception;

    void onMouseDragged(MouseEvent event);

    void onMouseReleased(MouseEvent event) throws VoidActionException;

}