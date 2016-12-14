package page;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Ksenia on 09.12.2016.
 */
public class HomePage extends PageBase {

    private static final By MAIL_BUTTON = get("HomePage.Mail");
    private static final By USER_NAME_BUTTON = get("HomePage.UserName");
    private static final By LOG_OUT_BUTTON = get("HomePage.Logout");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public MailEntrancePage goToMailEntrancePage() {
        driver.findElement(MAIL_BUTTON).click();
        return new MailEntrancePage(driver);
    }

    public void logout(){
        driver.findElement(USER_NAME_BUTTON).click();
        driver.switchTo().activeElement();
        driver.findElement(LOG_OUT_BUTTON).click();
        driver.manage().deleteAllCookies();
    }
}
