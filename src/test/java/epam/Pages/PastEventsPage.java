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


public class PastEventsPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);

    //лоадер
      @FindBy(xpath = "//div[@class='evnt-global-loader']")
      private WebElement loader;

    //registration closed annotation
      @FindBy(xpath = "//span[contains(.,'Registration closed')]")
      private WebElement regClosed;

   // language of event
      @FindBy(xpath = "//div[@class='evnt-card-wrapper']//descendant::p[@class='language']//child::span")
      private WebElement language;

    //event name
      @FindBy(xpath = "//div[@class='evnt-card-wrapper']//descendant::div[@class='evnt-event-name']//descendant::span")
      private WebElement eventName;

    //event date
      @FindBy(xpath = "//div[@class='evnt-card-wrapper']//descendant::span[@class='date']")
      private WebElement eventDate;

    //speaker
    @FindBy(xpath = "//div[@class='evnt-card-wrapper']//descendant::div[@class='evnt-speaker']")
    private WebElement speaker;



    public PastEventsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        PageFactory.initElements(driver, this);

    }

    public boolean isPageOpened(){
        return wait.until(ExpectedConditions.visibilityOf(regClosed)).isDisplayed();
    }


    /**
     * Проверка Карточек мероприятий
     */
    public void checkCards()
    {
        List<WebElement> lstLang = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-card-wrapper']//descendant::p[@class='language']//child::span"));
        logger.info("Найдено на странице элементов с указанием языка мероприятия: "+lstLang.size());

        List<WebElement> lstName = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-card-wrapper']//descendant::div[@class='evnt-event-name']//descendant::span"));
        logger.info("Найдено на странице элементов с указанием названия мероприятия: "+lstName.size());

        List<WebElement> lstDate = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-card-wrapper']//descendant::span[@class='date']"));
        logger.info("Найдено на странице элементов с указанием даты мероприятия: "+lstDate.size());

        List<WebElement> lstStatus = driver.findElements(By.ByXPath.xpath("//span[contains(.,'Registration closed')]"));
        logger.info("Найдено на странице элементов с указанием статуса мероприятия: "+lstStatus.size());

        List<WebElement> lstSpeakers = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-card-wrapper']//descendant::div[@class='evnt-speaker']"));
        logger.info("Найдено на странице Спикеров мероприятия: "+lstSpeakers.size());

        /**
         * Проверка наличия языка мероприятия в каждом мероприятии
         */
        for(WebElement we : lstLang){
            Assert.assertNotEquals(null,we.getText());
        }
        /**
         * Проверка наличия названия мероприятия в каждом мероприятии
         */
        for(WebElement we : lstName){
            Assert.assertNotEquals(null,we.getText());
        }
        /**
         * Проверка наличия дат мероприятия в каждом мероприятии
         */
        for(WebElement we : lstDate){
            Assert.assertNotEquals(null,we.getText());
        }
        /**
         * Проверка наличия статуса регистрации на мероприятие в каждом мероприятии
         */
        for(WebElement we : lstStatus){
            Assert.assertNotEquals(null,we.getText());
        }
        /**
         * Проверка наличия спикеров в каждом мероприятии
         */
        logger.info("Список спикеров:\n");
        for(WebElement we : lstSpeakers){
            logger.info(we.getAttribute("data-name"));
            Assert.assertNotEquals(null,we.getAttribute("data-name"));
        }


    }



}
