package mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class SoftwareEngineeringProject extends Application {
    /**
     * The main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the JavaFx Application
     *
     * @param stage the main window
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXMLDocument.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMinHeight(800);
        stage.setMinWidth(800);

        stage.setTitle("Geometrical drawing");
        stage.getIcons().add(new Image("icons/icon.png"));
        stage.show();
    }

}
