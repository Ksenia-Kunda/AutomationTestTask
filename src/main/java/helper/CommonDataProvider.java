package helper;

import db.XMLdb;
import org.testng.annotations.DataProvider;

import javax.mail.Session;

/**
 * Created by Ksenia on 13.12.2016.
 */
public class CommonDataProvider {

    @DataProvider
    public static Object[][] accountDataProvider() {
        return new Object[][]{
                new Object[]{XMLdb.getLogin(1), XMLdb.getPassword(1), XMLdb.getLogin(2), XMLdb.getPassword(2)},
                new Object[]{XMLdb.getLogin(3), XMLdb.getPassword(3), XMLdb.getLogin(4), XMLdb.getPassword(4)}
        };
    }
}

