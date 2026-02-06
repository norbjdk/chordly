package com.chordly.desktop.component;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

import static com.chordly.desktop.util.IconTool.setIcon;

public class ProfileBar extends HBox {
    private Image profileImg;
    private String name;

    private Circle circle;
    private Label nameLabel;

    public ProfileBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/sidebar.css")).toExternalForm());
        getStyleClass().add("profile-bar");

        setImage("/com/chordly/desktop/asset/icon/user.png");
        addElements();
        setName("Norbert Dominkiewicz");
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
        getChildren().add(imgContainer);
        // Name Label
        nameLabel = new Label(name);
        StackPane lblContainer = new StackPane();
        lblContainer.setAlignment(Pos.TOP_CENTER);
        lblContainer.getChildren().add(nameLabel);
        getChildren().add(lblContainer);
        // Notification Button
        Button notificationBtn = new Button();
        setIcon(notificationBtn, FontAwesomeSolid.BELL, 14);
        StackPane btnContainer = new StackPane();
        btnContainer.setAlignment(Pos.BOTTOM_CENTER);
        btnContainer.getChildren().add(notificationBtn);
        getChildren().add(btnContainer);
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
            nameLabel.setText(name.replace(" ", "\n"));
        });
    }
}
