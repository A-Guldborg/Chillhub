package com.exam.chillhub.controllers;

import com.exam.chillhub.models.CategoryType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.io.IOException;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class MainController {
    @FXML
    private Button usersBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private ComboBox<CategoryType> categoryPicker;
    @FXML
    private HBox btnsBox;
    @FXML
    private TextField searchInput;
    @FXML
    private Button searchBtn;
    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize() throws IOException {
        categoryPicker.setItems(FXCollections.observableArrayList(CategoryType.values()));
        resetCategory();
        setView("home-view.fxml");
    }

    private void setView(String name) throws IOException {
        var loader = new FXMLLoader(getResource(name));
        Node node = loader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    private void resetCategory() {
        categoryPicker.promptTextProperty().set("Select category");
    }
}
