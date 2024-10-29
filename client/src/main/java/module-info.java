module client {
    requires annotations;
    requires backend;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;

    exports org.client.controller to  javafx.graphics, backend;
    opens org.client.controller to javafx.fxml, backend;
    exports org.client to backend, javafx.graphics;
    opens org.client to backend, javafx.fxml;

}