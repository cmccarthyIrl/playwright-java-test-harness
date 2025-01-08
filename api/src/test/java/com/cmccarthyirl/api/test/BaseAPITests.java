package com.cmccarthyirl.api.test;

import com.cmccarthyirl.api.utils.RequestContext;
import com.cmccarthyirl.common.ExtentReporter;
import com.cmccarthyirl.common.ExtentTests;
import com.cmccarthyirl.common.MDCModel;
import com.cmccarthyirl.common.XrayTestListener;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners({XrayTestListener.class})
public class BaseAPITests extends RequestContext {

    // Playwright instance used for managing browser and API interactions
    private Playwright playwright;
    private final MDCModel mdcModel = new MDCModel();
    private static String logPrefix;


    /**
     * The setup method that runs before all tests in the suite.
     * It initializes the Playwright instance, which is necessary for interacting with the browser or APIs.
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeAll() {
        // Create a new Playwright instance
        playwright = Playwright.create();
    }

    /**
     * The teardown method that runs after all tests in the suite.
     * It disposes of the API request context and closes the Playwright instance.
     */
    @AfterSuite(alwaysRun = true)
    void afterAll() {
        // Dispose of any API request context resources
        disposeAPIRequestContext();
        // Close the Playwright instance to release resources
        closePlaywright(playwright);

        ExtentReporter.extent.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context, ITestResult iTestResult) {
        MDC.put("logFileName", "API" + "-" + method.getName());
        mdcModel.setName("API" + "-" + method.getName());

        if (ExtentTests.getTest() == null) {
            ExtentTests.createMethod(iTestResult, false);
        } else if (!ExtentTests.getTest().getModel().getName().equals(method.getName())) {
            ExtentTests.createMethod(iTestResult, false);
        }
    }

    /**
     * A helper method to return the weather-related API request context.
     * It utilizes the `getWeatherAPIContext` method (inherited from `RequestContext`) to get the specific API context.
     *
     * @return the API request context for the weather API
     */
    public APIRequestContext weatherContext() {
        return getWeatherAPIContext(playwright);
    }
}

