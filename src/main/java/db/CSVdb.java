package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Ksenia on 11.12.2016.
 */
public class CSVdb {

    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String FILE_HEADER = "Login,Password";

    private static final String LOGIN_NAME = "Ksenia00";
    private static final String DOMAIN = "@tut.by";
    private static final String PASSWORD = "AutomationTest";
    private static final String PATH = "E:\\java\\EPAM\\src\\main\\resources\\CSVdb.csv";
    private static List<String[]> accountsCreator;
    private static List<String> accounts;

    public static void createCSV() {
        accountsCreator = new ArrayList<String[]>();
        for (int i = 0; i < 10; i++) {
            accountsCreator.add(new String[2]);
        }
        int i = 0;
        do {
            for (String[] accountData : accountsCreator) {
                accountData[0] = LOGIN_NAME + (i + 1) + DOMAIN;
                accountData[1] = PASSWORD + (i + 1);
                i++;
            }
        } while (i < 10);
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            fileWriter.append(FILE_HEADER);
            for (String[] accountData : accountsCreator) {
                fileWriter.append(NEW_LINE);
                fileWriter.append(accountData[0]);
                fileWriter.append(COMMA);
                fileWriter.append(accountData[1]);
                fileWriter.append(COMMA);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readCSV(int acc) {

        accounts = new ArrayList<String>();
        File file = new File(PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                accounts.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return accounts.get(acc);
    }

    public static String getLogin(int acc) {
        String accData[] = readCSV(acc).split(",");
        return accData[0];
    }

    public static String getPassword(int acc) {
        String accData[] = readCSV(acc).split(",");
        return accData[1];
    }
}
