package com.exam.chillhub.controllers;

import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class CategoryController {
    @FXML
    private Label title;
    @FXML
    private HBox media;

    private Filter model;

    public void setModel(Filter model) {
        // Unbind current properties
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
        }

        this.model = model;

        // Bind new properties
        this.title.textProperty().bindBidirectional(model.titleProperty());

        // Load all media
        for (Media m : model.getFilteredData()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getResource("media.fxml"));
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