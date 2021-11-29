module com.exam.chillhub {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.exam.chillhub to javafx.fxml, javafx.graphics;
    opens com.exam.chillhub.controllers to javafx.fxml, javafx.graphics;
    exports com.exam.chillhub;
    exports com.exam.chillhub.controllers;
}
