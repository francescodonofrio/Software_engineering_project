package serializedIO;

import java.io.File;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileIOTest {

    SerializableEllipse testEllipse;
    SerializableLine testLine;
    SerializableRectangle testRectangle;
    Color testColor;
    Pane testPane;
    Pane testPaneEmpty;
    File testFile;
    File testFileEmpty;
    File testFileNull;

    public FileIOTest() {
    }

    @Before
    public void setUp() {
        testEllipse = new SerializableEllipse(29, 73, 24, 55);
        testLine = new SerializableLine(45, 93, 84, 123);
        testRectangle = new SerializableRectangle(66, 82, 23, 17);

        testEllipse.setFill(Color.CHOCOLATE);
        testRectangle.setFill(Color.MEDIUMORCHID);
        testEllipse.setStroke(Color.DARKGREEN);
        testRectangle.setStroke(Color.GOLDENROD);
        testLine.setStroke(Color.DARKSALMON);

        testPane = new Pane();
        testPane.getChildren().add(testLine);
        testPane.getChildren().add(testEllipse);
        testPane.getChildren().add(testRectangle);

        testFile = new File("testFile.bin");
                
        testFileEmpty = new File("testFileEmpty.bin");
        testPaneEmpty = new Pane();
        testFileNull = null;
    }

    /**
     * Test of save method, of class FileIO.
     */
    @Test
    public void testASave() {
        System.out.println("save");

        FileIO saveEmpty = new FileIO(testPaneEmpty);
        saveEmpty.save(testFileEmpty);
        
        assertNotNull(testFileEmpty);
        assertTrue(testFileEmpty.exists());
        assertTrue(testFileEmpty.canWrite());
        assertNotEquals(0, testFileEmpty.length()); // file is not really "empty" because the number of shapes is writed on it also is it 0.
        
        FileIO save = new FileIO(testPane); 
        save.save(testFile);
        save.save(testFileNull);

        assertNotNull(save);  
        assertNotNull(testFile);
        assertNull(testFileNull);  
        assertTrue(testFile.exists());
        assertTrue(testFile.canWrite());
        assertNotEquals(0, testFile.length());

    }

    /**
     * Test of load method, of class FileIO.
     */
    @Test
    public void testBLoad() {
        System.out.println("load");

        Pane loadedPane = new Pane();
        FileIO loadEmpty = new FileIO(loadedPane);
        loadEmpty.load(testFileEmpty);
        
        List expectedList = testPaneEmpty.getChildren();
        List actualList = loadedPane.getChildren();
        
        assertNotNull(loadEmpty);
        assertTrue(testFileEmpty.canRead());
        assertEquals(expectedList.toString(), actualList.toString());
        
        FileIO load = new FileIO(loadedPane);
        load.load(testFile);
        load.load(testFileNull);

        expectedList = testPane.getChildren();
        actualList = loadedPane.getChildren();
        
        assertNotNull(load);
        assertNotNull(testFile);
        assertNull(testFileNull);
        assertTrue(testFile.canRead());
        assertEquals(expectedList.toString(), actualList.toString());

    }

}
