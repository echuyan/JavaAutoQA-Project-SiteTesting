package epam.Tests;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;
import epam.Pages.PastEventsPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static drivers.WebDriverInit.initDriver;


public class UpcomingEventsDatesValidationTest {
    private Logger logger = LogManager.getLogger(UpcomingEventsDatesValidationTest.class);
    public WebDriver wd;
    InputStream inputStream;

    @Before
    public void setup() {
    //инициализируем драйвер
        wd = initDriver() ;
     wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     wd.manage().window().maximize();
     logger.info("Драйвер поднят");
    }

    @After
    public void shutdown() {
        wd.close();
        logger.info("Драйвер опущен");
    }

  /**
   * Валидация дат предстоящих мероприятий:
   * 1 Пользователь переходит на вкладку events
   *2 Пользователь нажимает на Upcoming Events
   *3 На странице отображаются карточки предстоящих мероприятий.
   *4 Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)
   */
     @Test
    public void UpcomingEventsDatesValidationTest() {

        /**
         *  создаем экземпляр главной страницы сайта
         */

        MainPage mainP = new MainPage(wd);
        mainP.openEvents(wd);

        /**
        *Создаем экземпляр страницы Events, проверяем ее открытие
        */
         EventsPage  eventsP = new EventsPage (wd);
        Assert.assertTrue(eventsP.isPageOpened());



        /**
        * Проверяем даты будущих мероприятий
         */
        eventsP.checkDates();

    }
}


