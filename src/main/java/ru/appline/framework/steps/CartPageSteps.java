package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class CartPageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("Удаляем все элементы их корзины")
    public void deleteAllItems(){
        pageManager.getCartPage().deleteAllItems();
    }

}
