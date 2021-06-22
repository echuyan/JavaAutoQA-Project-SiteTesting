package otus.Tests;

import factory.Browsers;
import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.Pages.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;


import java.util.concurrent.TimeUnit;


public class OtusTest {
    private Logger logger = LogManager.getLogger(OtusTest.class);
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

    @Test
    public void firstTest() {

        File propFile = new File("./src/main/resources/logopass.properties");
        Properties prop = new Properties();
        try {
        prop.load(new InputStreamReader(new FileInputStream(propFile),"UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //создаем экземпляр главной страницы сайта
        OtusMainPage mainP = new OtusMainPage(wd);
        //открываем форму логина
        mainP.openLoginPage(wd);

        //Создаем экземпляр страницы ввода логина-пароля
        OtusLoginPage loginP = new OtusLoginPage(wd);

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

        //Создаем эксземпляр страницы Персональные данные, заполняем и сохраняем данные
        OtusPersonalData persDataPage = new OtusPersonalData(wd);
        Assert.assertTrue(persDataPage.isPageOpened());


       propFile = new File("./src/main/resources/personalData.properties");
       try {
            prop.load(new InputStreamReader(new FileInputStream(propFile),"UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        persDataPage.fillData(prop);

    }

    @Test
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
        OtusMainPage mainP = new OtusMainPage(wd);
        //открываем форму логина
        mainP.openLoginPage(wd);
        //Создаем экземпляр страницы ввода логина-пароля
        OtusLoginPage loginP = new OtusLoginPage(wd);

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

    }
    }


