package epam.Tests;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;
import epam.Pages.PastEventsPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;


public class canadaPastEventsTest extends baseTestClass {


    /**
     * Просмотр прошедших мероприятий в Канаде:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
     * 4 На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.
     */
    @Test
    @DisplayName("Check past events in Canada")

    public void CanadaPastEventsTest() {

        /**
         *  создаем экземпляр главной страницы сайта
         */

        MainPage mainP = new MainPage(wd);
        mainP.openEvents();

        /**
         *Создаем экземпляр страницы Events, проверяем ее открытие
         */
        EventsPage eventsP = new EventsPage(wd);
        Assert.assertTrue(eventsP.isPageOpened());


        /**
         *Создаем экземпляр страницы Past Events, проверяем ее открытие
         */
        eventsP.openPastEvents();
        PastEventsPage pastEvents = new PastEventsPage(wd);
        Assert.assertTrue(pastEvents.isPageOpened());

        /**
         * Проверяем прошедшие канадские мероприятия
         */

        pastEvents.checkCanadaEvents();


    }
}


