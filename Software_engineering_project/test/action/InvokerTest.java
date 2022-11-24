package action;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class InvokerTest {
    
    private Invoker invoker;
    private List<Integer> test;

    class MockAction implements Action{
        private final List<Integer> list;
        private final int num;

        public MockAction(List list, int num){
            this.list=list;
            this.num=num;
        }

        @Override
        public void execute(){
            for(int i=0;i<this.num;i++)
                list.add(i);
        }
    }
    
    @Before
    public void setUp() {
        this.invoker=new Invoker();
        this.test=new ArrayList();
    }

    /**
     * Test of execute method, of class Invoker.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        int num=5;
        Action action=new MockAction(this.test,num);
        invoker.execute(action);
        assertEquals(num,this.test.size());
        
        this.test.clear();
        int num1=3;
        action=new TestAction(this.test,num1);
        invoker.execute(action);
        assertEquals(num1,this.test.size());
        
        int num2=5;
        action=new TestAction(this.test,num2);
        invoker.execute(action);
        assertEquals(num1+num2,this.test.size());
    }

}
