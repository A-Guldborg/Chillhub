package com.exam.chillhub.controllers;
import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class UserController {
    @FXML
    public TextField DefaultUsernameUser;

    private User model;

    public void setModel(User model) {
        this.model = model;
        DefaultUsernameUser.textProperty().bindBidirectional(model.nameProperty());
    }

}
