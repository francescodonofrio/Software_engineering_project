package action;

import java.awt.geom.Line2D;
import exceptions.NotExecutedActionException;
import exceptions.NotRotatedException;
import exceptions.ShapeNullException;
import javafx.event.Event;
import java.awt.geom.Point2D;
import javafx.scene.input.MouseEvent;
import shapes.ShapeInterface;

public class RotateAction implements Action{

    private final ShapeInterface selectedShape;
    private boolean hasNotBeenExecuted;
    private Line2D.Double xLine, yLine;
    private Point2D.Double centerPoint;
    private double initialAngle;
    /**
     * Returns a new instance of RotateAction
     *
     * @param selectedShape the shape to be rotated
     * @throws exceptions.ShapeNullException
     */
    public RotateAction(ShapeInterface selectedShape) throws ShapeNullException {
        if (selectedShape == null)
            throw new ShapeNullException();
        else
            this.selectedShape = selectedShape;
        this.hasNotBeenExecuted=true;
    }

    /**
     * Executes the action specified by the calling class when the mouse is clicked
     *
     * @param event the event of the mouse click
     */
    @Override
    public void execute(Event event) {
        initialAngle = selectedShape.getShape().getRotate();
        
        double topLeftPointX = selectedShape.getShape().getBoundsInParent().getMinX();
        double topLeftPointY = selectedShape.getShape().getBoundsInParent().getMinY();
        double bottomRightPointX = selectedShape.getShape().getBoundsInParent().getMaxX();
        double bottomRightPointY = selectedShape.getShape().getBoundsInParent().getMaxY();
        
        centerPoint = new Point2D.Double(topLeftPointX + (bottomRightPointX - topLeftPointX)/2, topLeftPointY + (bottomRightPointY - topLeftPointY)/2);
        xLine = new Line2D.Double();
        yLine = new Line2D.Double();
        yLine.setLine(centerPoint,new Point2D.Double(0, centerPoint.y));
        xLine.setLine(centerPoint,new Point2D.Double(centerPoint.x, 0));
        
        this.onMouseDragged(event);
        this.hasNotBeenExecuted=false;
    }

    /**
     * Executes the action specified by the calling class when the mouse is dragged
     *
     * @param event the event of the mouse click
     */
    @Override
    public void onMouseDragged(Event event) {
        MouseEvent mouseEvent = (MouseEvent) event;
        Point2D.Double clickedPoint = new Point2D.Double(mouseEvent.getX(),mouseEvent.getY());
        
        double angle, signAtan, signAngle, distanceX, distanceY;
        signAtan = 1;
        signAngle = 1;
        distanceX = distancePointToLine(xLine,clickedPoint);
        distanceY = distancePointToLine(yLine,clickedPoint);
        
        if(clickedPoint.getX() < centerPoint.getX())
            signAtan = -1;
        if(clickedPoint.getY() < centerPoint.getY())
            signAngle = -1;
        
        if(distanceX > distanceY){
            angle = Math.toDegrees(signAtan * Math.atan(distanceX/distanceY));
            selectedShape.getShape().setRotate(signAngle * (90-angle));
        }else{
            angle = Math.toDegrees(signAtan * Math.atan(distanceY/distanceX));
            selectedShape.getShape().setRotate(signAngle * angle);
        }
    }

    /**
     * Executes the action specified by the calling class when the mouse is released
     *
     * @param event the event of the mouse click
     * @throws exceptions.NotRotatedException
     */
    @Override
    public void onMouseReleased(Event event) throws NotRotatedException  {
        if (selectedShape.getShape().getRotate()-0.1 <= initialAngle && initialAngle <= selectedShape.getShape().getRotate()+0.1)
            throw new NotRotatedException();
    }

    /**
     * Undoes the action
     * 
     * @throws exceptions.NotExecutedActionException
     */
    @Override
    public void undo() throws NotExecutedActionException {
        if(hasNotBeenExecuted)
            throw new NotExecutedActionException();
        
        selectedShape.getShape().setRotate(initialAngle);
        this.hasNotBeenExecuted=true;
    }
    
    /**
     * Return the distance between a line and a point
     * 
     * @param line a Line2D.Double line
     * @param point a Point2D.Double point
     * @return the distance between the line and the point
     */
    private double distancePointToLine(Line2D.Double line, Point2D.Double point){
        final Point2D l1 = line.getP1();
        final Point2D l2 = line.getP2();
        return Math.abs((l2.getX() - l1.getX()) * (l1.getY() - point.getY())- (l1.getX() - point.getX()) * (l2.getY() - l1.getY()))/ Math.sqrt(Math.pow(l2.getX() - l1.getX(), 2) + Math.pow(l2.getY() - l1.getY(), 2));
    }
    
}
