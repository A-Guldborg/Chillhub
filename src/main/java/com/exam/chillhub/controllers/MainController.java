package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.models.CategoryType;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.MediaType;
import com.exam.chillhub.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class MainController {
    @FXML
    private Button usersBtn;
    @FXML
    private ComboBox<CategoryType> categoryPicker;
    @FXML
    private TextField searchInput;
    @FXML
    private Button searchBtn;
    @FXML
    private AnchorPane mainPane;

    private User model;
    private MediaType type;
    private final HashMap<MediaType, Node> viewCache = new HashMap<>();

    @FXML
    public void initialize() {
        categoryPicker.setItems(FXCollections.observableArrayList(CategoryType.values()));
        resetCategory();
        setType(MediaType.ANY);
    }

    private void setView(Node node) {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    private FXMLLoader loadView(String name) {
        var loader = new FXMLLoader(getResource(name));
        Node node;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Error loading fxml");
        }

        viewCache.put(this.type, node);
        setView(node);

        return loader;
    }

    private void loadFilter(Filter filter) {
        var loader = loadView("filter-view.fxml");
        FilterController controller = loader.getController();
        controller.setModel(filter);
    }

    private void setType(MediaType type) {
        this.type = type;

        if (viewCache.containsKey(type))
            setView(viewCache.get(type));
        else if (type == MediaType.ANY)
            loadView("home-view.fxml");
        else
            loadFilter(MediaDB.instance.getDB().getFilteredType(type));

    }

    private void resetCategory() {
        categoryPicker.promptTextProperty().set("Select category");
    }

    @FXML
    private void homeAction() {
        setType(MediaType.ANY);
    }

    @FXML
    private void moviesAction() {
        setType(MediaType.MOVIE);
    }

    @FXML
    private void seriesAction() {
        setType(MediaType.SERIES);
    }
}
