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
import org.assertj.core.api.SoftAssertions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

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
    static String STANDARD_PRODUCT_1_PRICE = "78,61";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        searchPage = pageFactoryManager.getSearchPage();
        productPage = pageFactoryManager.getProductPage();
        basketPage = pageFactoryManager.getBasketPage();
        paymentPage = pageFactoryManager.getPaymentPage();
    }

    @Test
    public void isMainElementsDisplayedOnHomePage() {
        BasePage.openPage(HOME_URL);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(homePage.isLogoDisplayed())
                    .withFailMessage("Logo not displayed").isTrue();
            softly.assertThat(homePage.isSignInButtonDisplayed())
                    .withFailMessage("Sign In Button not displayed").isTrue();
            softly.assertThat(searchPage.isItemCountInCartDisplayed())
                    .withFailMessage("Item Count In Cart not displayed").isTrue();
            softly.assertThat(homePage.isCartButtonDisplayed())
                    .withFailMessage("Cart Button not displayed").isTrue();
            softly.assertThat(homePage.isSearchFieldDisplayed())
                    .withFailMessage("Search Field not displayed").isTrue();
            softly.assertThat(homePage.isNavigationMenuDisplayed())
                    .withFailMessage("Navigation Menu not displayed").isTrue();
            softly.assertThat(homePage.isBannerDisplayed())
                    .withFailMessage("Banner not displayed").isTrue();
        });

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
    public void verifyTotalPriceEqualsSubtotal() {
        BasePage.openPage(HOME_URL + STANDARD_PRODUCT_1);
        productPage.isProductDescriptionDisplayed();
        productPage.clickAddToCartButton();
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getCheckoutButtonElement());
        searchPage.clickCheckoutButton();
        basketPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, basketPage.getDefaultCheckoutButton());
        basketPage.clickDefaultCheckoutButton();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(paymentPage.getItemPrice())
                    .withFailMessage("Item price isn't equal to expected")
                    .isEqualTo(STANDARD_PRODUCT_1_PRICE);
            softly.assertThat(paymentPage.getTotalPrice())
                    .withFailMessage("Total price isn't equal to expected")
                    .isEqualTo(STANDARD_PRODUCT_1_PRICE);
        });
    }

    @After
    public void afterTest() {
        driver.close();
    }
}
