module com.chordly.desktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;
    requires proxymusic;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires java.prefs;
    requires nu.xom;

    opens com.chordly.desktop to javafx.fxml;
    exports com.chordly.desktop;
    opens com.chordly.desktop.controller to javafx.fxml;
    exports com.chordly.desktop.controller;

    exports com.chordly.desktop.model.dto.internal to com.fasterxml.jackson.databind;
    exports com.chordly.desktop.model.dto.external to com.fasterxml.jackson.databind;
    exports com.chordly.desktop.model.entity to com.fasterxml.jackson.databind;

    opens com.chordly.desktop.model.dto.internal to com.fasterxml.jackson.databind;
    opens com.chordly.desktop.model.dto.external to com.fasterxml.jackson.databind;
    opens com.chordly.desktop.model.entity to com.fasterxml.jackson.databind;
}
