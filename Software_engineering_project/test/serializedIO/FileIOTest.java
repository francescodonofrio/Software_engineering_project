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
    File testFile;

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
    }

    /**
     * Test of save method, of class FileIO.
     */
    @Test
    public void testASave() {
        System.out.println("save");

        FileIO save = new FileIO(testPane);
        save.save(testFile);

        assertNotNull(save);
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
        FileIO load = new FileIO(loadedPane);
        load.load(testFile);

        List expectedList = testPane.getChildren();
        List actualList = loadedPane.getChildren();

        assertNotNull(load);
        assertTrue(testFile.canRead());
        assertEquals(expectedList.toString(), actualList.toString());

    }

}
