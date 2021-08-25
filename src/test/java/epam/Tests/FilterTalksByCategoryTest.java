package epam.Tests;

import epam.Pages.MainPage;
import epam.Pages.VideoTalksLibraryPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;


public class filterTalksByCategoryTest extends BaseTestClass {


    /**
     * Фильтрация докладов по категориям:
     * 1 Пользователь переходит на вкладку Talks Library
     * 2 Пользователь нажимает на More Filters
     * 3 Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, На вкладке фильтров
     * 4 На странице отображаются карточки соответствующие правилам выбранных фильтров
     */
    @Test
    @DisplayName("Filter video talks by category = Testing, language = English and location = Belarus")
    public void filterTalksByCategoryTest() {

        /**
         *  создаем экземпляр главной страницы сайта
         */

        MainPage mainP = new MainPage(wd);
        mainP.openVideo();

        /**
         *Создаем экземпляр страницы Video, проверяем ее открытие
         */
        VideoTalksLibraryPage videoTalksP = new VideoTalksLibraryPage(wd);
        Assert.assertTrue(videoTalksP.isPageOpened());


        /**
         * Проверяем Фильтрацию по Category – Testing, Location – Belarus, Language – English,
         */
        videoTalksP.checkFiltration();

    }
}


