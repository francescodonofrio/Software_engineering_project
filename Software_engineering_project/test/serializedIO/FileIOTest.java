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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vince
 */
public class FileIOTest {
    
    SerializableEllipse testEllipse;
    SerializableLine testLine;
    SerializableRectangle testRectangle;   
    Color testColor;
    Pane testPane;
    File testFile;
    
    public FileIOTest() {
    }
    
    @Before
    public void setUp() {
        testEllipse = new SerializableEllipse(50, 50, 30, 30);
        testLine = new SerializableLine(40, 40, 70, 70);
        testRectangle = new SerializableRectangle(40, 50, 60,40);
        
        testColor = new Color(0,0,0,0);
        testEllipse.setFill(testColor);
        testRectangle.setFill(testColor);
        testEllipse.setStroke(testColor);
        testRectangle.setStroke(testColor);
        testLine.setStroke(testColor);
        
        testPane = new Pane();
        testPane.getChildren().add(testLine);
        testPane.getChildren().add(testEllipse); 
        testPane.getChildren().add(testRectangle);
        
        testFile = new File("testFile.bin");
    }
    

    /**
     * Test of save method, of class FileIO.
     * PENSO CHE LA SAVE E LA LOAD VANNO TESTATE IN UN SOLO METODO
     * oppure 
     * DEFINIRE IL PANE CON LE IMMAGINI NELLA CLASSE DEL TEST E USARLI NEI METODI DI Save e Load test
     */
    @Test
    public void testSave() throws IOException {   
        System.out.println("save");

        FileIO save = new FileIO(testPane); 
        save.save(testFile);
        
        assertEquals(true, testFile.exists());
        assertEquals(true, testFile.canWrite());
        assertNotEquals(0, testFile.length());
//        assertEquals(469, testFile.length()); // file with the pane with the added shape in setUp() have size of 469 byte
        
    }

    /**
     * Test of load method, of class FileIO.
     * PENSO CHE LA SAVE E LA LOAD VANNO TESTATE IN UN SOLO METODO
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        
        Pane loadedPane = new Pane();
        FileIO load = new FileIO(loadedPane); 
        load.load(testFile);
        
        List testList1 = testPane.getChildren();
        List testList2 = loadedPane.getChildren();
        
        assertEquals(true, testFile.canRead());
        assertEquals(testList1.toString(), testList2.toString());
        
    }
    
}
