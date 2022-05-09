package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "img[alt='Bookdepository.com']")
    private static WebElement logo;

    @FindBy(css = "[class='mob-nav-account'] a[href='/account/login/to/account']")
    private static WebElement signInButton;

    @FindBy(css = "input[name='searchTerm']")
    private static WebElement searchField;

    @FindBy(css = "[class*='mob-nav-shop dropdown']")
    private static WebElement navigationMenu;

    @FindBy(css = "div[class*='lock-wrap promo']")
    private static WebElement banner;

    @FindBy(css = "[class='right-section'] [href='/basket']")
    private static WebElement cartButton;

    public void searchForProduct(final String productName) {
        searchField.sendKeys(productName);
        searchField.submit();
    }

    public boolean isLogoDisplayed(){return logo.isDisplayed();}

    public boolean isSignInButtonDisplayed(){return signInButton.isDisplayed();}

    public boolean isSearchFieldDisplayed(){return searchField.isDisplayed();}

    public boolean isNavigationMenuDisplayed(){return navigationMenu.isDisplayed();}

    public boolean isBannerDisplayed(){return banner.isDisplayed();}

    public boolean isCartButtonDisplayed(){return cartButton.isDisplayed();}

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
