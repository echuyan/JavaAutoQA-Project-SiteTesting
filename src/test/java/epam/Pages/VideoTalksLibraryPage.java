package epam.Pages;

import epam.Tests.FutureEventsTest;
import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class VideoTalksLibraryPage extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(FutureEventsTest.class);

    //Заголовок
      @FindBy(xpath = "//h1[contains(.,'Talks Library')]")
      private WebElement header;

    //More filters
    @FindBy(xpath ="//span[contains(.,'More Filters')]")
    private WebElement moreFilters;

    //Location filter
    @FindBy(xpath ="//span[@class='evnt-filter-text'][contains(.,'Location')]")
    private WebElement locationFilter;

    //Language filter
    @FindBy(xpath ="//span[@class='evnt-filter-text'][contains(.,'Language')]")
    private WebElement languageFilter;

    //Language filter
    @FindBy(xpath ="//span[@class='evnt-filter-text'][contains(.,'Category')]")
    private WebElement categoryFilter;

    //Category = Testing
    @FindBy(xpath ="//label[@data-value='Testing']")
    private WebElement categoryTesting;

    //Location = Belarus
    @FindBy(xpath ="//label[@data-value='Belarus']")
    private WebElement locationBelarus;


    //Language = English
    @FindBy(xpath ="//label[@data-value='ENGLISH']")
    private WebElement languageEnglish;

    //found results
    @FindBy(xpath ="//p[contains(.,' results found for')]")
    private WebElement foundResults;

    //Back
    @FindBy(xpath ="//a[contains(.,'Back')]")
    private WebElement back;

    //All Talks
    @FindBy(xpath ="//h3[contains(.,'All Talks')]")
    private WebElement alltalks;

    //Hide filters
    @FindBy(xpath ="//span[contains(.,'Hide Filters')]")
    private WebElement hideFilters;


    //close button
    @FindBy(xpath ="//div[@class='evnt-alert-content']//following-sibling::button[@class='close']")
    private WebElement close;

    //card language
    @FindBy(xpath = "//div[@class='evnt-talk-details language evnt-now-past-talk']/span")
    private WebElement cardLanguage;


    //card label=Testing
    @FindBy(xpath = "//div[@class='evnt-talk-details topics']//div//label[contains(.,'Testing')]")
    private WebElement cardLabelTesting;

    //card address is in Belarus
    @FindBy(xpath =   "//div[@class='evnt-talk-details location evnt-now-past-talk']//span[contains(.,'Belarus')]")
    private WebElement cardAddressBelarus;


    //Конструктор
        public VideoTalksLibraryPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15,50);
        PageFactory.initElements(driver, this);

    }

    public boolean isPageOpened(){
        return wait.until(ExpectedConditions.visibilityOf(header)).isDisplayed();
    }



    /**
     * Проверка количества будущих мероприятий
     */
   public void checkFiltration()
    {
        if (close.isDisplayed()) {close.click();}
        Actions action = new Actions(driver);
        moreFilters.click();

        categoryFilter.click();
        wait.until(ExpectedConditions.elementToBeClickable(categoryTesting)).click();
        wait.until(ExpectedConditions.visibilityOf(locationFilter)).click();
        wait.until(ExpectedConditions.visibilityOf(locationBelarus)).click();
        languageFilter.click();
        wait.until(ExpectedConditions.elementToBeClickable(languageEnglish)).click();
        wait.until(ExpectedConditions.visibilityOf(foundResults));
        hideFilters.click();
        wait.until(ExpectedConditions.visibilityOf(moreFilters));

        List<WebElement> lst = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-talk-card']/a"));
        logger.info("Найдено карточек с мероприятиями в Беларуси на английском языке по тематике Тестирования: "+lst.size());
        int i = lst.size();
        int j =0;
        for (j=0;j<i;j++) {
           action.moveToElement(lst.get(j)).click().build().perform();
           wait.until(ExpectedConditions.visibilityOf(back));
           Assert.assertEquals("Мероприятие не на английском языке!","ENGLISH",cardLanguage.getText());
           Assert.assertTrue("Мероприятие не принадлежит категории Тестирование!",cardLabelTesting.isDisplayed());
           Assert.assertTrue("Мероприятие проводится не в Беларуси!",cardAddressBelarus.isDisplayed());

           back.click();

           wait.until(ExpectedConditions.visibilityOf(foundResults));
           lst = driver.findElements(By.ByXPath.xpath("//div[@class='evnt-talk-card']/a"));
        }

    }


   }

