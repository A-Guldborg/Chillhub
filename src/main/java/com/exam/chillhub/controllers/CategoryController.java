package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CategoryController {
    @FXML
    private Label title;
    @FXML
    private HBox media;

    @FXML
    protected void addMedia() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChillhubApplication.class.getResource("media.fxml"));
        media.getChildren().add(fxmlLoader.load());
    }
}