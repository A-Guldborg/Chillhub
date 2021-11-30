package com.exam.chillhub.controllers;

import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.models.*;
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

    public void setModel(Filter model) {
        this.title.textProperty().bindBidirectional(model.titleProperty());
        for (Media m : model.getFilteredData()) {
            FXMLLoader fxmlLoader = new FXMLLoader(ChillhubApplication.class.getResource("media.fxml"));
            try {
                media.getChildren().add(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException("Error loading fxml");
            }
            MediaController controller = fxmlLoader.getController();
            controller.setModel(m);
        }
    }
}