package action;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface Action {
    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    void execute(MouseEvent event) throws Exception;

    /**
     * Executes the action specified by the calling class when there are some action
     *
     * @param event the event of some action
     * @throws Exception if something goes wrong
     */
    void execute(ActionEvent event) throws Exception;
    
    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    void onMouseDragged(MouseEvent event);

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    void onMouseReleased(MouseEvent event) throws Exception;

}