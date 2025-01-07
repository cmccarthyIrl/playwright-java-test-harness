package utils;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.Properties;

public class BrowserManager {

    private Browser browser;

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    private final Logger log = LoggerFactory.getLogger(BrowserManager.class);

    public void createBrowser(Properties properties) {


        if (getBrowser() == null) {
            playwrightThreadLocal.set(Playwright.create());


//            if (Arrays.toString(this.environment.getActiveProfiles()).contains("cloud-provider")) {
//                setRemoteDriver(new URL(applicationProperties.getGridUrl()));
//            } else {

            setLocalWebDriver( properties);
//            }
        }
    }

    public void setLocalWebDriver(Properties properties) {

        String binary = properties.getProperty("binary");
        String browserName = properties.getProperty("browser");
        String headless = properties.getProperty("headless");

        switch (browserName) {
            case ("chrome") -> {
                List<String> chromeOptions = List.of(
                        "--start-maximized",
                        "--disable-gpu",
                        "--no-sandbox"
                );

                browser = playwrightThreadLocal.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                        .setSlowMo(50)                    // Slow down interactions by 50 ms
                        .setArgs(chromeOptions));
            }
            case ("firefox") -> {
                browser = playwrightThreadLocal.get()
                        .firefox()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));
            }
            default -> throw new RuntimeException("Failed to create an instance of WebDriver for: " + binary);
        }

        BrowserContext context = browser.newContext();

        Page page = context.newPage();
        pageThreadLocal.set(page);
        contextThreadLocal.set(context);
        browserThreadLocal.set(browser);
    }

    private void setRemoteDriver(URL hubUrl, Properties properties) {

        String binary = properties.getProperty("binary");
        String browserName = properties.getProperty("browser");
        String headless = properties.getProperty("headless");

        switch (browserName) {
            case ("chrome") -> {
                browser = playwrightThreadLocal.get()
                        .chromium()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));


            }
            case ("firefox") -> {
                browser = playwrightThreadLocal.get()
                        .firefox()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));
            }
            default ->
                    throw new RuntimeException("Failed to create an instance of RemoteWebDriver for: " + binary);
        }

        BrowserContext context = browser.newContext();

        Page page = context.newPage();
        pageThreadLocal.set(page);
        contextThreadLocal.set(context);
        browserThreadLocal.set(browser);
    }

    /**
     * Getting the Playwright reference
     */
    public Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    /**
     * Getting the Browser object
     */
    public Browser getBrowser() {
        return browserThreadLocal.get();
    }

    /**
     * Getting the Browser Context object
     */
    public BrowserContext getBrowserContext() {
        return contextThreadLocal.get();
    }

    /**
     * Getting the Page object
     */
    public Page getPage() {
        return pageThreadLocal.get();
    }
}
