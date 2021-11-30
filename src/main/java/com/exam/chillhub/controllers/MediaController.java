package com.exam.chillhub.controllers;

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

    @FXML
    public void initialize() throws IOException {
        poster.setImage(new Movie("ET", "20", 1).getPoster());
    }
}