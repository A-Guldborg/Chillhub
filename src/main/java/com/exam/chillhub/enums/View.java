package com.exam.chillhub.enums;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

import static com.exam.chillhub.ChillhubApplication.getResource;

/**
 * Enum of all available views that can be loaded.
 */
public enum View {
    MainView("views/main-view.fxml"),
    LoginView("views/login-view.fxml"),
    AccountView("views/account-view.fxml"),
    User("views/user.fxml"),
    UserView("views/user-view.fxml"),
    HomeView("views/home-view.fxml"),
    FilterView("views/filter-view.fxml"),
    Category("views/category.fxml"),
    Media("views/media.fxml"),
    MediaView("views/media-view.fxml");

    /**
     * Represents a loaded view, i.e. a view as a node and the loader used to load it.
     */
    public static record LoadedView(FXMLLoader loader, Node node) {}

    private String fxmlPath;

    View(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    /**
     * Load the given view.
     * @return A LoadedView with the views FXMLLoader and loaded Node.
     * @throws RuntimeException if loading failed. This should only happen during development because loading only fails
     * due to malformed fxml or poor linking to the controller.
     */
    public LoadedView load() {
        var loader = new FXMLLoader(getResource(fxmlPath));
        Node node;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new LoadedView(loader, node);
    }
}
