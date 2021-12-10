package com.exam.chillhub.controllers;
import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Model;
import com.exam.chillhub.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserController implements Navigator {
    @FXML
    public TextField DefaultUsernameUser;

    private Navigable navigable;
    private User model;

    public void setModel(User model) {
        this.model = model;
        DefaultUsernameUser.textProperty().bindBidirectional(model.nameProperty());
    }

    @FXML
    public void onClick() {
        navigable.navigateTo(View.UserView, model);
    }

    @Override
    public void onNavigateTo(Navigable navigable, Model model) {
        this.navigable = navigable;
        setModel((User) model);
    }
}
