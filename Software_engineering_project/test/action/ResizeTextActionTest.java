/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import shapes.TextShape;

public class ResizeTextActionTest {
    private ResizeTextAction action;
    private TextShape textShape;
    private ActionEvent event;
    private double newSize = 40;

    @Before
    public void setUp() {
        // Shapes set up
        Application.run(()->{});
        
        textShape = new TextShape();
        
        event = new ActionEvent();
    }
    /**
     * Test of execute method, of class ResizeTextAction.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.print("execute: ");
        
        action= new ResizeTextAction(textShape,newSize);
        
        action.execute(event);
        
        assertEquals(textShape.getSizeFont(),newSize,0.1);
        
        System.out.println("Passed");
    }
    
    /**
     * Test of undo method, of class ResizeTextAction.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.print("undo: ");
        
        double oldSize=textShape.getSizeFont();
        
        action = new ResizeTextAction(textShape,newSize);
        action.execute(event);
        
        action.undo();
        
        assertEquals(textShape.getSizeFont(),oldSize,0.1);
 
        action.undo();

        System.out.println("Passed");
    }
    
}
