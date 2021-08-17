package epam.Pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@DefaultUrl("https://events.epam.com/")
public class MainPage extends PageObject {
    private WebDriver driver;
    private static String PAGE_URL = "https://events.epam.com/";

    //Вкладка Events
    @FindBy(xpath="//a[@class='nav-link'][contains(.,'Events')]")
    private WebElement eventsTab;

    //Вкладка Video
       @FindBy(xpath="//a[@class='nav-link'][contains(.,'Video')]")
       private WebElement videoTab;

       // кнопка акцепта куки
       @FindBy(xpath="//div[@id='onetrust-banner-sdk']//descendant::button[@id='onetrust-accept-btn-handler']")
       private WebElement acceptCookies;



    //конструктор
    public MainPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        acceptCookies.click();
    }

    //открытие вкладки Events
    public void openEvents(WebDriver driver)
    {
      eventsTab.click();
     }


    //открытие вкладки Video
    public void openVideo(WebDriver driver)
    {
        videoTab.click();
    }

}
