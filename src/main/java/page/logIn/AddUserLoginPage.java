package page.logIn;

import base.PageBase;
import db.OptionDB;
import db.XMLdb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.MailPage;

import static helper.Locators.get;

/**
 * Created by Ksenia on 10.12.2016.
 */
public class AddUserLoginPage extends PageBase implements LogIn {

    private static final By USERNAME_INPUT = get("AddUserLoginPage.InputUserName");
    private static final By PASSWORD = get("AddUserLoginPage.InputPassword");
    private static final By ENTRANCE_BUTTON = get("AddUserLoginPage.EntranceButton");

    public AddUserLoginPage(WebDriver driver) {
        super(driver);
    }

    public void usernameInput(String login) {
        driver.findElement(USERNAME_INPUT).sendKeys(login);
    }

    public void passwordInput(String password) {
        driver.findElement(PASSWORD).sendKeys(password);
    }

    public MailPage entranceButtonClick() {
        driver.findElement(ENTRANCE_BUTTON).click();
        return new MailPage(driver);
    }

    public MailPage fillAllFields(int choiceDB, int accTo) {
        usernameInput(OptionDB.choiceDB(choiceDB, accTo, 1));
        passwordInput(OptionDB.choiceDB(choiceDB, accTo, 2));
        entranceButtonClick();
        return new MailPage(driver);
    }
}
