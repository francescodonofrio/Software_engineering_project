package action;

import javafx.event.Event;

public interface Action {
    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    void execute(Event event) throws Exception;

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    void onMouseDragged(Event event);

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws Exception if something goes wrong
     */
    void onMouseReleased(Event event) throws Exception;

}