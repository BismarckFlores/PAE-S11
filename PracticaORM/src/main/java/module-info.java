module ni.edu.uam.practicaorm {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens ni.edu.uam.practicaorm to javafx.fxml;
    opens ni.edu.uam.practicaorm.controller to javafx.fxml;
    opens ni.edu.uam.practicaorm.model to org.hibernate.orm.core;
    exports ni.edu.uam.practicaorm;
}