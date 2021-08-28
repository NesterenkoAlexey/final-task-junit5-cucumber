package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{

    @FindBy(xpath = "//span[text()[contains(., 'Удалить выбранные')]]")
    private WebElement deleteSelectedButton;


    @FindBy(xpath = "//div[text() = 'Удалить']/../..")
    private WebElement deleteButton;

    //Удаляем все товары из корзины
    public CartPage deleteAllItems(){
        deleteSelectedButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text() = 'Удалить']/../..")));
        deleteButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[text() = 'Корзина']/..//span[@class[contains(.,'f')]]"), "0"));
        return this;
    }

}
