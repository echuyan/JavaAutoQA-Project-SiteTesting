package epam.Tests;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;
import epam.Pages.VideoTalksLibraryPage;
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


public class FindTalksByKeywordTest {
    private Logger logger = LogManager.getLogger(FilterTalksByCategoryTest.class);
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
     *Поиск докладов по ключевому слову:
     *1 Пользователь переходит на вкладку VIDEO - Talks Library
     *2 Пользователь вводит ключевое слово QA в поле поиска
     *3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
     */
    @Test
    public void FilterTalksByKeywordTest() {

        /**
         *  создаем экземпляр главной страницы сайта
         */

        MainPage mainP = new MainPage(wd);
        mainP.openVideo(wd);

        /**
         *Создаем экземпляр страницы Video, проверяем ее открытие
         */
        VideoTalksLibraryPage videoTalksP = new VideoTalksLibraryPage (wd);
        Assert.assertTrue(videoTalksP.isPageOpened());



        /**
         * Проверяем Фильтрацию по Ключевому слову QA
         */
        videoTalksP.checkFiltrationKeyword();

    }
}


