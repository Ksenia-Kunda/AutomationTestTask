package page.logIn;

import org.openqa.selenium.By;
import page.MailPage;

import static helper.Locators.get;

/**
 * Created by Ksenia on 10.12.2016.
 */
public interface LogIn {

    public void usernameInput(String login);

    public void passwordInput(String password);

    public MailPage entranceButtonClick();
}
