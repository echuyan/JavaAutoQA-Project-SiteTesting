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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PastEventsPage {
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

    //Location filter
    @FindBy(xpath = "//div[@class='evnt-filter-button evnt-button btn dropdown-toggle'][@id='filter_location']")
    private WebElement locationFilter;


    //Canada
    @FindBy(xpath = "//label[@data-value='Canada']")
    private WebElement canada;

    //счетчик прошедших мероприятий
    @FindBy(xpath = "//span[contains(.,'Past Events')]//following-sibling::span[@class='evnt-tab-counter evnt-label small white']")
    private WebElement pastEventsCounter;

    //results found
    @FindBy(xpath ="//p[contains(.,'results found for')]")
    private WebElement resultsFound;

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

    public void checkCanadaEvents() {

        locationFilter.click();
        wait.until(ExpectedConditions.elementToBeClickable(canada)).click();
        wait.until(ExpectedConditions.visibilityOf(resultsFound));
        List<WebElement> lst = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-cards-container']//div[@class='evnt-card-wrapper']"));
        String s = String.valueOf(lst.size());
        Assert.assertEquals("Количество мероприятий не соответствует счетчику!",pastEventsCounter.getText(),s);

        //получаем даты мероприятий
        List<WebElement> datesWe = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-cards-container']//div[@class='evnt-card-wrapper']//descendant::span[@class='date']"));
        int i = 0;
        //перегоняем в строковый массив
        String[] datesArray = new String[datesWe.size()];
        for (WebElement we : datesWe) {
            datesArray[i] = we.getText();
            logger.info(datesArray[i]);
            i++;
        }

        //вспомогательные переменные
        Date date1 = new Date();
        String[] dateS = new String[2];
        Date date2 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        LocalDate currentLocalDate = LocalDate.now();
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
        Date nowDate = Date.from(zonedDateTime.toInstant());

        //проверяем даты как для случая диапазона дат, так и для однодневного мероприятия
        for (i = 0; i < datesArray.length; i++) {
            //если используется диапазон дат
            if (datesArray[i].length()>11) {
                dateS = datesArray[i].split(" - ");
                try {

                    date2= dateFormat.parse(dateS[1]);
                    logger.info("Дата2 "+ date2);
                    Assert.assertTrue("Полученные даты мероприятия не являются прошлыми!",
                            nowDate.after(date2));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            //если используется дата
            else {
                try {
                    date1 = dateFormat.parse(datesArray[i]);
                    logger.info("Дата "+ date1);
                    Assert.assertTrue("Полученная дата мероприятия в будущем!",nowDate.after(date1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }


    }




}
