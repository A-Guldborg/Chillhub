package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class FilterViewController implements Navigator {
    @FXML
    private Label title;
    @FXML
    private Pane media;

    private Navigable navigable;
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
            var loaded = View.Media.load();
            media.getChildren().add(loaded.node());
            MediaController controller = loaded.loader().getController();
            controller.onNavigateTo(navigable, m);
        }
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((Filter) model);
    }
}
