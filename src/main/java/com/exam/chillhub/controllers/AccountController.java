package com.exam.chillhub.controllers;

import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.exam.chillhub.models.Account;
import java.io.IOException;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

import static com.exam.chillhub.ChillhubApplication.getResource;
public class AccountController {
    @FXML
    private HBox users;

    private Account model;

    @FXML
    private TextField username;

    public void initialize() {
        setModel(AccountDB.instance.getAccounts().get(0));
    }

    public void setModel(Account model) {
        this.model = model;

        for (var user : model.getUsers()) {
            addUser(user);
        }
    }

    private void addUser(User user) {
        FXMLLoader fxmlloader = new FXMLLoader(getResource("User-view.fxml"));
        try {
            users.getChildren().add(fxmlloader.load());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        UserController controller = fxmlloader.getController();
        controller.setModel(user);
    }

    @FXML
    public void addUserAction() {
        //username.visibleProperty().set(true);
        var newUser = new User("New user");
        model.addUser(newUser);
        addUser(newUser);
    }

    @FXML
    public void deleteUserAction() {

    }
}




