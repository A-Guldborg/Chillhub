package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.database.MediaDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {
    @FXML
    private VBox categories;

    @FXML
    public void initialize() throws IOException {
        for (var filter : MediaDB.instance.getCategories().values()) {
            FXMLLoader fxmlLoader = new FXMLLoader(ChillhubApplication.class.getResource("category.fxml"));
            categories.getChildren().add(fxmlLoader.load());
            CategoryController controller = fxmlLoader.getController();
            controller.setModel(filter);
        }
    }
}