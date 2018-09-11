package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.SearchPage;

import java.util.List;


public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @Test
    public void searchResultsOnSearchPage() {
        String searchTerm = "Selenium";
        Assert.assertTrue(homePage.isLoaded(), "HomePage is not loaded");
        searchPage =  homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isLoaded(), "SearchPage is not loaded.");
        Assert.assertEquals(searchPage.getSearchResultsCount(), 10, "Not enough search results on search page");
        List<String> searchResults = searchPage.getSearchResultsList();
        for (String searchResult: searchResults){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "search term: "+searchTerm+" not found in: \n"+searchResult);
        }

        searchPage = searchPage.goToSecondPage();
        Assert.assertTrue(searchPage.isLoaded("Сторінка 2"));

        Assert.assertEquals(searchPage.getSearchResultsCount(), 10, "Not enough search results on search page");

        List<String> searchResultsSecondPage = searchPage.getSearchResultsList();
        for (String searchResult: searchResultsSecondPage){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "For second page search term: "+searchTerm+" not found in: \n"+searchResult);
        }
    }
}
