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

    private final MDCModel mdcModel = new MDCModel();
    private static String logPrefix;


    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private APIRequestContext request;

    /**
     * The setup method that runs before all tests in the suite.
     * It initializes the Playwright instance, which is necessary for interacting with the browser or APIs.
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeAll() {
        // Playwright instance used for managing browser and API interactions
        playwrightThreadLocal.set(Playwright.create());
    }

    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    /**
     * The teardown method that runs after all tests in the suite.
     * It disposes of the API request context and closes the Playwright instance.
     */
    @AfterSuite(alwaysRun = true)
    void afterAll() {
        try {
            disposeAPIRequestContext();
        } finally {
            closePlaywright(getPlaywright());
        }
        ExtentReporter.extent.flush();
    }

    /**
     * This method disposes of the current APIRequestContext, if it exists.
     * It ensures that the resources associated with the context are released.
     */
    protected void disposeAPIRequestContext() {
        if (request != null) {
            // Dispose of the request context to free resources
            request.dispose();
            request = null;
        }
    }

    /**
     * This method closes the Playwright instance, if it exists.
     * It ensures that any resources associated with Playwright are properly cleaned up.
     *
     * @param playwright The Playwright instance to close.
     */
    protected void closePlaywright(Playwright playwright) {
        if (playwrightThreadLocal.get() != null) {
            playwrightThreadLocal.get().close();
            playwrightThreadLocal.remove();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context, ITestResult iTestResult) {
        String methodName = method.getName();
        String logFileName = "API-" + methodName;

        // Set log file name in MDC
        MDC.put("logFileName", logFileName);

        // Update model name
        mdcModel.setName(logFileName);

        // Check if a test method needs to be created or updated
        boolean isTestMethodValid = ExtentTests.getTest() != null
                && ExtentTests.getTest().getModel().getName().equals(methodName);

        // Create or update the test if necessary
        if (ExtentTests.getTest() == null || !isTestMethodValid) {
            ExtentTests.createOrUpdateTestMethod(iTestResult, false);
        }

    }

    /**
     * A helper method to return the weather-related API request context.
     * It utilizes the `getWeatherAPIContext` method (inherited from `RequestContext`) to get the specific API context.
     *
     * @return the API request context for the weather API
     */
    public APIRequestContext weatherContext() {
        return getWeatherAPIContext(getPlaywright());
    }
}

