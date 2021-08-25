package epam.Tests;

import epam.Pages.EventsPage;
import epam.Pages.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;


public class upcomingEventsDatesValidationTest extends baseTestClass {


    /**
     * Валидация дат предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Upcoming Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)
     */
    @Test
    @DisplayName("Upcoming events dates validation")
    public void UpcomingEventsDatesValidationTest() {

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
         * Проверяем даты будущих мероприятий
         */
        eventsP.checkDates();

    }
}


