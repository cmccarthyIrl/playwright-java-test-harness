package test;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import utils.DriverManager;

import java.io.IOException;

public class BaseTests {

    private Browser browser;
    private final DriverManager driverManager = new DriverManager();

    @BeforeSuite
    public void setUp() throws IOException {
        driverManager.createBrowser();
        browser = driverManager.getBrowser();
    }

    public DriverManager getDriverManager(){
        return driverManager;
    }

    @AfterClass
    public void tearDown() {
        browser.close();
    }
}