package GUI_Functions.ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageOR {

    @FindBy(css= "div[class$='greenLogo']")
    public static WebElement homePageLogo;
}
