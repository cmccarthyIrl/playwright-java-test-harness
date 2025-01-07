package test;

import org.testng.annotations.Test;
import page.SearchPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UITest extends BaseUITests {

   private SearchPage searchPage;

    @Test
    public void searchForExactTitle() {
        searchPage = new SearchPage(getDriverManager());
        String title = "Agile Testing";
        searchPage.getPage().navigate("https://automationbookstore.dev/");
        searchPage.search(title);
        assertEquals(searchPage.getNumberOfVisibleBooks(), 1, "Number of visible books");
        assertTrue(searchPage.getVisibleBooks().contains(title), "Title of visible book");
    }

    @Test
    public void searchForPartialTitle() {
        searchPage = new SearchPage(getDriverManager());
        searchPage.getPage().navigate("https://automationbookstore.dev/");
        searchPage.search("Test");

        List<String> expectedBooks = List.of(
                "Test Automation in the Real World",
                "Experiences of Test Automation",
                "Agile Testing",
                "How Google Tests Software",
                "Java For Testers"
        );

        assertEquals(searchPage.getNumberOfVisibleBooks(), expectedBooks.size(), "Number of visible books");
        assertEquals(searchPage.getVisibleBooks(), expectedBooks, "Titles of visible books");
    }
}