/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package shapes.util;

import javafx.scene.layout.Pane;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {
    
    public GridTest() {
        System.out.println("Test Grid");
    }

    /**
     * Test of resize method, of class Grid.
     */
    @Test
    public void testResize() {
        System.out.println("resize: ");
        Pane testPane = new Pane();
        testPane.setPrefSize(800, 600);
        int initialSize = 1;
        int newSize = 3;
        Grid instanceOfGrid = new Grid(testPane, initialSize, true);
        
        assertEquals(initialSize, instanceOfGrid.getSize(), 0.1);
        assertEquals(800, instanceOfGrid.getPrefWidth(), 0.1);
        assertEquals(600, instanceOfGrid.getPrefHeight(), 0.1);
        
        instanceOfGrid.resize(newSize);
        assertEquals(newSize, instanceOfGrid.getSize(), 0.1);
        
        System.out.println("Passed");
    }
    
}
