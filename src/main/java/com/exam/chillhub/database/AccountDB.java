package com.exam.chillhub.database;

import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.exam.chillhub.ChillhubApplication.openResource;

public class AccountDB {
    public final static AccountDB instance;
    private final static String dbPath = "accounts.txt";

    static {
        instance = new AccountDB();
    }

    private final Filter media;
    private List<Account> AccountDB;

    private AccountDB() {
        AccountDB = new ArrayList<>();
        media = MediaDB.instance.getDB();
        readAccounts();
    }

    private void readAccounts() {
        try {
            Scanner inputFile = new Scanner(openResource(dbPath));
            while (inputFile.hasNext()) {
                String[] accountInfo = inputFile.nextLine().split(";");
                Account account = new Account(accountInfo[0], accountInfo[1]);
                for (int i = 0; i < Integer.parseInt(accountInfo[2]); i++) {
                    String[] userInfo = inputFile.nextLine().split(";");
                    User u = new User(userInfo[0], userInfo[1]);
                    String[] favorites = inputFile.nextLine().split(";");
                    for (String fav : favorites) {
                        if (!fav.equals("")) {
                            u.changeFavorite(media.getFilteredData().get(Integer.parseInt(fav)));
                        }
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
            file.close();
        } catch (IOException e) {
            System.out.println("Error when saving accounts");
        }
    }

    public void addAccount(Account account) {
        AccountDB.add(account);
    }

    public void removeAccount(Account account) {
        AccountDB.remove(account);
    }

    public List<Account> getAccounts() {
        return this.AccountDB;
    }
}
