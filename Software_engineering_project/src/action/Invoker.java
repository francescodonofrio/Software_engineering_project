package action;

import java.util.Deque;
import java.util.ArrayDeque;

public class Invoker {
    private Deque<Action> actions;

    /**
     *  Constructs an Invoker capable of memorizing the sequence
     *  of actions that have been executed
     */
    public Invoker(){
        this.actions= new ArrayDeque<Action>();
    }

    /**
     * Executes a specified action, saving it in the internal collection for further operations
     * @param action the action to execute
     */
    public void execute(Action action){
        try{
            action.execute();
            this.actions.add(action);
        }catch(Exception ex){
            System.out.println("Unable to execute this action: \" "+ex+" \" !\n");
        }
    }

    /**
     * Undoes the last executed action stored inside the internal collection
     */
    public void undo(){
        try{
            Action action=this.actions.pop();
            action.undo();
        }catch(Exception ex){
            System.out.println("Unable to undo the action: \" "+ex+" \" !\n");
        }
    }

}
