package epam.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static drivers.WebDriverInit.initDriver;

public class baseTestClass {

    private Logger logger = LogManager.getLogger(baseTestClass.class);
    public WebDriver wd;

    @Before
    public void setup() {
        //инициализируем драйвер
        wd = initDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        logger.info("Драйвер поднят");
    }

    @After
    public void shutdown() {
        wd.close();
        logger.info("Драйвер опущен");
    }


}
