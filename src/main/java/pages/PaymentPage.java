package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(css = "div.wrapper div dl dd")
    private WebElement itemPrice;

    @FindBy(css = "dl.order-summary-last-dl dd")
    private WebElement totalPrice;

    public String getItemPrice() {
        return itemPrice.getText().replaceAll("[^0-9.,]", "");
    }

    public String getTotalPrice() {
        return totalPrice.getText().replaceAll("[^0-9.,]", "");
    }

    public PaymentPage(WebDriver driver) {
        super(driver);
    }
}
