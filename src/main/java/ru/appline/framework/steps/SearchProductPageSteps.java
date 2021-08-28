package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class SearchProductPageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^Ограничивем максимальную цену \"(.+)\"$")
    public void setMaxPrice(String sum) {
        pageManager.getSearchProductPage().setMaxPrice(sum);
    }

    @И("Отмечаем чекбокс NFC")
    public void nfcCkick() {
        pageManager.getSearchProductPage().nfcCkick();
    }

    @И("Отмечаем чекбокс Высокий рейтинг")
    public void chooseSort(){
        pageManager.getSearchProductPage().chooseSort();
    }

    @И("^Добавляем в корзину первые \"(\\d+)\" четных товара$")
    public void addEvenProducts(int num) {
        pageManager.getSearchProductPage().addEvenProducts(num);
    }

    @И("Переходим в корзину")
    public void goToCartPage(){
        pageManager.getSearchProductPage().goToCartPage();
    }

    @И("Открываем список всех брендов")
    public void seeAllBrands(){
        pageManager.getSearchProductPage().seeAllBrands();
    }

    @И("^Выбираем бренд \"(.+)\"$")
    public void selectBrand(String brandName){
        pageManager.getSearchProductPage().selectBrand(brandName);
    }

    @И("Сохраняем товар")
    public void saveProducts(){ pageManager.getSearchProductPage().saveProductsToFile();}
}
