package epam.Tests;

import factory.Browsers;
import factory.WebDriverFactory;
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


public class FutureEventsTest {
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);
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

    /*Просмотр предстоящих мероприятий:
     1 Пользователь переходит на вкладку events
     2 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events*/
    @Test
    public void futureEventsTest() {

        File propFile = new File("./src/main/resources/logopass.properties");
        Properties prop = new Properties();
        try {
        prop.load(new InputStreamReader(new FileInputStream(propFile),"UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //создаем экземпляр главной страницы сайта
        MainPage mainP = new MainPage(wd);
        //открываем форму логина
        mainP.openEvents(wd);

        //Создаем экземпляр страницы Events, проверяем ее открытие и сверяем количество событий
        EventsPage  eventsP = new EventsPage (wd);
        Assert.assertTrue(eventsP.isPageOpened());
        eventsP.checkEventsCount();

    }

   /* @Test
    public void secondTest () {
        File propFile = new File("./src/main/resources/logopass.properties");
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(new FileInputStream(propFile),"UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        wd.manage().deleteAllCookies();
        //создаем экземпляр главной страницы сайта
        MainPage mainP = new MainPage(wd);
        //открываем форму логина
        mainP.openLoginPage(wd);
        //Создаем экземпляр страницы ввода логина-пароля
         loginP = new (wd);

        Assert.assertEquals("ВОЙДИТЕ В СВОЙ АККАУНТ",loginP.isPageOpened());
        loginP.login(prop.getProperty("login"),prop.getProperty("password"));

        //находимся на главной странице после логина (персонализированная)
        OtusPersonalizedPage persP = new OtusPersonalizedPage(wd);
        Assert.assertTrue(persP.isPageOpened());
        persP.openLK();

        //Создаем эксземпляр страницы ЛК, переходим на страницу заполнения данных
        OtusLKPage lkPage = new OtusLKPage(wd);
        Assert.assertEquals("Мои курсы",lkPage.isPageOpened());
        lkPage.openContacts();

        //Создаем эксземпляр страницы Персональные данные, проверяем данные
        OtusPersonalData persDataPage = new OtusPersonalData(wd);
        Assert.assertTrue(persDataPage.isPageOpened());


        propFile = new File("./src/main/resources/personalData.properties");
        try {
            prop.load(new InputStreamReader(new FileInputStream(propFile),"UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        persDataPage.checkData(prop);

    }*/
    }


