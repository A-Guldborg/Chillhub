package com.exam.chillhub.models;
import java.util.*;
public class Account {
        protected String username;
        protected String password;
        protected List<User> users;

    public Account(String username, String password){
        this.password = password;
        this.username = username;
        users = new ArrayList<>();
    }
    public boolean checkPassword(String EnterYourPassword){
        if(EnterYourPassword.equals(password)){
            return true;
        } else{
            return false;
        }
    }
    public void addUser(User u){
        users.add(u);
    }
    public void getUsers(){
        for(int i = 0; i < users.size(); i++){
            users.get(i);
        }
    }
    }

