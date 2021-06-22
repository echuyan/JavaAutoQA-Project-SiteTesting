package otus.Pages;

import net.thucydides.core.pages.PageObject;
import org.apache.bcel.generic.SWITCH;
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
import otus.Tests.OtusTest;
import java.util.List;
import java.util.Iterator;

import java.util.Properties;


public class OtusPersonalData extends PageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(OtusTest.class);
    Actions action;


    //title
    @FindBy(xpath = "//h3[contains(.,'Персональные данные')]")
    private WebElement title;
    //имя
    @FindBy(xpath = "//input[@name='fname']")
    private WebElement name;

    //LatinName
    @FindBy(xpath = "//input[@name='fname_latin']")
    private WebElement nameLatin;

    //        surname
    @FindBy(xpath = "//input[@name='lname']")
    private WebElement surname;

    // LatinSurname
    @FindBy(xpath = "//input[@name='lname_latin']")
    private WebElement surnameLatin;

    //blogName
    @FindBy(xpath = "//input[@name='blog_name']")
    private WebElement blogname;


    //birthDate
    @FindBy(xpath = "//input[@name='date_of_birth']")
    private WebElement birthDate;

    //Country list
    @FindBy(xpath = "//input[@name='country']//following-sibling::div")
    private WebElement country;

    //City list
    @FindBy(xpath = "//input[@name='city']//following-sibling::div")
    private WebElement city;

    //English Level
    @FindBy(xpath = "//input[@name='english_level']//following-sibling::div")
    private WebElement englishLevel;

    //          relocationAbility = Да/Нет
    @FindBy(xpath = "//input[@name='ready_to_relocate']//following-sibling::span[contains(.,'Да')]")
    private WebElement relocateYes;
    @FindBy(xpath = "//input[@name='ready_to_relocate']//following-sibling::span[contains(.,'Нет')]")
    private WebElement relocateNo;

    @FindBy(xpath = "//input[@name='ready_to_relocate' and @value='False']")
    private WebElement relocateNoInput;
    @FindBy(xpath = "//input[@name='ready_to_relocate' and @value='True']")
    private WebElement relocateYesInput;



    //  workScheduleFullDay = Да/Нет
    @FindBy(xpath = "//input[@name='work_schedule']")
    private WebElement fullDay;
    @FindBy(xpath = "//input[@name='work_schedule']//following-sibling::span[contains(.,'Полный')]")
    private WebElement fullDayCheck;


    //  flexibleSchedule = Да/Нет
    @FindBy(xpath = "//input[@name='work_schedule' and @value='flexible']")
    private WebElement flexible;
    @FindBy(xpath = "//input[@name='work_schedule' and @value='flexible']//following-sibling::span[contains(.,'Гибкий')]")
    private WebElement flexibleCheck;


    //  remote = Да/Нет
    @FindBy(xpath = "//input[@name='work_schedule' and @value='remote']")
    private WebElement remote;
    @FindBy(xpath = "//input[@name='work_schedule' and @value='remote']//following-sibling::span[contains(.,'Удаленно')]")
    private WebElement remoteCheck;

    //кнопка Добавить
    @FindBy(xpath = "//button[contains(.,'Добавить')]")
    private WebElement addButton;

    @FindBy(xpath = "//span[contains(.,'Способ связи')]")
    private WebElement contactChoose;



    @FindBy(xpath = "//input[@value='vk']//parent::label//following::input[@type='text'][1]")
    private WebElement valueVK;

    @FindBy(xpath = "//input[@value='facebook']//parent::label//following::input[@type='text'][1]")
    private WebElement valueFacebook;


    //Кнопка сохранить
    @FindBy(xpath = "//button[@title='Сохранить и заполнить позже']")
    private WebElement save;
    //Кнопка сохранить
    @FindBy(xpath = "//span[contains(.,'Данные успешно сохранены')]")
    private WebElement success;

    //город на выбор
    @FindBy(xpath = "//span[contains(.,'Город')]")
    private WebElement gorod;
    //fff
    @FindBy(xpath = "//span[contains(.,'fff')]")
    private WebElement fff;

    public OtusPersonalData(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return wait.until(ExpectedConditions.visibilityOf(title)).isEnabled();

    }


    public void fillData(Properties prop) {
        name.clear();
        surname.clear();
        nameLatin.clear();
        surnameLatin.clear();
        blogname.clear();
        birthDate.clear();
        name.sendKeys(prop.getProperty("name"));
        surname.sendKeys(prop.getProperty("surname"));
        nameLatin.sendKeys(prop.getProperty("LatinName"));
        surnameLatin.sendKeys(prop.getProperty("LatinSurname"));
        blogname.sendKeys(prop.getProperty("blogName"));
        birthDate.sendKeys(prop.getProperty("birthDate"));
        birthDate.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.elementToBeClickable(country));

        country.click();
        List<WebElement> lst = driver.findElements(By.ByXPath.xpath("//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_country js-custom-select-options']//button"));

        Iterator<WebElement> it = lst.iterator();
        while (it.hasNext()) {
            WebElement wb = it.next();
            if (wb.getText().equals(prop.getProperty("Country"))) {
                wb.click();

                break;
            }
        }


        wait.until(ExpectedConditions.visibilityOf(gorod));
        gorod.click();
        lst = driver.findElements(By.ByXPath.xpath("//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_city js-custom-select-options']//button"));
        it = lst.iterator();
        while (it.hasNext()) {
            WebElement wb = it.next();
            if (wb.getText().equals(prop.getProperty("City"))) {
                wb.click();
                break;
            }
        }


        wait.until(ExpectedConditions.visibilityOf(englishLevel)).click();
        lst = driver.findElements(By.ByXPath.xpath("//div[@class='lk-cv-block__select-scroll  js-custom-select-options']//button"));
        it = lst.iterator();
        while (it.hasNext()) {
            WebElement wb = it.next();
            if (wb.getText().equals(prop.getProperty("englishLevel"))) {
                wb.click();
                break;
            }
        }

        switch (prop.getProperty("relocationAbility")) {
            case "Да":
                relocateYes.click();
                break;
            case "Нет":
                relocateNo.click();
                break;
            default:
                break;
        }


        if ((prop.getProperty("workScheduleFullDay").equals("Нет")) & (fullDay.isSelected())) {
            fullDayCheck.click();
        } else {
            if ((prop.getProperty("workScheduleFullDay").equals("Да")) & !(fullDay.isSelected())) {
                fullDayCheck.click();
            }
        }



        if ((prop.getProperty("workScheduleFlexible").equals("Нет")) & (flexible.isSelected())) {
            flexibleCheck.click();
        } else {
            if ((prop.getProperty("workScheduleFlexible").equals("Да")) & !(flexible.isSelected())) {
                flexibleCheck.click();
            }
        }


        if ((prop.getProperty("workScheduleRemote").equals("Нет")) & (remote.isSelected())) {
            remoteCheck.click();
        } else {
            if ((prop.getProperty("workScheduleRemote").equals("Да")) & !(remote.isSelected())) {
                remoteCheck.click();
            }
        }

//первый контакт
        addButton.click();
        wait.until(ExpectedConditions.visibilityOf(contactChoose));
        contactChoose.click();

        lst = driver.findElements(By.ByXPath.xpath("//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_service js-custom-select-options']//button"));
        it = lst.iterator();
        while (it.hasNext()) {
            WebElement wb = it.next();
            if (wb.getText().equals("Facebook")) {
                wb.click();
                break;
            }
        }
        lst = driver.findElements(By.ByXPath.xpath("//div[@class='container__row js-formset-row']//child::input[@type='text']"));


        int i = lst.size() - 1;
        lst.get(i).sendKeys(prop.getProperty("contactFacebook"));

//второй контакт
        addButton.click();
        wait.until(ExpectedConditions.visibilityOf(contactChoose));
        contactChoose.click();

        lst = driver.findElements(By.ByXPath.xpath("//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_service js-custom-select-options']//button"));
        it = lst.iterator();
        while (it.hasNext()) {
            WebElement wb = it.next();
            if (wb.getText().equals("VK")) {
                wb.click();
                break;
            }
        }

        lst = driver.findElements(By.ByXPath.xpath("//div[@class='container__row js-formset-row']//child::input[@type='text']"));


        i = lst.size() - 1;
        lst.get(i).sendKeys(prop.getProperty("contactVK"));


        save.click();
        wait.until(ExpectedConditions.visibilityOf(success));

    }


    public void checkData(Properties prop) {
        Assert.assertEquals(prop.getProperty("name"), name.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("surname"), surname.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("LatinName"), nameLatin.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("blogName"), blogname.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("birthDate"), birthDate.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("LatinSurname"), surnameLatin.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("Country"), country.getText());
        Assert.assertEquals(prop.getProperty("City"), city.getText());
        Assert.assertEquals(prop.getProperty("englishLevel"), englishLevel.getText());

        boolean flagNo = relocateNoInput.isSelected();
        boolean flagYes = relocateYesInput.isSelected();
        if ((prop.getProperty("relocationAbility").equals("Нет")) & (flagNo)) {
            Assert.assertEquals(true, true);
        } else {
            if ((prop.getProperty("relocationAbility").equals("Да")) & (flagYes)) {
                Assert.assertEquals(true, true);
            } else {Assert.assertEquals(false,true);
            }
        }



        if ((prop.getProperty("workScheduleFullDay").equals("Да")) & (fullDay.isSelected())) {
            Assert.assertEquals(true, true);
        } else {
            if ((prop.getProperty("workScheduleFullDay").equals("Нет")) & (!fullDay.isSelected())) {
                Assert.assertEquals(true, true);
            } else {Assert.assertEquals(false,true);
            }
        }



        if ((prop.getProperty("workScheduleFlexible").equals("Да")) & (flexible.isSelected())) {
            Assert.assertEquals(true, true);
        } else {
            if ((prop.getProperty("workScheduleFlexible").equals("Нет")) & (!flexible.isSelected())) {
                Assert.assertEquals(true, true);
            } else {Assert.assertEquals(false,true);
            }
        }


        if ((prop.getProperty("workScheduleRemote").equals("Да")) & (remote.isSelected())) {
            Assert.assertEquals(true, true);
        } else {
            if ((prop.getProperty("workScheduleRemote").equals("Нет")) & (!remote.isSelected())) {
                Assert.assertEquals(true, true);
            } else {Assert.assertEquals(false,true);
            }
        }

        Assert.assertEquals(prop.getProperty("contactFacebook"), valueFacebook.getAttribute("value"));
        Assert.assertEquals(prop.getProperty("contactVK"), valueVK.getAttribute("value"));


    }
}