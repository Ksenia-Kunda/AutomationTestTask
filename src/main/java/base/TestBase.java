package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.System;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ksenia on 09.12.2016.
 */
public class TestBase {

    private static final String URL = "https://www.tut.by";
    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void close(){
        driver.close();
    }
}
