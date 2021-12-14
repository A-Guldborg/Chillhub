package com.exam.chillhub.database;

import com.exam.chillhub.models.Account;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import com.exam.chillhub.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDB {
    private final static AccountDB instance;
    private final static String dbPath = "./accounts.txt";

    static {
        instance = new AccountDB();
    }

    private final List<Account> AccountDB;

    private AccountDB() {
        AccountDB = new ArrayList<>();
        readAccounts();
    }

    public static AccountDB getInstance() {
        return instance;
    }

    private void readAccounts() {
        try {
            Scanner inputFile = new Scanner(new FileReader(dbPath));
            while (inputFile.hasNext()) {
                String line = inputFile.nextLine();
                System.out.println(line);
                String[] accountInfo = line.split(";");
                Account account = new Account(accountInfo[0], accountInfo[1]);
                for (int i = 0; i < Integer.parseInt(accountInfo[2]); i++) {
                    String[] userInfo = inputFile.nextLine().split(";");
                    User u = new User(userInfo[0], userInfo[1]);
                    String[] favorites = inputFile.nextLine().split(";");
                    account.addUser(u);
                }
                AccountDB.add(account);
            }
            inputFile.close();
        } catch (IOException e) {
            try {
                FileWriter file = new FileWriter(dbPath, false);
                // Creates standard accounts if it was not possible to read the accounts initially (lack of accounts.txt file)
                file.write("admin;admin;2;\n" +
                        "admin1;0x484d5cff;\n" +
                        "12;19;142;\n" +
                        "admin2;0x0a8f89ff;\n" +
                        "66;87;101;112;188;\n");
                file.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            readAccounts();
        }
    }

    public void saveAccounts() {
        StringBuilder savetxt = new StringBuilder();
        for (Account acc : AccountDB) {
            List<User> users = acc.getUsers();
            savetxt.append(acc.getUsername() + ";" + acc.getPassword() + ";" + users.size() + ";\n");
            for (User u : users) {
                savetxt.append(u.getName() + ";" + u.getColorString() + ";\n");
                for (Media m : u.getFavorites().getFilteredData()) {
                    savetxt.append(m.getIdx() + ";");
                }
                savetxt.append("\n");
            }
        }
        try {
            FileWriter file = new FileWriter(dbPath, false);
            file.write(savetxt.toString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param account Account to be added
     * @return True if account creation succeeds, false if username is already in use
     */
    public boolean addAccount(Account account) {
        for (Account acc : AccountDB) {
            if (acc.getUsername().equals(account.getUsername())) {
                // Account med samme username findes allerede
                return false;
            }
        }
        AccountDB.add(account);
        return true;
    }

    public void removeAccount(Account account) {
        AccountDB.remove(account);
    }

    public List<Account> getAccounts() {
        return this.AccountDB;
    }
}
