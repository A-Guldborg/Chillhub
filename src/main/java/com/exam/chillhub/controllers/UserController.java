package com.exam.chillhub.controllers;
import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class UserController {
    @FXML
    public TextField DefaultUsernameUser;

    public void DefaultUser() {
        for (Account account : AccountDB.instance.getAccounts()) {
            DefaultUsernameUser.textProperty().setValue(account.getUsername());

        }
    }

}
