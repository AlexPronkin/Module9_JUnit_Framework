package manager;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.SearchPage;
import pages.BasketPage;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public SearchPage getSearchPage() { return new SearchPage(driver); }

    public ProductPage getProductPage() { return new ProductPage(driver); }

    public BasketPage getBasketPage() { return new BasketPage(driver); }

    public PaymentPage getPaymentPage() { return new PaymentPage(driver); }

}
