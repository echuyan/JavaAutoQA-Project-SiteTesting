package epam.Pages;

import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import epam.Tests.FutureEventsTest;


public class OtusLKPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);
    Actions action;

    //Ссылка на раздел О себе
      @FindBy(xpath = "//a[@title='О себе']")
      private WebElement aboutMe;


    public OtusLKPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public String isPageOpened(){
       return driver.getTitle();
      }


    public void openContacts()
    {
       wait.until(ExpectedConditions.visibilityOf(aboutMe)).click();
    }


}
