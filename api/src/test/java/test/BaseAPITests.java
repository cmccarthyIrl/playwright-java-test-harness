package test;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.RequestContext;


public class BaseAPITests extends RequestContext {

    private Playwright playwright;

    @BeforeSuite
    public void beforeAll() {
        playwright = Playwright.create();
    }

    @AfterSuite
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright(playwright);
    }

    public APIRequestContext weatherContext() {
        return getWeatherAPIContext(playwright);
    }
}