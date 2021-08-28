package ru.appline.tests;


import org.junit.jupiter.api.Test;
import ru.appline.base.BaseTest;


public class ItemInCartTest extends BaseTest {

    @Test
    public void startTest() {
        app.getHomePage()
                .searchProduct("iphone")
                .setMaxPrice("100000")
                .nfcCkick()
                .chooseSort()
                .addEvenProducts(4)
                .saveProductsToFile()
                .goToCartPage()
                .deleteAllItems();

//        app.getHomePage()
//                .searchProduct("беспроводные наушники")
//                .setMaxPrice("10000")
//                .seeAllBrands()
//                .selectBrand("Beats")
//                .selectBrand("Samsung")
//                .selectBrand("Xiaomi")
//                .chooseSort()
//                .addEvenProducts(2)
//                .goToCartPage()
//                .deleteAllItems();


        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
