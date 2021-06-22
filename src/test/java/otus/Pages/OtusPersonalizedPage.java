package otus.Pages;

import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.Tests.OtusTest;


public class OtusPersonalizedPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(OtusTest.class);
    Actions action;

    //автарка
      @FindBy(xpath = "//div[@class[contains(.,'avatar')]]")
      private WebElement avatar;
    //Ссылка на ЛК
      @FindBy(xpath = "//a[@title='Личный кабинет']")
      private WebElement lk;


    public OtusPersonalizedPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return wait.until(ExpectedConditions.visibilityOf(avatar)).isEnabled();

    }


    public void openLK()
    {
     action.moveToElement(avatar).build().perform();
     wait.until(ExpectedConditions.visibilityOf(lk)).click();
    }


}
