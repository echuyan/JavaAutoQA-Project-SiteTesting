package otus.Pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;


@DefaultUrl("https://otus.ru/")
public class OtusMainPage extends PageObject {
    private WebDriver driver;
    private static String PAGE_URL = "https://otus.ru/";

    //Кнопка Войти и регистрация
    @FindBy(xpath="//button[contains(.,'и регистрация')]")
    private WebElement loginAndRegistrationButton;


    //конструктор
    public OtusMainPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    //нажатие кнопки для появления формы логина
    public void openLoginPage(WebDriver driver)
    {
      loginAndRegistrationButton.click();
     }


}
