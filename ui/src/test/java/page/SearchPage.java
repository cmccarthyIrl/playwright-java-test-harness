package page;

import com.microsoft.playwright.Page;
import utils.BrowserManager;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.options.WaitForSelectorState.ATTACHED;
import static com.microsoft.playwright.options.WaitForSelectorState.DETACHED;


public class SearchPage extends AbstractPage {

    private final Page page;

    private final String locator_searchBar = "#searchBar";
    private final String locator_hiddenBooks = "li.ui-screen-hidden";
    private String locator_visibleBooks = "li:not(.ui-screen-hidden)";
    private String locator_visibleBookTitles = "li:not(.ui-screen-hidden) h2";


    public SearchPage(BrowserManager browserManager) {
        super(browserManager);
        page = getPage();
    }

    public void search(String query) {
        clearSearchBar();
        page.fill(locator_searchBar, query);
        var expectedState = new Page.WaitForSelectorOptions().setState(ATTACHED);
        page.waitForSelector(locator_hiddenBooks, expectedState);
    }

    public void clearSearchBar() {
        page.fill(locator_searchBar, "");
        var expectedState = new Page.WaitForSelectorOptions().setState(DETACHED);
        page.waitForSelector(locator_hiddenBooks, expectedState);
    }

    public int getNumberOfVisibleBooks() {
        return page.querySelectorAll(locator_visibleBooks).size();
    }

    public List<String> getVisibleBooks() {
        return page.querySelectorAll(locator_visibleBookTitles)
                .stream()
                .map(e -> e.innerText())
                .collect(Collectors.toList());
    }
}