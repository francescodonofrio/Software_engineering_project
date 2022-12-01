package action;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.Event;

public class Invoker {
    private final Deque<Action> actions;

    /**
     * Returns a new instance of Invoker, capable of memorizing the sequence
     * of actions that have been executed
     */
    public Invoker() {
        this.actions = new ArrayDeque<>();
    }

    /**
     * Executes a specified action, saving it in the internal collection for further operations
     *
     * @param action the action to execute
     * @param event  the event of the mouse click
     */
    public void execute(Action action, Event event) {
        try {
            action.execute(event);
            this.actions.push(action);
        } catch (Exception ex) {
            System.out.println("Unable to execute this action: \"" + ex + "\" !\n");
        }
    }

    /**
     * Executes the onMouseDragged method of the action passing to it the event as parameter
     *
     * @param action the action on whom call the onMouseDragged method
     * @param event  the event passed to the onMouseDragged method of the action
     */
    public void executeOnMouseDragged(Action action, Event event) {
        action.onMouseDragged(event);
    }

    /**
     * Executes the onMouseReleased method of the action passing to it the event as parameter
     *
     * @param action the action on whom call the onMouseReleased method
     * @param event  the event passed to the onMouseReleased method of the action
     */
    public void executeOnMouseReleased(Action action, Event event) {
        try {
            action.onMouseReleased(event);
        } catch (Exception ex) {
            this.actions.pop();
        }
    }

}
