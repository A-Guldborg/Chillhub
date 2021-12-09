package com.exam.chillhub;

import com.exam.chillhub.database.AccountDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ChillhubApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    /**
     * Open a resource from the resources directory.
     *
     * @param name The name of the resource to open.
     * @return An InputStream over the resource, or null if it doesn't exist.
     */
    public static InputStream openResource(String name) {
        try {
            var res = getResource(name);
            if (res == null)
                return null;
            return res.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Could not load resource");
        }
    }

    /**
     * Get a resource from the resources directory.
     *
     * @param name The name of the resource to get.
     * @return An URL to the resource or null if it doesn't exist.
     */
    public static URL getResource(String name) {
        return ChillhubApplication.class.getResource(name);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("media-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chillhub");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // When window is closed, this makes sure to save all accounts before closing the program
        AccountDB.instance.saveAccounts();
    }
}
