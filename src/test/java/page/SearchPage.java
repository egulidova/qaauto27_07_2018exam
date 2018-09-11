package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@id='resultStats']")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//a[@aria-label='Page 2']")
    private WebElement secondPageLink;

    public SearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchResultsTotal, 10);
    }

    /**
     * Class to check if required element on page is displayed.
     * @return true/false when reqiered element on page is/is not displayed.
     */
    @Override
    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
                && getCurrentPageTitle().contains("Пошук Google")
                && getCurrentPageUrl().contains("/search");
    }

    public boolean isLoaded(String searchTotalResultsText) {
        return searchResultsTotal.isDisplayed()
                && getSearchTotalResultsText().contains(searchTotalResultsText)
                && getCurrentPageTitle().contains("Пошук Google")
                && getCurrentPageUrl().contains("/search");
    }

    public List<String> getSearchResultsList(){
       List<String> searchResultsList = new ArrayList<>();
       for (WebElement searchResult: searchResults){
           searchResultsList.add(searchResult.getText());
       }
        return searchResultsList;
    }

    public int getSearchResultsCount() {
        return searchResults.size();
    }

    private String getSearchTotalResultsText(){
        return searchResultsTotal.getText();
    }

    public SearchPage goToSecondPage(){
        secondPageLink.click();
        return new SearchPage(browser);
    }
}
