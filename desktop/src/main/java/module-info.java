module com.chordly.desktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.chordly.desktop to javafx.fxml;
    exports com.chordly.desktop;
}