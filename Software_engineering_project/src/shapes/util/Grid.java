/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes.util;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;



public class Grid extends Pane{
    
    private final double cmToPixel =  37.7952755906;
    private final double width, height;
    
    /**
     * Returns a new instance of Grid
     *
     * @param width 
     * @param height
     */
    public Grid(double width, double height) {    
        this.width = width;
        this.height = height;
        super.setPrefSize(width, height);
        super.setVisible(false);
        this.setMouseTransparent(true);
        this.setManaged(false);
        this.makeGrid(1);
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
    
    private void makeGrid(int size){   
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
    public void resize(int newSize){
        super.getChildren().clear();
        this.makeGrid(newSize);
    }
    
    
}
