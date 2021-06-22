package otus.Pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.Tests.OtusTest;


public class OtusLoginPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(OtusTest.class);

    //поле логина
      @FindBy(xpath = "//div[@class='new-log-reg__body']//input[@placeholder = 'Электронная почта']")
      private WebElement loginName;
    //поле с заголовком
      @FindBy(xpath = "//span[contains(.,'Войдите в свой аккаунт')]")
      private WebElement heading;
   // поле пароля
      @FindBy(xpath = "//div[@class='new-log-reg__body']//input[@type = 'password']")
      private WebElement password;
    // кнопка Войти
    @FindBy(xpath = "//button[contains(.,'Войти')]")
    private WebElement loginButton;


    public OtusLoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        PageFactory.initElements(driver, this);

    }

    public String isPageOpened(){
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }


    public void login(String login,String password)
    {
        loginName.sendKeys(login, Keys.ENTER);
        this.password.sendKeys(password,Keys.ENTER);
        loginButton.click();
    }


}
