package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@placeholder]")
    private WebElement searchMenu;

    @FindBy(xpath = "//input[@placeholder]/../..//button")
    private WebElement searchButton;





    //Поиск
    public SearchProductPage searchProduct(String productName){

        fillInputField(searchMenu , productName);
        searchButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[text()[contains(.,'Категория')]]")));
        return pageManager.getSearchProductPage();

    }


    //Выбор меню из списка
//    public HomePage selectBaseMenu(String nameBaseMenu) {
//        for (WebElement menuItem : listBaseMenu) {
//            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
//                waitUtilElementToBeClickable(menuItem).click();
//                return this;
//            }
//        }
//        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
//        return this;
//    }


}
