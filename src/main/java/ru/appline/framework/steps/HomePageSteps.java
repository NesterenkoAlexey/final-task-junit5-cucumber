package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;


public class HomePageSteps {

    PageManager pageManager = PageManager.getPageManager();

    @И("^Выполняем поиск по \"(.+)\"$")
    public void searchProduct(String name) {
        pageManager.getHomePage().searchProduct(name);
    }

}
