package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class DriverManager {

    private Browser browser;

    private static final ThreadLocal<Browser> driverThreadLocal = new ThreadLocal<>();
//    private final Logger log = LoggerFactory.getLogger(DriverManager.class);


    public void createBrowser() throws IOException {


        if (getBrowser() == null) {

//            if (Arrays.toString(this.environment.getActiveProfiles()).contains("cloud-provider")) {
//                setRemoteDriver(new URL(applicationProperties.getGridUrl()));
//            } else {
                setLocalWebDriver();
//            }
//            WebDriverRunner.setWebDriver(getBrowser());
//            WebDriverRunner.getWebDriver().manage().deleteAllCookies();//useful for AJAX pages
        }
    }

    public void setLocalWebDriver() throws IOException {
        switch ("chrome") {
            case ("chrome") -> {
                browser = Playwright.create()
                        .chromium().launch(new BrowserType.LaunchOptions());

                driverThreadLocal.set(browser);
            }
            case ("firefox") -> {
                browser = Playwright
                        .create()
                        .firefox()
                        .launch(new BrowserType.LaunchOptions());
            }
//            default ->
//                    throw new NoSuchElementException("Failed to create an instance of WebDriver for: " + applicationProperties.getBrowser());
        }

        driverThreadLocal.set(browser);

//        driverWait.getDriverWaitThreadLocal()
//                .set(new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(Constants.timeoutShort), Duration.ofSeconds(Constants.pollingShort)));
    }

    private void setRemoteDriver(URL hubUrl) {
        switch ("chrome") {
            case ("chrome") -> {
                browser = Playwright
                        .create()
                        .chromium()
                        .launch(new BrowserType.LaunchOptions());

                driverThreadLocal.set(browser);
            }
            case ("firefox") -> {
                browser = Playwright
                        .create()
                        .firefox()
                        .launch(new BrowserType.LaunchOptions());
            }
//            default ->
//                    throw new NoSuchElementException("Failed to create an instance of RemoteWebDriver for: " + applicationProperties.getBrowser());
        }
//        driverWait.getDriverWaitThreadLocal()
//                .set(new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(Constants.timeoutShort), Duration.ofSeconds(Constants.pollingShort)));
    }

    public Browser getBrowser() {
        return driverThreadLocal.get();
    }
}
