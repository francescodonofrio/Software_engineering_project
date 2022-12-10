package shapes.util;

import java.io.ByteArrayOutputStream;
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
import java.lang.IllegalArgumentException;
import java.util.LinkedList;

/*
    The test methods need to execute in order.
    In JUnit 4 is possible to exucte methods in order of name ascending, so: 
    first testASaveFile() and testASaveByteArray(), second testBLoadFile() and testBLoadByteArray() and testBLoadByteArrayEmptyStream(). 
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShapesIOTest {

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
    private byte[] content;

    public ShapesIOTest() {
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
     * Test of save method, of class ShapesIO.
     */
    @Test
    public void testASaveFile() {
        System.out.println("saveFile Test:");

        ShapesIO saveEmpty = new ShapesIO();
        try {
            saveEmpty.saveFile(testFileEmpty,listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(testFileEmpty);
        assertTrue(testFileEmpty.exists());
        assertTrue(testFileEmpty.canWrite());
        assertNotEquals(0, testFileEmpty.length());
        
        listInsertedShapes.add(testRectangle);
        listInsertedShapes.add(testEllipse);
        listInsertedShapes.add(testLine);

        ShapesIO save = new ShapesIO();
        try {
            save.saveFile(testFile, listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            save.saveFile(testFileNull, listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
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
     * Test of loadFile method, of class ShapesIO.
     */
    @Test
    public void testBLoadFile() {
        System.out.println("loadFile Test:");

        List expectedList = testPane.getChildren();

        ShapesIO loadEmpty = new ShapesIO();
        try {
            loadEmpty.loadFile(testFileEmpty, listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List actualList = testPane.getChildren();

        assertNotNull(loadEmpty);
        assertTrue(testFileEmpty.canRead());
        assertEquals(expectedList.toString(), actualList.toString());

        ShapesIO load = new ShapesIO();
        listInsertedShapes.add(testRectangle);
        listInsertedShapes.add(testEllipse);
        listInsertedShapes.add(testLine);

        try {
            load.saveFile(testFile, listInsertedShapes);
            load.loadFile(testFile, listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            load.loadFile(testFileNull, listInsertedShapes);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Test of save method, of class ShapesIO.
     * @throws java.lang.Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testASaveStreamByte() throws IllegalArgumentException, Exception {
        System.out.println("saveStreamByte Test:");
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ShapesIO saveEmpty = new ShapesIO();
        try {
            saveEmpty.saveStreamByte(stream, null);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(stream);
        assertEquals(stream.size(),113);

        ShapesIO save = new ShapesIO();
        try {
            save.saveStreamByte(stream, testEllipse);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            save.saveStreamByte(null, testEllipse);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            if(ex.getClass() == IllegalArgumentException.class )
                throw new IllegalArgumentException();
        }

        assertNotNull(stream);
        assertNotEquals(stream.size(),113);
        
        System.out.println("Passed");
    }

    /**
     * Test of loadFile method, of class ShapesIO.
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testBLoadStreamByte() throws Exception {
        System.out.println("loadStreamByte Test:");

        LinkedList<ShapeInterface> shape = new LinkedList<>();    

        ShapesIO load = new ShapesIO();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        try {
            load.saveStreamByte(stream, testEllipse);
            content = stream.toByteArray();
            load.loadStreamByte(content, shape);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertNotEquals(content.length,0);
        assertEquals(testEllipse.getClass(), shape.get(0).getClass());
        assertEquals(testEllipse.getShape().getClass(), shape.get(0).getShape().getClass());
        assertEquals(testEllipse.getCountourColor(), shape.get(0).getCountourColor());
        assertEquals(testEllipse.getInternalColor(), shape.get(0).getInternalColor());
        
        ShapesIO loadEmpty = new ShapesIO();
        try {
            loadEmpty.loadStreamByte(content, null);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            if(ex.getClass() == NullPointerException.class)
                throw new NullPointerException();
        }

        assertEquals(content.length,0);
        
        System.out.println("Passed");
    }
    
    /**
     * Test of loadFile method, of class ShapesIO.
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testBLoadStreamByteEmptyStream() throws Exception {
        System.out.println("loadStreamByteEmptyStream Test:");

        LinkedList<ShapeInterface> shape = new LinkedList<>();

        ShapesIO load = new ShapesIO();
        try {
            load.loadStreamByte(null, shape);
        } catch (IOException ex) {
            Logger.getLogger(ShapesIOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            if(ex.getClass() == NullPointerException.class )
                throw new NullPointerException();
        }

        System.out.println("Passed");
    }
    
}
