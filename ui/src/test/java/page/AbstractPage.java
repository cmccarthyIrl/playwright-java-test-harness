package page;

import com.microsoft.playwright.Page;
import utils.DriverManager;

public abstract class AbstractPage {

    private final Page page;

    protected AbstractPage(DriverManager driverManager) {
        page = driverManager.getBrowser().newPage();
    }

    public Page getPage() {
        return page;
    }

}