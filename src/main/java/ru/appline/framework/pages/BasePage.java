package ru.appline.framework.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */


    private static final TestPropManager props = TestPropManager.getTestPropManager();
    protected final DriverManager driverManager = DriverManager.getDriverManager();


    /**
     * Менеджер страничек
     *
     * @see PageManager
     */
    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    @FindBy(xpath = "//a[@id = 'header-logo']")
    private WebElement mainPageElement;

    public BasePage() {

        PageFactory.initElements(driverManager.getDriver(), this);

    }
    // Скролл до любого эл-та
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected void scrollToUpJs() {
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }



    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-элемент поле ввода
     * @param value - значение вводимое в поле
     */
    protected void fillInputField(WebElement field, String value) {

        field.click();
        field.clear();
        field.sendKeys(Keys.chord(Keys.CONTROL , "a") , value);
    }

    public void waitTime(int timeMilSec){
        try {
            Thread.sleep(timeMilSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Общий метод по заполнению полей с датой
     *
     * @param field - веб-элемент поле с датой
     * @param value - значение вводимое в поле с датой
     */
    protected void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }


    //Проверка существует ли элемент
    public boolean isElementPresent(By by) {
        boolean flag = false;
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driverManager.getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {

        }finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }

}
