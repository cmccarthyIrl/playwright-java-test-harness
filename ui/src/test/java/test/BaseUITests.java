package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import utils.BrowserManager;
import utils.ReadPropertyFile;

import java.io.IOException;
import java.util.Properties;

public class BaseUITests {

    private final BrowserManager browserManager = new BrowserManager();

    @BeforeSuite
    public void setUp() throws IOException {
        Properties properties = new ReadPropertyFile().loadProperties("./config.properties");
        browserManager.createBrowser(properties);
    }

    public BrowserManager getDriverManager() {
        return browserManager;
    }

    @AfterClass
    public void tearDown() {
        browserManager.getPage().close();
        browserManager.getBrowser().close();
        browserManager.getPlaywright().close();
    }
}