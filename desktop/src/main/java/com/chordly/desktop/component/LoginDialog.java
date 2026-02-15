package com.chordly.desktop.component;

import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.LoginFailedEvent;
import com.chordly.desktop.model.event.LoginSuccessEvent;
import com.chordly.desktop.service.AuthService;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginDialog extends Dialog<Boolean> {
    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;
    private ProgressIndicator progressIndicator;
    private AuthService authService;
    private boolean loginSuccess = false;

    public LoginDialog() {
        authService = new AuthService();
        initDialog();
        setupListeners();
    }

    private void initDialog() {
        setTitle("Login to Chordly");
        setHeaderText("Sign in to your account");

        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefWidth(250);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(250);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(30, 30);
        progressIndicator.setVisible(false);

        content.getChildren().addAll(
                new Label("Username:"),
                usernameField,
                new Label("Password:"),
                passwordField,
                errorLabel,
                progressIndicator
        );

        dialogPane.setContent(content);

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        okButton.setText("Login");

        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    showError("Please fill in all fields");
                    return false;
                }

                showLoading(true);
                authService.login(username, password);

                // Dialog zamknie się dopiero po otrzymaniu eventu
                return null;
            }
            return false;
        });
    }

    private void setupListeners() {
        EventBus.getInstance().subscribe(LoginSuccessEvent.class, event -> {
            Platform.runLater(() -> {
                showLoading(false);
                loginSuccess = true;
                close();
            });
        });

        EventBus.getInstance().subscribe(LoginFailedEvent.class, event -> {
            Platform.runLater(() -> {
                showLoading(false);
                showError(event.getErrorMessage());
            });
        });
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    private void showLoading(boolean loading) {
        progressIndicator.setVisible(loading);
        usernameField.setDisable(loading);
        passwordField.setDisable(loading);

        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(loading);
    }
}