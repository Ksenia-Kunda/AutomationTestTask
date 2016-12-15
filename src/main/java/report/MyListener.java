package report;

import helper.DateManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Ksenia on 12.12.2016.
 */
public class MyListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getStatus());
    }

    public void onTestFailure(ITestResult iTestResult) {
        MyReporter.createScreenshot(iTestResult.getName());
        System.out.println(iTestResult.getStatus());
        System.out.println(iTestResult.getThrowable().getCause());

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

}
