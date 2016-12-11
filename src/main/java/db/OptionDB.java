package db;

/**
 * Created by Ksenia on 11.12.2016.
 */
public class OptionDB {

    private static String option = "";

    public static String choiceDB(int choiceDB, int choiceAccount, int choiceField) {
        switch (choiceDB) {
            case 1:
                switch (choiceField) {
                    case 1:
                        option = XMLdb.getLogin(choiceAccount);
                        break;
                    case 2:
                        option = XMLdb.getPassword(choiceAccount);
                        break;
                }
                break;
            case 2:
                switch (choiceField) {
                    case 1:
                        option = CSVdb.getLogin(choiceAccount);
                        break;
                    case 2:
                        option = CSVdb.getPassword(choiceAccount);
                        break;
                }
                break;
            case 3:
                switch (choiceField) {
                    case 1:
                        option = MySQLdb.getLogin(choiceAccount);
                        break;
                    case 2:
                        option = MySQLdb.getPassword(choiceAccount);
                        break;
                }
                break;
        } return option;
    }
}
