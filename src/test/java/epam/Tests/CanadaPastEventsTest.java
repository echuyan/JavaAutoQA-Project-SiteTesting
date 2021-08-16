package epam.Tests;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;
import factory.Browsers;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;


public class CanadaPastEventsTest {
    private Logger logger = LogManager.getLogger(CanadaPastEventsTest.class);
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

  /**
   * Просмотр прошедших мероприятий в Канаде:
   * 1 Пользователь переходит на вкладку events
   * 2 Пользователь нажимает на Past Events
   * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
   * 4 На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.
   */
     @Test
    public void CanadaPastEventsTest() {

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


