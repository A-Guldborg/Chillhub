package com.exam.chillhub.controllers;

import static com.exam.chillhub.ChillhubApplication.openResource;
import com.exam.chillhub.models.Media;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MediaController {
    @FXML
    private ImageView poster;
    @FXML
    private AnchorPane popup;
    @FXML
    private Button playBtn;
    @FXML
    private Button favoriteBtn;

    private Media model;
    private ChangeListener<Boolean> favoriteListener;
    private ImageView checkImg;
    private ImageView plusImg;

    public void initialize() {
        var img = new Image(openResource("play-circle-solid.png"));
        var imgView = new ImageView(img);
        playBtn.setGraphic(imgView);
        checkImg = new ImageView(new Image(openResource("check-circle-solid.png")));
        plusImg = new ImageView(new Image(openResource("plus-circle-solid.png")));

        favoriteListener = this::favoriteListenerMethod;
    }

    private void favoriteListenerMethod(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
        updateFavoriteBtn(newValue);
    }

    private void updateFavoriteBtn(boolean checked) {
        if (checked) {
            favoriteBtn.setGraphic(checkImg);
        } else {
            favoriteBtn.setGraphic(plusImg);
        }
    }

    public void setModel(Media model) {
        // Remove current listeners
        if (this.model != null) {
            this.model.favoriteProperty().removeListener(favoriteListener);
        }

        this.model = model;

        poster.setImage(model.getPoster());
        model.favoriteProperty().addListener(favoriteListener);
        updateFavoriteBtn(model.getFavorite());
    }

    @FXML
    protected void onFavoriteAction() {
        // Set favorite to inverse of current value
        model.favoriteProperty().set(!model.getFavorite());
    }

    @FXML
    protected void onMouseEntered() {
        popup.setVisible(true);
    }

    @FXML
    protected void onMouseExited() {
        popup.setVisible(false);
    }
}