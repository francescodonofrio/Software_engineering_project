package action;

import exceptions.NoActionsException;
import exceptions.NotExecutedActionException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;

import java.util.ArrayDeque;
import java.util.Deque;

public class Invoker {
    private final Deque<Action> actions;
    private final SimpleBooleanProperty emptyQueue;

    /**
     * Returns a new instance of Invoker, capable of memorizing the sequence
     * of actions that have been executed
     */
    public Invoker() {
        this.actions = new ArrayDeque<>();
        emptyQueue = new SimpleBooleanProperty(true);
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
            if (!this.actions.contains(action))
                this.actions.push(action);
            this.emptyQueue.set(false);
        } catch (Exception ex) {
        }
    }

    /**
     * Executes the onMouseDragged method of the action passing to it the event as parameter
     *
     * @param action the action on whom call the onMouseDragged method
     * @param event  the event passed to the onMouseDragged method of the action
     * @throws java.lang.Exception
     */
    public void executeOnMouseDragged(Action action, Event event) throws Exception {
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
            if (this.actions.isEmpty())
                this.emptyQueue.set(true);
        }
    }

    /**
     * Undoes the last executed action
     *
     * @throws exceptions.NoActionsException
     * @throws exceptions.NotExecutedActionException
     */
    public void undo() throws NoActionsException, NotExecutedActionException {
        if (this.actions.isEmpty())
            throw new NoActionsException();

        try {
            Action a = this.actions.pop();
            a.undo();
        } catch (NotExecutedActionException ex) {
            System.out.println(ex);
        }
        if (this.actions.isEmpty())
            this.emptyQueue.set(true);
    }

    /**
     * Returns a boolean property representing the status of the actions queue
     *
     * @return the emptyQueue property
     */
    public BooleanProperty emptyQueueProperty() {
        return this.emptyQueue;
    }

}
