package test;

import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.RequestContext;

import com.microsoft.playwright.*;

public class BaseAPITests extends RequestContext {

    // Playwright instance used for managing browser and API interactions
    private Playwright playwright;

    /**
     * The setup method that runs before all tests in the suite.
     * It initializes the Playwright instance, which is necessary for interacting with the browser or APIs.
     */
    @BeforeSuite
    public void beforeAll() {
        // Create a new Playwright instance
        playwright = Playwright.create();
    }

    /**
     * The teardown method that runs after all tests in the suite.
     * It disposes of the API request context and closes the Playwright instance.
     */
    @AfterSuite
    void afterAll() {
        // Dispose of any API request context resources
        disposeAPIRequestContext();
        // Close the Playwright instance to release resources
        closePlaywright(playwright);
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

