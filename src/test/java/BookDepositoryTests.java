import manager.PageFactoryManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.BasketPage;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.SearchPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookDepositoryTests {

    static final int DEFAULT_TIMEOUT = 30;
    PageFactoryManager pageFactoryManager;
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    SearchPage searchPage;
    BasketPage basketPage;
    PaymentPage paymentPage;

    static String HOME_URL = "https://www.bookdepository.com/";
    static String STANDARD_PRODUCT_1 = "s/9780131872486";

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        searchPage = pageFactoryManager.getSearchPage();
        productPage = pageFactoryManager.getProductPage();
        basketPage = pageFactoryManager.getBasketPage();
        paymentPage = pageFactoryManager.getPaymentPage();
    }

    @Test
    public void isLogoDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isLogoDisplayed());
    }

    @Test
    public void isSignInButtonDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isSignInButtonDisplayed());
    }

    @Test
    public void isItemCountInCartDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(searchPage.isItemCountInCartDisplayed());
    }

    @Test
    public void isCartButtonDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isCartButtonDisplayed());
    }

    @Test
    public void isSearchFieldDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isSearchFieldDisplayed());
    }

    @Test
    public void isNavigationMenuDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isNavigationMenuDisplayed());
    }

    @Test
    public void isBannerDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        assertTrue(homePage.isBannerDisplayed());
    }

    @Test
    public void isCountOfProductsInCartDisplayed() {
        BasePage.openPage(HOME_URL);
        homePage.searchForProduct("Thinking in Java");
        searchPage.clickAddToCartButton();
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getPopupCloseButtonElement());
        searchPage.clickClosePopupButton();
        assertEquals("1", searchPage.getItemCountInCart());
    }

    @Test
    public void isProductDescriptionDisplayed() {
        BasePage.openPage(HOME_URL + STANDARD_PRODUCT_1);
        productPage.isProductDescriptionDisplayed();
    }

    @Test
    public void verifyTotalPriceEqualsSubtotal() {
        BasePage.openPage(HOME_URL + STANDARD_PRODUCT_1);
        productPage.clickAddToCartButton();
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getCheckoutButtonElement());
        searchPage.clickCheckoutButton();
        basketPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, basketPage.getDefaultCheckoutButton());
        basketPage.clickDefaultCheckoutButton();
        assertEquals(paymentPage.getItemPrice(), paymentPage.getTotalPrice());
    }

    @After
    public void afterTest() {
        driver.close();
    }
}
