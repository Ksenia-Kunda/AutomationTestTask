package base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ksenia on 09.12.2016.
 */
public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
}
