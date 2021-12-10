package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HomeViewController implements Navigator {
    @FXML
    private VBox categories;

    private Navigable navigable;
    private User model;

    public void setModel(User model) {
        this.model = model;
        categories.getChildren().clear();
        addCategory(model.getFavorites());
        for (var filter : MediaDB.instance.getCategories().values()) {
            addCategory(filter);
        }
    }

    public void addCategory(Filter filter) {
        var loaded = View.Category.load();
        categories.getChildren().add(loaded.node());
        FilterViewController controller = loaded.loader().getController();
        controller.onNavigateTo(navigable, filter);
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((User) model);
    }
}