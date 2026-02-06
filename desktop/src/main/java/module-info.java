module com.chordly.desktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;

    opens com.chordly.desktop to javafx.fxml;
    exports com.chordly.desktop;
    opens com.chordly.desktop.controller to javafx.fxml;
    exports com.chordly.desktop.controller;
}
