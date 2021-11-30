package com.exam.chillhub.database;
import com.exam.chillhub.ChillhubApplication;
import com.exam.chillhub.models.*;

import java.util.*;
import java.io.*;

public class AccountDB {
    private List<Account> AccountDB;
    private final static String dbPath = "accounts.txt";
    private final List<Media> media;

    public final static AccountDB instance;

    static {
        instance = new AccountDB();
    }

    private AccountDB() {
        AccountDB = new ArrayList<>();
        media = MediaDB.instance.getDB();
        readAccounts();
    }

    private void readAccounts() {
        try {
            Scanner inputFile = new Scanner(ChillhubApplication.class.getResource(dbPath).openStream());
            while (inputFile.hasNext()) {
                String[] accountInfo = inputFile.nextLine().split(";");
                Account account = new Account(accountInfo[0], accountInfo[1]);
                for (int i = 0; i < Integer.parseInt(accountInfo[2]); i++) {
                    String[] userInfo = inputFile.nextLine().split(";");
                    User u = new User(userInfo[0], userInfo[1]);
                    String[] favorites = inputFile.nextLine().split(";");
                    for (String fav : favorites) {
                        u.changeFavorite(media.get(Integer.parseInt(fav)));
                    }
                    account.addUser(u);
                }
                AccountDB.add(account);
            }
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAccounts() {
        StringBuilder savetxt = new StringBuilder();
        for (Account acc : AccountDB) {
            List<User> users = acc.getUsers();
            savetxt.append(acc.getUsername() + ";" + acc.getPassword() + ";" + users.size() + ";\n");
            for (User u : users) {
                savetxt.append(u.getName() + ";" + u.getColorString() + ";\n");
                for (Media m : u.getFavorites()) {
                    savetxt.append(m.getIdx() + ";");
                }
                savetxt.append("\n");
            }
        }
        try {
            FileWriter file = new FileWriter("src/main/resources/com/exam/chillhub/" + dbPath, false);
            System.out.println(savetxt);
            file.write(savetxt.toString());
            System.out.println("Accounts saved!");
            file.close();
        } catch (IOException e) {
            System.out.println("Error when saving accounts");
        }
    }

    public void addAccount(Account account) {
        AccountDB.add(account);
        System.out.println("Added account: " + account.getUsername());
    }
}
