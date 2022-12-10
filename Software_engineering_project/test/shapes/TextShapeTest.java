package shapes;

import com.sun.glass.ui.Application;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class TextShapeTest {
   @Before
    public void initJfxRuntime() {
       Application.run(()->{});
       textShape = new TextShape();
       
   }
   private TextShape textShape;
    
    
    public TextShapeTest() {
        System.out.println("Test TextShape");
    }
  
    
     /**
     * Test of setText method, of class TextShape.
     */
    @Test
    public void testSetText() {
        System.out.print("setText: ");
       
        
        String finalText="final TextShapeTests";

        textShape.setText(finalText);
        
        assertEquals(((Text)textShape.getShape()).getText(),finalText);
    }
    
    /**
     * Test of setSizeFont method, of class TextShape.
     */
    @Test
    public void testSetSizeFont() {
        System.out.print("setText: ");
       
        double finalSize=20;

        textShape.setSizeFont(finalSize);
        
        assertEquals(((Text)textShape.getShape()).getFont().getSize(),finalSize,0.1);
    }
   
    /**
     * Test of getSizeFont method, of class TextShape.
     */
    @Test
    public void testGetSizeFont(){
        double finalSize=20;

        ((Text)textShape.getShape()).setFont(Font.font(finalSize));
        
        assertEquals(textShape.getSizeFont(),finalSize,0.1);
    
    }

    /**
     * Test of getDimX method, of class TextShape.
     */
    @Test
    public void testGetDimX() {
        double test =textShape.getDimX();
        assertEquals(textShape.getDimX(),test,0.1);
    }
 
   /**
     * Test of getDimY method, of class TextShape.
     */
    @Test
    public void testGetDimY() {
        double test =textShape.getDimY();
        assertEquals(textShape.getDimY(),test,0.1);
    }
}