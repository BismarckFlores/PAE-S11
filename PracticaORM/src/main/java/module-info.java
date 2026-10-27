module ni.edu.uam.practicaorm {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.practicaorm to javafx.fxml;
    opens ni.edu.uam.practicaorm.controller to javafx.fxml;
    exports ni.edu.uam.practicaorm;
}
