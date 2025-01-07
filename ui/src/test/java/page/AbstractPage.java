package page;

import com.microsoft.playwright.Page;
import utils.BrowserManager;

public abstract class AbstractPage {

    // Instance of BrowserManager to manage browser interactions for the page
    private final BrowserManager browserManager;

    /**
     * Constructor for AbstractPage class, initializing the BrowserManager.
     * This constructor is used by subclasses to initialize the page with the provided BrowserManager.
     *
     * @param browserManager the BrowserManager instance used to interact with the browser
     */
    protected AbstractPage(BrowserManager browserManager) {
        // Initialize the browserManager field with the provided BrowserManager instance
        this.browserManager = browserManager;
    }

    /**
     * Gets the current Page object from the BrowserManager.
     * This method is used to get the current page being interacted with in the browser.
     *
     * @return the current Page object managed by the BrowserManager
     */
    public Page getPage() {
        return browserManager.getPage();
    }

}
