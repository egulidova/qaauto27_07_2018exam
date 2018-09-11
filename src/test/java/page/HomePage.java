package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='lst-ib']")
    private WebElement searchField;

    public HomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchField, 10);
    }

    /**
     * Class to check if required element on page is displayed.
     * @return true/false when reqiered element on page is/is not displayed.
     */
    @Override
    public boolean isLoaded() {
        return searchField.isDisplayed()
                && getCurrentPageTitle().equals("Google")
                && getCurrentPageUrl().equals("https://www.google.com.ua/");
    }

    public SearchPage search(String searchTerm){
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new SearchPage(browser);
    }
}
