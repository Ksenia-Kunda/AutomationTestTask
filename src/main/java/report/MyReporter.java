package report;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ksenia on 11.12.2016.
 */
public class MyReporter {
    private static String result = "";


    public static String createScreenshot(String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) TestBase.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String pathName = System.getProperty("user.dir") + "/ReportResult/" + screenshotName + ".png";
        File destination = new File(pathName);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathName;
    }


    public static String testResult(Boolean bool) {
        if (bool == true) {
            result = "Test passed";
        } else {
            result = "Test failed";
        }
        return result;
    }
}


