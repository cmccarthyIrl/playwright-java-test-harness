package page;

import com.microsoft.playwright.Page;
import utils.BrowserManager;

public abstract class AbstractPage {

    private final BrowserManager browserManager;

    protected AbstractPage(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public Page getPage() {
        return browserManager.getPage();
    }

}