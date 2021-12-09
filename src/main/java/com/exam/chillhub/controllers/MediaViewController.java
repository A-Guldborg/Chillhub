package com.exam.chillhub.controllers;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.Season;
import com.exam.chillhub.models.Series;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class MediaViewController extends MediaController {
    @FXML
    private Label title;

    @FXML
    private ScrollPane pane;

    @FXML
    private Accordion seasonsPane;

    private AnchorPane anchor;

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
        // pane.setContent(seasonsPane);
    }

    public void setModel(Media model) {
        if (this.model != null) {
            title.textProperty().unbindBidirectional(this.model.titleProperty());
        }
        super.setModel(model);
        this.title.textProperty().bindBidirectional(model.titleProperty());
    }
}
