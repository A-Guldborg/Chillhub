package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {
    @FXML
    private VBox categories;

    @FXML
    protected void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChillhubApplication.class.getResource("category.fxml"));

        categories.getChildren().add(fxmlLoader.load());
    }
}