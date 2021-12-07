package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class HomeController {
    @FXML
    private VBox categories;

    @FXML
    public void initialize() throws IOException {
        for (var filter : MediaDB.instance.getCategories().values()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getResource("category.fxml"));
            categories.getChildren().add(fxmlLoader.load());
            FilterController controller = fxmlLoader.getController();
            controller.setModel(filter);
        }
    }
}