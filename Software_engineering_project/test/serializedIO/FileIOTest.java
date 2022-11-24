/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package serializedIO; 

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vince
 */
public class FileIOTest {
    
    public FileIOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of save method, of class FileIO.
     * PENSO CHE LA SAVE E LA LOAD VANNO TESTATE IN UN SOLO METODO
     */
    @Test
    public void testSave() throws IOException {   
        
        System.out.println("save");
        
        SerializableEllipse testEllipse = new SerializableEllipse(50, 50, 30, 30);
        SerializableLine testLine = new SerializableLine(40, 40, 70, 70);
        SerializableRectangle testRectangle = new SerializableRectangle(40, 50, 60,40);
        
        Color testColor = new Color(0,0,0,0);
        testEllipse.setFill(testColor);
        testRectangle.setFill(testColor);
        testEllipse.setStroke(testColor);
        testRectangle.setStroke(testColor);
        testLine.setStroke(testColor);
        
        Pane testPane = new Pane();
        testPane.getChildren().add(testLine);
        testPane.getChildren().add(testEllipse); // NON SI SALVANO LA X E LA Y 
        testPane.getChildren().add(testRectangle);

        FileIO save = new FileIO(testPane); 
        File testFile = new File("testFile.bin");
        save.save(testFile);
        
        Pane newPane = new Pane();
        FileIO load = new FileIO(newPane);
        load.load(testFile);
        
        List testList1 = testPane.getChildren();
        List testList2 = newPane.getChildren();
//        System.out.println(testList1.toString());
//        System.out.println(testList2.toString());
        assertEquals(testList1.toString(), testList2.toString());
        
    }

    /**
     * Test of load method, of class FileIO.
     * PENSO CHE LA SAVE E LA LOAD VANNO TESTATE IN UN SOLO METODO
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        FileIO instance = null;
        File testFile = new File("testFile.bin");
        instance.load(testFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
