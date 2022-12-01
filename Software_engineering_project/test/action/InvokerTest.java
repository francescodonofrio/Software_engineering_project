package action;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InvokerTest {

    private Invoker invoker;
    private List<Integer> test;
    private MouseEvent event;
    private int num, num1, num2;

    public InvokerTest() {
        System.out.println("Test Invoker");
    }

    @Before
    public void setUp() {
        this.invoker = new Invoker();
        this.test = new ArrayList();
    }

    /**
     * Test of execute method, of class Invoker.
     */
    @Test
    public void testExecute() {
        System.out.print("execute: ");
        
        event = new MouseEvent(new EventType("test"), 180, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);

        num = 5;
        Action action = new MockAction(this.test, num);
        invoker.execute(action, event);
        assertEquals(num, this.test.size());
        
        this.test.clear();
        num1 = 3;
        action = new MockAction(this.test, num1);
        invoker.execute(action, event);
        assertEquals(num1, this.test.size());

        num2 = 5;
        action = new MockAction(this.test, num2);
        invoker.execute(action, event);
        assertEquals(num1 + num2, this.test.size());

        System.out.println("Passed");
    }
    
    /**
     * Test of executeOnMouseDragged method, of class Invoker.
     */
    @Test
    public void testExecuteOnMouseDragged() {
        System.out.print("executeOnMouseDragged: ");
        event = new MouseEvent(new EventType("test1"), 180, 200, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
       
        num = 5;
        Action action = new MockAction(this.test, num);
        invoker.executeOnMouseDragged(action, event);
        assertEquals((int)event.getX(), (int)this.test.get(0));
        assertEquals((int)event.getY(), (int)this.test.get(1));
        
        System.out.println("Passed");
    }  
    
    /**
     * Test of executeOnMouseReleased method, of class Invoker.
     */
    @Test
    public void testExecuteOnMouseReleased() {
        System.out.print("executeOnMouseReleased: ");
        event = new MouseEvent(new EventType("test3"), 180, 200, 20, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
       
        num = 5;
        Action action = new MockAction(this.test, num);
        invoker.executeOnMouseReleased(action, event);
        assertEquals((int)event.getX(), (int)this.test.get(0));
        assertEquals((int)event.getY(), (int)this.test.get(1));
        
        event = new MouseEvent(new EventType("test4"), 180, 180, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null);
        
        invoker.execute(action, event);
        invoker.executeOnMouseReleased(action, event);
        
        System.out.println("Passed");
    }    
    
    public class MockAction implements Action {
        private final List<Integer> list;
        private final int num;

        public MockAction(List list, int num) {
            this.list = list;
            this.num = num;
        }

        @Override
        public void onMouseDragged(MouseEvent event) {
            list.add(0, (int)event.getX());
            list.add(1, (int)event.getY());
        }

        @Override
        public void onMouseReleased(MouseEvent event) throws Exception{
            list.add(0, (int)event.getX());
            list.add(1, (int)event.getY());
            if(event.getX() == event.getY())
                throw new Exception();
        }

        @Override
        public void execute(MouseEvent event) throws Exception {
            for (int i = 0; i < this.num; i++)
                list.add(i);
        }
    }

}
