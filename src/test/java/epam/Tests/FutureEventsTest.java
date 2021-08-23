package epam.Tests;


import org.junit.Test;
import org.junit.Assert;
import epam.Pages.*;



public class futureEventsTest extends baseTestClass {


    /**
     * Просмотр предстоящих мероприятий:
     *1 Пользователь переходит на вкладку events
     *2 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events
    */
    @Test
    public void futureEventsTest() {


        //создаем экземпляр главной страницы сайта
        MainPage mainP = new MainPage(wd);
        mainP.openEvents();

        //Создаем экземпляр страницы Events, проверяем ее открытие и сверяем количество событий
        EventsPage  eventsP = new EventsPage (wd);
        Assert.assertTrue(eventsP.isPageOpened());
        eventsP.checkEventsCount();

    }

}


