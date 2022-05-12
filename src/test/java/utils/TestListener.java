package utils;

import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.util.UUID;

public class TestListener implements ITestListener {

    private void takeScreenshot() {
        try {
            TakesScreenshot takesScreenshot = ((TakesScreenshot) Driver.getDriver());
            File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            System.out.println("Taking screenshot...");
            String reportDirectory = "./target/surefire-reports";
            File destFile = new File( reportDirectory + "/screenshots/screenshot" + UUID.randomUUID() + ".png");
            FileUtils.copyFile(scrFile, destFile);
            Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

        } catch (Exception ex) {
            System.out.println("Exception while taking a screenshot: " + ex.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot();
    }
}