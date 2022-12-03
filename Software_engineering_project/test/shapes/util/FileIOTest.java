package shapes.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import shapes.EllipseShape;
import shapes.LineShape;
import shapes.RectangleShape;
import shapes.ShapeInterface;

/*
    The test methods need to execute in order.
    In JUnit 4 is possible to exucte methods in order of name ascending, so: 
    first testASave(), second testBLoad(). 
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileIOTest {

    Color internalColor, contourColor;
    private EllipseShape testEllipse;
    private LineShape testLine;
    private RectangleShape testRectangle;
    private Pane testPane;
    private File testFile;
    private File testFileEmpty;
    private File testFileNull;
    private ObservableList<ShapeInterface> listInsertedShapes;
    private double layoutX, layoutY, initialDim1, initialDim2, finalDim1, finalDim2;

    public FileIOTest() {
        System.out.println("Test File I/O (Load and Save)");
    }

    @Before
    public void setUp() {

        testPane = new Pane();
        listInsertedShapes = FXCollections.observableArrayList();
        listInsertedShapes.addListener((ListChangeListener.Change<? extends ShapeInterface> change) -> {
            while (change.next()) {
                change.getRemoved().forEach(remItem -> {
                    testPane.getChildren().remove(remItem.getShape());
                });
                change.getAddedSubList().forEach(addItem -> {
                    testPane.getChildren().add(addItem.getShape());
                });
            }
        });

        testRectangle = new RectangleShape();
        testEllipse = new EllipseShape();
        testLine = new LineShape();

        layoutX = 100;
        layoutY = 150;
        internalColor = Color.GREEN;
        contourColor = Color.BEIGE;
        initialDim1 = 70.0;
        initialDim2 = 130.0;
        finalDim1 = 150.0;
        finalDim2 = 300.0;

        testRectangle.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        testRectangle.setProperties(layoutX, layoutY, internalColor, contourColor);

        testEllipse.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        testEllipse.setProperties(layoutX, layoutY, internalColor, contourColor);

        testLine.setDim(initialDim1, initialDim2, finalDim1, finalDim2);
        testLine.setProperties(layoutX, layoutY, internalColor, contourColor);

        testFile = new File("testFile.xml");
        testFileEmpty = new File("testFileEmpty.xml");
        testFileNull = null;
    }

    /**
     * Test of save method, of class FileIO.
     */
    @Test
    public void testASave() {
        System.out.println("Save Test:");

        FileIO saveEmpty = new FileIO(listInsertedShapes);
        try {
            saveEmpty.save(testFileEmpty);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(testFileEmpty);
        assertTrue(testFileEmpty.exists());
        assertTrue(testFileEmpty.canWrite());
        assertNotEquals(0, testFileEmpty.length());
        
        listInsertedShapes.add(testRectangle);
        listInsertedShapes.add(testEllipse);
        listInsertedShapes.add(testLine);

        FileIO save = new FileIO(listInsertedShapes);
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

        List expectedList = testPane.getChildren();

        FileIO loadEmpty = new FileIO(listInsertedShapes);
        try {
            loadEmpty.load(testFileEmpty);
        } catch (IOException ex) {
            Logger.getLogger(FileIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List actualList = testPane.getChildren();

        assertNotNull(loadEmpty);
        assertTrue(testFileEmpty.canRead());
        assertEquals(expectedList.toString(), actualList.toString());

        FileIO load = new FileIO(listInsertedShapes);
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

        assertNotNull(load);
        assertNotNull(testFile);
        assertNull(testFileNull);
        assertTrue(testFile.canRead());
        assertEquals(expectedList.get(0).toString(), testRectangle.getShape().toString());
        assertEquals(expectedList.get(1).toString(), testEllipse.getShape().toString());
        assertEquals(expectedList.get(2).toString(), testLine.getShape().toString());

        System.out.println("Passed");
    }

}
