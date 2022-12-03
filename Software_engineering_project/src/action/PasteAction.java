/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import shapes.ShapeInterface;
import shapes.util.Clipboard;

/**
 *
 * @author vince
 */
public class PasteAction implements Action{
    
    private final Clipboard clipboard;
    private final ObservableList<ShapeInterface> listInsertedShapes;

    public PasteAction(Clipboard clipboard, ObservableList<ShapeInterface> listInsertedShapes) {
        this.clipboard = clipboard;
        this.listInsertedShapes = listInsertedShapes;
    }

    @Override
    public void execute(Event event) throws Exception {
        MouseEvent mouseEvent = (MouseEvent)event;
        ShapeInterface shapeToPaste = clipboard.getValue();
        shapeToPaste.setX(mouseEvent.getX());
        shapeToPaste.setY(mouseEvent.getY());
        listInsertedShapes.add(shapeToPaste);        
    }

    @Override
    public void onMouseDragged(Event event) {
    }

    @Override
    public void onMouseReleased(Event event) throws Exception {
    }
    
}
