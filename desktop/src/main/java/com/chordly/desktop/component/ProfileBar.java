package com.chordly.desktop.component;

import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.LoginSuccessEvent;
import com.chordly.desktop.model.event.LogoutEvent;
import com.chordly.desktop.service.AuthService;
import com.chordly.desktop.service.UserService;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class ProfileBar extends HBox {
    private Image profileImg;
    private String name;

    private Circle circle;
    private Label nameLabel;
    private Button loginButton;
    private Button logoutButton;

    private AuthService authService;
    private UserService userService;

    public ProfileBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/sidebar.css")).toExternalForm());
        getStyleClass().add("profile-bar");

        authService = new AuthService();
        userService = new UserService();

        setImage("/com/chordly/desktop/asset/icon/user.png");
        addElements();
        setupListeners();

        checkLoginState();
    }

    private void addElements() {
        // Profile Image
        circle = new Circle(25);
        circle.setFill(new ImagePattern(profileImg));
        StackPane imgContainer = new StackPane();
        imgContainer.getChildren().add(circle);
        imgContainer.setPrefSize(50, 50);
        DropShadow shadow = new DropShadow(8, Color.gray(0.5));
        circle.setEffect(shadow);

        nameLabel = new Label("Not logged in");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> showLoginDialog());

        logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logout());
        logoutButton.setVisible(false);

        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(nameLabel, loginButton, logoutButton);

        getChildren().addAll(imgContainer, infoBox);
        setSpacing(10);
    }

    private void setupListeners() {
        EventBus.getInstance().subscribe(LoginSuccessEvent.class, event -> {
            Platform.runLater(() -> {
                setName(event.getUser().getUsername());
                loginButton.setVisible(false);
                logoutButton.setVisible(true);
            });
        });

        EventBus.getInstance().subscribe(LogoutEvent.class, event -> {
            Platform.runLater(() -> {
                setName("Not logged in");
                loginButton.setVisible(true);
                logoutButton.setVisible(false);
            });
        });
    }

    private void checkLoginState() {
        try {
            if (authService.isLoggedIn()) {
                var user = userService.getCurrentUser();
                if (user != null) {
                    setName(user.getUsername());
                    loginButton.setVisible(false);
                    logoutButton.setVisible(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoginDialog() {
        LoginDialog dialog = new LoginDialog();
        dialog.showAndWait();
    }

    private void logout() {
        authService.logout();
    }

    public void setImage(Image image){
        profileImg = image;
    }

    public void setImage(String path) {
        profileImg = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
    }

    public void setName(String name) {
        this.name = name;
        Platform.runLater(() -> {
            nameLabel.setText(name);
        });
    }
}