package action;

public interface Action {
    /**
     * Executes the action specified by the calling class
     * @throws java.lang.Exception
     */
    public void execute() throws Exception;

    /**
     * Undoes the action specified by the calling class
     * @throws java.lang.Exception
     */
    public void undo() throws Exception;
}
