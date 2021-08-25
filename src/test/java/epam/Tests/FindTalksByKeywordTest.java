package epam.Tests;

import epam.Pages.MainPage;
import epam.Pages.VideoTalksLibraryPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;


public class FindTalksByKeywordTest extends BaseTestClass {


    /**
     * Поиск докладов по ключевому слову:
     * 1 Пользователь переходит на вкладку VIDEO - Talks Library
     * 2 Пользователь вводит ключевое слово QA в поле поиска
     * 3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
     */
    @Test
    @DisplayName("Find video talks by keyword = QA")
    public void filterTalksByKeywordTest() {

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
         * Проверяем Фильтрацию по Ключевому слову QA
         */
        videoTalksP.checkFiltrationKeyword();

    }
}


