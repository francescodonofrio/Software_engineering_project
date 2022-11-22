/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author franc
 */
public class WindowController implements Initializable {

    @FXML
    private Pane drawingPane;
    @FXML
    private Button lineSegmentBtn;
    @FXML
    private Button rectangleBtn;
    @FXML
    private Button ellipseBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lineSegmentSelection(ActionEvent event) {
    }

    @FXML
    private void rectangleSelection(ActionEvent event) {
    }

    @FXML
    private void ellipseSelection(ActionEvent event) {
    }
    
}
