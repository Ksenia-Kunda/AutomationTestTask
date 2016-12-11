package page;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.logIn.MailEntrancePage;

import static helper.Locators.get;

/**
 * Created by Ksenia on 09.12.2016.
 */
public class HomePage extends PageBase {

    private static final By MAIL_BUTTON = get("HomePage.Mail");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public MailEntrancePage goToMailEntrancePage() {
        driver.findElement(MAIL_BUTTON).click();
        return new MailEntrancePage(driver);
    }
}
