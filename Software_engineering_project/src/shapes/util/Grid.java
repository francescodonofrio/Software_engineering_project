package shapes.util;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;



public class Grid extends Pane{
    
    private final double width, height;
    private double size;
    private final double cmToPixel =  37.7952755906;
    private final double gridOpacity =  0.5;
    
    /**
     * Returns a new instance of Grid
     *
     * @param drawingPane of the main program
     * @param actualSize of grid cell
     * @param isVisible 
     */
    public Grid(Pane drawingPane, double actualSize, boolean isVisible) {    
        this.width = drawingPane.getPrefWidth();
        this.height = drawingPane.getPrefHeight();
        this.size = actualSize;
        super.setPrefSize(width, height);
        super.setVisible(isVisible);
        this.setMouseTransparent(true);
        this.setManaged(false);
        this.setOpacity(gridOpacity);
        this.makeGrid(size);
    }
    
    
    private Line makeRow(double x){
        Line row = new Line(x,0,x,height);
        row.setStroke(new Color(0,0,0,0.5));
        return row;
    }
    
    private Line makeColumn(double y){
       Line column = new Line(0,y,width,y);
       column.setStroke(new Color(0,0,0,0.5));
       return column;
    }
    
    private void makeGrid(double size){   
        double cellSize = size * cmToPixel;
        
        for (int x=1 ; x*cellSize < width; x++)
            super.getChildren().add(makeRow(x*cellSize));
        
        for (int y=1 ; y*cellSize < height; y++)
            super.getChildren().add(makeColumn(y*cellSize));
    }
    
    /**
     * Resize the grid, with a new size for the cells of grids.
     * @param newSize the size in cm of the grid's square
     */
    public void resize(double newSize){
        super.getChildren().clear();
        this.size = newSize;
        this.makeGrid(newSize);
    }

    public double getSize() {
        return size;
    }
    
}
