package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
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
        try {
            var img = new Image(ChillhubApplication.class.getResource("play-circle-solid.png").openStream());
            var imgView = new ImageView(img);
            playBtn.setGraphic(imgView);
            checkImg = new ImageView(new Image(ChillhubApplication.class.getResource("check-circle-solid.png").openStream()));
            plusImg = new ImageView(new Image(ChillhubApplication.class.getResource("plus-circle-solid.png").openStream()));
        } catch (IOException e) {
            throw new RuntimeException("Could not load resource");
        }

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

        try {
            poster.setImage(model.getPoster());
            model.favoriteProperty().addListener(favoriteListener);
        } catch (IOException e) {
            throw new RuntimeException("Could not load poster");
        }

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