package com.exam.chillhub.controllers;

import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Label WrongPasswordOrUsername;
    @FXML
    public Label UsernameAlreadyTaken;

    @FXML
    public void loginAction(ActionEvent actionEvent) {
        boolean found = false;
        for (var acc : AccountDB.instance.getAccounts()) {
            if (acc.getUsername().equals(username.textProperty().get())){
                found = true;
                if (acc.checkPassword(password.textProperty().get())){

                    // todo: open user
                } else {
                    WrongPasswordOrUsername.visibleProperty().setValue(true);
                    break;
                }
            }
        }

        if (!found)
            WrongPasswordOrUsername.visibleProperty().setValue(true);
    }
    public void registerAction(ActionEvent actionEvent) {
        if (username != null && password != null) {
            boolean successfulCreation = AccountDB.instance.addAccount(new Account(username.textProperty().toString(), password.textProperty().toString()));
            if (successfulCreation) {
                // todo: open user, since no need to login again
            } else {
                UsernameAlreadyTaken.visibleProperty().setValue(true);
            }
        }
    }

}

