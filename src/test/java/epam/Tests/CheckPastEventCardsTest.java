package epam.Tests;

import epam.Pages.PastEventsPage;
import factory.Browsers;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import epam.Pages.EventsPage;
import epam.Pages.MainPage;

import java.io.*;
import java.util.concurrent.TimeUnit;


public class CheckPastEventCardsTest {
    private Logger logger = LogManager.getLogger(CheckPastEventCardsTest.class);
    public WebDriver wd;
    InputStream inputStream;

    @Before
    public void setup() {
    //инициализируем драйвер
     wd = WebDriverFactory.create(Browsers.CHROME);
     wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     wd.manage().window().maximize();
     logger.info("Драйвер поднят");
    }

    @After
    public void shutdown() {
        wd.close();
        logger.info("Драйвер опущен");
    }

    /*Просмотр карточек мероприятий:
    1 Пользователь переходит на вкладку events
    2 Пользователь нажимает на Past Events
    3 На странице отображаются карточки прошедших мероприятий.
    4 В карточке указана информация о мероприятии:
        язык
        название мероприятия
        дата мероприятия
        информация о регистрации
        список спикеров // Минимально достаточное - проверить одну карточку. В идеале все что отображаются.*/
    @Test
    public void checkEventCardTest() {

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
        *Создаем экземпляр страницы Past Events, проверяем ее открытие
        */
         eventsP.openPastEvents();
        PastEventsPage pastEvents = new PastEventsPage(wd);
        Assert.assertTrue(pastEvents.isPageOpened());

        /**
        * Проверяем карточки прошедших мероприятий
         */
        pastEvents.checkCards();

    }
}


