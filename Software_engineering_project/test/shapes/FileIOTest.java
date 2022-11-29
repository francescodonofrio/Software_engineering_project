package shapes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/*
    The test methods need to execute in order.
    In JUnit 4 is possible to exucte methods in order of name ascending, so: 
    first testASave(), second testBLoad(). 
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileIOTest {

    private Ellipse testEllipse;
    private Line testLine;
    private Rectangle testRectangle;
    private Pane testPane;
    private Pane testPaneEmpty;
    private File testFile;
    private File testFileEmpty;
    private File testFileNull;

    public FileIOTest() {
        System.out.println("Test File I/O (Load and Save)");
    }

    @Before
    public void setUp() {
        
        testEllipse = new Ellipse(29, 73, 24, 55);
        testLine = new Line(45, 93, 84, 123);
        testRectangle = new Rectangle(66, 82, 23, 17);

        testEllipse.setFill(Color.CHOCOLATE);
        testRectangle.setFill(Color.MEDIUMORCHID);
        testEllipse.setStroke(Color.DARKGREEN);
        testRectangle.setStroke(Color.GOLDENROD);
        testLine.setStroke(Color.DARKSALMON);

        testPane = new Pane();
        testPane.getChildren().add(testLine);
        testPane.getChildren().add(testEllipse);
        testPane.getChildren().add(testRectangle);

        testFile = new File("testFile.xml");
                
        testFileEmpty = new File("testFileEmpty.xml");
        testPaneEmpty = new Pane();
        testFileNull = null;
        
    }

    /**
     * Test of save method, of class FileIO.
     */
    @Test
    public void testASave() {
        System.out.println("Save Test:");

        FileIO saveEmpty = new FileIO(testPaneEmpty);
        try {
            saveEmpty.save(testFileEmpty);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertNotNull(testFileEmpty);
        assertTrue(testFileEmpty.exists());
        assertTrue(testFileEmpty.canWrite());
        assertNotEquals(0, testFileEmpty.length()); // file is not really "empty" because the number of shapes is writed on it also is it 0.
        
        FileIO save = new FileIO(testPane); 
        try {
            save.save(testFile);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            save.save(testFileNull);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(save);  
        assertNotNull(testFile);
        assertNull(testFileNull);  
        assertTrue(testFile.exists());
        assertTrue(testFile.canWrite());
        assertNotEquals(0, testFile.length());
        System.out.println("Passed");
    }

    /**
     * Test of load method, of class FileIO.
     */
    @Test
    public void testBLoad() {
        System.out.println("Load Test:");

        Pane loadedPane = new Pane();
        FileIO loadEmpty = new FileIO(loadedPane);
        try {
            loadEmpty.load(testFileEmpty);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List expectedList = testPaneEmpty.getChildren();
        List actualList = loadedPane.getChildren();
        
        assertNotNull(loadEmpty);
        assertTrue(testFileEmpty.canRead());
        assertEquals(expectedList.toString(), actualList.toString());
        
        FileIO load = new FileIO(loadedPane);
        try {
            load.load(testFile);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            load.load(testFileNull);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        expectedList = testPane.getChildren();
        actualList = loadedPane.getChildren();
        
        assertNotNull(load);
        assertNotNull(testFile);
        assertNull(testFileNull);
        assertTrue(testFile.canRead());
        assertEquals(expectedList.toString(), actualList.toString());
        
        System.out.println("Passed");
    }

}
