package com.cmccarthyirl.ui.test;

import com.cmccarthyirl.common.ExtentReporter;
import com.cmccarthyirl.common.ExtentTests;
import com.cmccarthyirl.common.MDCModel;
import com.cmccarthyirl.common.XrayTestListener;
import com.cmccarthyirl.ui.utils.PlaywrightManager;
import com.cmccarthyirl.ui.utils.ReadPropertyFile;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

@Listeners({XrayTestListener.class})
public class BaseUITests {

    private final MDCModel mdcModel = new MDCModel();
    // Instance of BrowserManager to handle browser setup and management
    private final PlaywrightManager playwrightManager = new PlaywrightManager();

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
        playwrightManager.createBrowser(properties);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context, ITestResult iTestResult) {
        MDC.put("logFileName", "UI" + "-" + method.getName());
        mdcModel.setName("UI" + "-" + method.getName());

        if (ExtentTests.getTest() == null) {
            ExtentTests.createMethod(iTestResult, false);
        } else if (!ExtentTests.getTest().getModel().getName().equals(method.getName())) {
            ExtentTests.createMethod(iTestResult, false);
        }
    }

    /**
     * Gets the instance of the BrowserManager.
     * This method allows tests to access the BrowserManager for browser interactions.
     *
     * @return the BrowserManager instance
     */
    public PlaywrightManager getDriverManager() {
        return playwrightManager;
    }

    /**
     * Clean up after the tests are complete by closing the page, browser, and Playwright instance.
     * This method is executed once after all the tests in the class have run.
     */
    @AfterClass
    public void tearDown() {
        // Close the currently opened page
        playwrightManager.getPage().close();

        // Close the browser
        playwrightManager.getBrowser().close();

        // Close the Playwright instance to free up resources
        playwrightManager.getPlaywright().close();

        ExtentReporter.extent.flush();

    }
}
