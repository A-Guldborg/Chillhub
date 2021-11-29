package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
        poster.setImage(new Image(ChillhubApplication.class.getResource("posters/24.jpg").openStream()));
    }
}