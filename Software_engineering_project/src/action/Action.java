package action;

public interface Action {
    /**
     * A function used to execute the action specified by the class
     * @throws java.lang.Exception
     */
    public void execute() throws Exception;

    /**
     * A function used to undo the action specified by the class
     * @throws java.lang.Exception
     */
    public void undo() throws Exception;
}
