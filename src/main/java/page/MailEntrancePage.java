package page;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.MailPage;

import static helper.Locators.get;


/**
 * Created by Ksenia on 10.12.2016.
 */
public class MailEntrancePage extends PageBase{

    private static final By USERNAME_INPUT = get("MailEntrancePage.InputUserName");
    private static final By PASSWORD = get("MailEntrancePage.InputPassword");
    private static final By ENTRANCE_BUTTON = get("MailEntrancePage.EntranceButton");

    public MailEntrancePage(WebDriver driver) {
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

    public MailPage fillAllFields(String loginAccFrom, String passwordAccFrom) {
        usernameInput(loginAccFrom);
        passwordInput(passwordAccFrom);
        entranceButtonClick();
        return new MailPage(driver);
    }
}
