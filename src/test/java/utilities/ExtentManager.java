package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String fileName = getReportName();
        String directory = System.getProperty("user.dir") + "/reports/";
        new File(directory).mkdirs();
        String path = directory + fileName;
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);//Default Theme of Report

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // General information releated to application
        extent.setSystemInfo("Application Name", "Mortgage Calculator");
        extent.setSystemInfo("Browser", "Chrome");

        return extent;
    }

    public static String getReportName() {
        Date d = new Date();
        String fileName = "AutomationReport" + "_" + d.toString().replace(":","_").replace(" ", "_") + ".html";
        return fileName;
    }
}
