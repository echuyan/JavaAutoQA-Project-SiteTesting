package epam.Tests;

import epam.Pages.PastEventsPage;
import org.junit.Assert;
import org.junit.Test;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;


public class CheckPastEventCardsTest extends BaseTestClass {


    /**Просмотр карточек мероприятий:
    *1 Пользователь переходит на вкладку events
   * 2 Пользователь нажимает на Past Events
   * 3 На странице отображаются карточки прошедших мероприятий.
   * 4 В карточке указана информация о мероприятии:
     *   язык
    *    название мероприятия
    *    дата мероприятия
     *   информация о регистрации
      *  список спикеров // Минимально достаточное - проверить одну карточку. В идеале все что отображаются.
     */
    @Test
    public void checkEventCardTest() {

        /**
         *  создаем экземпляр главной страницы сайта
         */

        MainPage mainP = new MainPage(wd);
        mainP.openEvents();

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


