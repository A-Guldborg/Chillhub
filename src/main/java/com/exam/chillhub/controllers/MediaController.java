package com.exam.chillhub.controllers;

import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MediaController {
    @FXML
    private ImageView poster;
    @FXML
    private Label title;
    @FXML
    private Button playBtn;
    @FXML
    private Button favoriteBtn;

    public void setModel(Media model) {
        title.textProperty().bindBidirectional(model.nameProperty());
        try {
            poster.setImage(model.getPoster());
        } catch (IOException e) {
            throw new RuntimeException("Could not load poster");
        }
    }
}