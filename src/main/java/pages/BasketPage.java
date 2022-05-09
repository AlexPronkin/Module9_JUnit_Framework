package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {
    @FindBy(css = "a.checkout-btn.btn.original-bucket")
    private WebElement defaultCheckoutButton;

    public WebElement getDefaultCheckoutButton() {
        return defaultCheckoutButton;
    }

    public void clickDefaultCheckoutButton() {
        defaultCheckoutButton.click();
    }

    public BasketPage(WebDriver driver) {
        super(driver);
    }
}
