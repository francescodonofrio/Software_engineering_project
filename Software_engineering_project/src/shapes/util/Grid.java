package shapes.util;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Grid extends Pane {

    private final double width, height;
    private final double cmToPixel = 37.7952755906;
    private final double gridOpacity = 0.5;
    private double size;

    /**
     * Returns a new instance of Grid
     *
     * @param drawingPane of the main program
     * @param actualSize  of grid cell
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

    /**
     * Make a column in a specified position along x-axis
     *
     * @param x the shape to be save on the clipboard
     */
    private Line makeColumn(double x) {
        Line row = new Line(x, 0, x, height);
        row.setStroke(new Color(0, 0, 0, 0.5));
        return row;
    }
    
    /**
     * Make a row in a specified position along y-axis
     *
     * @param y the shape to be save on the clipboard
     */
    private Line makeRow(double y) {
        Line column = new Line(0, y, width, y);
        column.setStroke(new Color(0, 0, 0, 0.5));
        return column;
    }
    
    /**
     * Make a grid with of specified size
     *
     * @param x the shape to be save on the clipboard
     */
    private void makeGrid(double size) {
        double cellSize = size * cmToPixel;

        for (int x = 1; x * cellSize < width; x++)
            super.getChildren().add(makeColumn(x * cellSize));

        for (int y = 1; y * cellSize < height; y++)
            super.getChildren().add(makeRow(y * cellSize));
    }

    /**
     * Resize the grid, with a new size for the cells of grids.
     *
     * @param newSize the size in cm of the grid's square
     */
    public void resize(double newSize) {
        super.getChildren().clear();
        this.size = newSize;
        this.makeGrid(newSize);
    }
    
    /**
     * Getter for size of the grid
     *
     * @return a double represnt the size of the grid
     */
    public double getSize() {
        return size;
    }

}
