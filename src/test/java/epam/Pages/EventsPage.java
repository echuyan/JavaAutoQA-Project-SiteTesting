package epam.Pages;

import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import epam.Tests.FutureEventsTest;

import java.util.List;


public class EventsPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);

    //лоадер
      @FindBy(xpath = "//div[@class='evnt-global-loader']")
      private WebElement loader;
    //upcoming events
      @FindBy(xpath = "//span[contains(.,'Upcoming events')]")
      private WebElement upcomingEvents;
   // past events
      @FindBy(xpath = "//span[contains(.,'Past Events')]")
      private WebElement pastEvents;
    //счетчик будущих мероприятий
      @FindBy(xpath = "//span[contains(.,'Upcoming events')]//following-sibling::span[@class='evnt-tab-counter evnt-label small white']")
      private WebElement eventsCounter;
    //Карточки мероприятий
      @FindBy(xpath = "//div[@class='evnt-cards-container']//div[@class='evnt-card-wrapper']")
      private WebElement events;

    public EventsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        PageFactory.initElements(driver, this);

    }

    public boolean isPageOpened(){
        return wait.until(ExpectedConditions.visibilityOf(upcomingEvents)).isDisplayed();
    }


   public void checkEventsCount()
    {
        List<WebElement> lst = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-cards-container']//div[@class='evnt-card-wrapper']"));
        String s = String.valueOf(lst.size());
        Assert.assertEquals(eventsCounter.getText(),s);

   }


   public void openPastEvents()
   {       pastEvents.click();   }


}
