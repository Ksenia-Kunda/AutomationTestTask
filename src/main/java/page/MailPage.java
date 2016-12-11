package page;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.logIn.AddUserLoginPage;
import send.SendEMail;

import java.util.List;

import static helper.Locators.get;

/**
 * Created by Ksenia on 10.12.2016.
 */
public class MailPage extends PageBase {

    private static final By SUBJECT_FIELD = get("MailPage.Subject");
    private static final By SENT_FOLDER_BUTTON = get("MailPage.Sent");
    private static final By USER_ICON = get("MailPage.UserIcon");
    private static final By ADD_USER = get("MailPage.AddUser");
    private static final By INBOX_FOLDER_BUTTON = get("MailPage.Inbox");
    private Boolean compareResult = false;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public void sentFolderClick() {
        driver.findElement(SENT_FOLDER_BUTTON).click();
    }

    public void inboxFolderClick() {
        driver.findElement(INBOX_FOLDER_BUTTON).click();
    }

    public Boolean findSubject() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elements = driver.findElements(SUBJECT_FIELD);

        for (int i = 0; i < elements.size(); i++) {
            String subject = driver.findElements(SUBJECT_FIELD).get(i).getText();
            if (subject.equals(SendEMail.getSubject())) {
                compareResult = true;
                break;
            }
        }
        return compareResult;
    }

    public AddUserLoginPage addUser() {
        driver.findElement(USER_ICON).click();
        driver.switchTo().activeElement();
        driver.findElement(ADD_USER).click();
        return new AddUserLoginPage(driver);
    }
}
