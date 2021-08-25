package epam.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


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


public class EventsPage extends BasePage {

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

    public EventsPage(WebDriver driver) {
        super(driver);
    }


    public boolean isPageOpened() {
        return wait.until(ExpectedConditions.visibilityOf(upcomingEvents)).isDisplayed();
    }

    /**
     * Проверка количества будущих мероприятий
     */
    public void checkEventsCount() {
        List<WebElement> lst = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-cards-container']//div[@class='evnt-card-wrapper']"));
        String s = String.valueOf(lst.size());
        Assert.assertEquals(eventsCounter.getText(), s);

    }

    /**
     * Переключение на прошедшие мероприятия
     */
    public void openPastEvents() {
        pastEvents.click();
    }

    /**
     * Проверка дат будущих мероприятий
     */
    public void checkDates() {

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
            if (datesArray[i].length() > 11) {
                dateS = datesArray[i].split(" - ");
                try {
                    date1 = dateFormat.parse(dateS[0] + " " + Year.now().toString());
                    logger.info("Дата1 " + date1);
                    date2 = dateFormat.parse(dateS[1]);
                    logger.info("Дата2 " + date2);
                    Assert.assertTrue("Полученные даты мероприятия не являются будущими!",
                            (nowDate.before(date1) || (nowDate.after(date1) && nowDate.before(date2))));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            //если используется дата
            else {
                try {
                    date1 = dateFormat.parse(datesArray[i]);
                    logger.info("Дата " + date1);
                    Assert.assertTrue("Полученная дата мероприятия в прошлом!", nowDate.before(date1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
