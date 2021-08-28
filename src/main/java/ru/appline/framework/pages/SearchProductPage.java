package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.classes.Conteiner;
import ru.appline.framework.classes.Converter;
import ru.appline.framework.classes.Product;
import ru.appline.framework.managers.PageManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SearchProductPage extends BasePage {

    @FindBy(xpath = "//span[text()[contains(.,'NFC')]]/../..")
    private WebElement nfcSwitch;

    @FindBy(xpath = "//div[@title]/../../..")
    private WebElement sortSelector;

    @FindBy(xpath = "//div[@class = 'widget-search-result-container e1p4']//div//div[@style[contains(., 'grid-column-start')]]")
    private List<WebElement> listOfProduct;

    @FindBy(xpath = "//span[text() = 'Корзина']/..//span[@class[contains(.,'f')]]")
    private WebElement numberOfItemInCart;

    @FindBy(xpath = "//div[text()[contains(., 'Цена')]]/..//input[@qa-id = 'range-to' and @type]")
    private WebElement maxPriceField;

    @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']")
    private WebElement filters;

    @FindBy(xpath = "//div[text()[contains(., 'Бренды')]]/..//span[text()[contains(., 'Посмотреть все')]]")
    private WebElement seeAllBrandsField;


    //Добавляем NFC
    public SearchProductPage nfcCkick() {

        nfcSwitch.click();
        if (!isElementPresent(By.xpath("//span[text()[contains(., 'Беспроводные интерфейсы: NFC')]]"))) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()[contains(., 'Беспроводные интерфейсы: NFC')]]")));

        }
        return pageManager.getSearchProductPage();
    }


    //Выбрать пункт Высокий рейтинг
    public SearchProductPage chooseSort() {
        scrollToUpJs();
        sortSelector.click();
        driverManager.getDriver().findElement(By.xpath("//div[@title = 'Высокий рейтинг']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title = 'Высокий рейтинг']")));
        return pageManager.getSearchProductPage();
    }

    //Добавить четные элементы в корзину
    public SearchProductPage addEvenProducts(int value) {
        int flag = 0;
        int itemsInCart = 0;
        int itemIndex = 0;
        while (itemsInCart < value) {
            Product product = new Product();
            List<WebElement> listButton = (List<WebElement>) listOfProduct.get(itemIndex).findElements(By.xpath(".//button"));
            //Если нет кнопки за час курьером
            flag = Integer.parseInt(numberOfItemInCart.getText());
            if (listButton.size() == 4) {
                product.setName(listOfProduct.get(itemIndex).findElement(By.xpath(".//a[@class[contains(.,'tile-hover-target')]]//span//span")).getText());
                product.setPrice(Converter.convertСurrencyFromStringToInteger(listOfProduct.get(itemIndex).findElement(By.xpath(".//span[@style = 'color: rgb(249, 17, 85);']")).getText()));

                Conteiner.getMapProduct().put(product.getName(), product);

                listButton.get(1).click();
                wait.until(ExpectedConditions.textToBePresentInElement(numberOfItemInCart, Integer.toString(flag + 1)));
                itemsInCart++;
            }
            itemIndex = itemIndex + 2;

        }

        return pageManager.getSearchProductPage();
    }

    //Сохранить в файл товары
    public SearchProductPage saveProductsToFile(){
        try {
            FileWriter writer = new FileWriter(new File("Products.txt"));
            Product productMaxPrice = Conteiner.getMapProduct().values().stream().max(Comparator.comparingInt(Product::getPrice)).get();
            Conteiner.getMapProduct().forEach((s , product) ->{

                StringBuilder sb = new StringBuilder();
                sb.append(product.getName() + " ")
                        .append(product.getPrice())
                        .append("\n");
                System.out.println(sb.toString());
                try {
                    writer.write(sb.toString());
                    writer.write("Продукт с наибольшей ценой: " + productMaxPrice.getName() + " " + productMaxPrice.getPrice());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    //Установка макс цены
    public SearchProductPage setMaxPrice(String sum) {

        fillInputField(maxPriceField, sum);
        maxPriceField.sendKeys(Keys.ENTER);
        WebElement filter = filters.findElement(By.xpath(".//span[contains(text(), 'до')]"));
        waitUtilElementToBeVisible(filter);
        return this;
    }

    public SearchProductPage seeAllBrands() {
        seeAllBrandsField.click();
        return this;
    }

    public SearchProductPage selectBrand(String brandName) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()[contains(.,'Бренды')]]/..//span[text()= '" + brandName + "']/../../..")));
        driverManager.getDriver().findElement(By.xpath("//div[text()[contains(.,'Бренды')]]/..//span[text()= '" + brandName + "']/../../..")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()[contains(., 'Бренды: " + brandName + "')]]")));
        return this;
    }

    //Переход на страницу корзины
    public CartPage goToCartPage() {
        driverManager.getDriver().findElement(By.xpath("//span[text() = 'Корзина']/..")).click();
        return PageManager.getPageManager().getCartPage();
    }


}
