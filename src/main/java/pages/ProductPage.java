package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = ".item-excerpt")
    public WebElement productDescription;

    @FindBy(css = ".btn-wrap *.add-to-basket")
    private WebElement addToCartButton;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public boolean isProductDescriptionDisplayed() {
        return productDescription.isDisplayed();
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }
}
