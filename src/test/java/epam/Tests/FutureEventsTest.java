package epam.Tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import epam.Pages.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;


import java.util.concurrent.TimeUnit;

import static drivers.WebDriverInit.initDriver;


public class FutureEventsTest {
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);
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
     * Просмотр предстоящих мероприятий:
     *1 Пользователь переходит на вкладку events
     *2 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events
    */
    @Test
    public void futureEventsTest() {


        //создаем экземпляр главной страницы сайта
        MainPage mainP = new MainPage(wd);
        mainP.openEvents(wd);

        //Создаем экземпляр страницы Events, проверяем ее открытие и сверяем количество событий
        EventsPage  eventsP = new EventsPage (wd);
        Assert.assertTrue(eventsP.isPageOpened());
        eventsP.checkEventsCount();

    }

}


