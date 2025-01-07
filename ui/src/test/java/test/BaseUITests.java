package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import utils.BrowserManager;
import utils.ReadPropertyFile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import java.io.IOException;
import java.util.Properties;

public class BaseUITests {

    // Instance of BrowserManager to handle browser setup and management
    private final BrowserManager browserManager = new BrowserManager();

    /**
     * Set up the test suite by initializing the browser and loading the necessary configurations.
     * This method is executed once before the tests start running in the suite.
     *
     * @throws IOException if there is an issue reading the properties file.
     */
    @BeforeSuite
    public void setUp() throws IOException {
        // Load the configuration properties (like browser settings, headless mode, etc.)
        Properties properties = new ReadPropertyFile().loadProperties("./config.properties");

        // Create a new browser instance using the loaded properties
        browserManager.createBrowser(properties);
    }

    /**
     * Gets the instance of the BrowserManager.
     * This method allows tests to access the BrowserManager for browser interactions.
     *
     * @return the BrowserManager instance
     */
    public BrowserManager getDriverManager() {
        return browserManager;
    }

    /**
     * Clean up after the tests are complete by closing the page, browser, and Playwright instance.
     * This method is executed once after all the tests in the class have run.
     */
    @AfterClass
    public void tearDown() {
        // Close the currently opened page
        browserManager.getPage().close();

        // Close the browser
        browserManager.getBrowser().close();

        // Close the Playwright instance to free up resources
        browserManager.getPlaywright().close();
    }
}
