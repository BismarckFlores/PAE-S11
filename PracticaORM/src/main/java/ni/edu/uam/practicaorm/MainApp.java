package ni.edu.uam.practicaorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ni.edu.uam.practicaorm.controller.EstudianteController;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("estudiante-view.fxml"));
        Scene scene = new Scene(loader.load(), 1180, 720);
        scene.getStylesheets().add(MainApp.class.getResource("estudiante.css").toExternalForm());

        EstudianteController controller = loader.getController();
        stage.setOnCloseRequest(event -> controller.cerrar());

        stage.setTitle("Gestión Académica");
        stage.setScene(scene);
        stage.show();
    }
}
