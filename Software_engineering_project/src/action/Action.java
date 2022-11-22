package action;

public interface Action {
    /**
     * A function used to execute the action specified by the class
     */
    public void execute() throws Exception;

    /**
     * A function used to undo the action specified by the class
     */
    public void undo() throws Exception;
}
