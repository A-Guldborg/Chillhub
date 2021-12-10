package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.CategoryType;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MediaViewController extends MediaController {
    @FXML
    private Label title;
    @FXML
    private Label year;
    @FXML
    private Label rating;
    @FXML
    private Accordion seasonsPane;
    @FXML
    private VBox contentBox;

    public void initialize() {
        super.initialize();
        Series s = (Series) MediaDB.instance.getDB().search("Game of Thrones").getFilteredData().get(0);
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
            }
            tPane.setContent(vbox);
            seasonsPane.getPanes().add(tPane);
        }
    }

    public void setModel(Media model) {
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
            year.textProperty().unbindBidirectional(this.model.yearProperty());
            rating.textProperty().unbindBidirectional(this.model.ratingProperty());

        }
        super.setModel(model);
        title.textProperty().bindBidirectional(model.titleProperty());
        year.textProperty().bindBidirectional(model.yearProperty());
        rating.textProperty().bind(model.ratingProperty().asString());

        // Todo add filter view for each category
        var categories = MediaDB.instance.getCategories();
        var mediaCategories = model.getCategories();
        for (CategoryType cat : mediaCategories) {
            var loaded = View.Category.load();
            contentBox.getChildren().add(loaded.node());
            FilterViewController controller = loaded.loader().getController();
            controller.onNavigateTo(navigable, categories.get(cat));
        }
    }

    @FXML
    public void onBack() {
        navigable.navigateBack();
    }
}
