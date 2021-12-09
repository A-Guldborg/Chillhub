package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.exam.chillhub.ChillhubApplication.getResource;

public class MediaViewController extends MediaController {
    @FXML
    private Label title;

    @FXML
    private ScrollPane pane;

    @FXML
    private Accordion seasonsPane;

    @FXML
    private VBox contentBox;

    public void initialize() {
        super.initialize();
        Series s = (Series) MediaDB.instance.search("Game of Thrones").getFilteredData().get(0);
        setModel(s);
    }

    public void setModel(Series model) {
        // Overloaded function if and only if it is a season (used to load seasons and episodes)
        setModel((Media) model);
        List<Season> seasons = model.getSeasons();
        for (Season season : seasons) {
            TitledPane tPane = new TitledPane();
            tPane.textProperty().bindBidirectional(season.getTitle());
            VBox vbox = new VBox();
            for (int i = 1; i <= season.getEpisodes(); i++) {
                HBox hbox = new HBox();
                hbox.getChildren().addAll(new Label("Episode " + i), new Button("Play"));
                vbox.getChildren().add(hbox);
                System.out.println("Episode" + i);
            }
            tPane.setContent(vbox);
            seasonsPane.getPanes().add(tPane);
        }
    }

    public void setModel(Media model) {
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
        }
        super.setModel(model);
        this.title.textProperty().bindBidirectional(model.titleProperty());

        // Todo add filter view for each category
        Map<CategoryType, Filter> categories = MediaDB.instance.getCategories();
        List<CategoryType> mediaCategories = model.getCategories();
        for (CategoryType cat : mediaCategories) {
            FXMLLoader filterview = new FXMLLoader(getResource("category.fxml"));
            try {
                contentBox.getChildren().add(filterview.load());
            } catch (IOException e) {
                throw new RuntimeException("Error loading fxml");
            }
            FilterController controller = filterview.getController();
            controller.setModel(categories.get(cat));
        }
    }
}
