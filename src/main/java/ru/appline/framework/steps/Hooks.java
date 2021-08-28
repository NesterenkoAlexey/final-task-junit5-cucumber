package ru.appline.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;


import static ru.appline.framework.utils.PropConst.BASE_URL;

public class Hooks {

    // Менеджер страничек
    protected PageManager app = PageManager.getPageManager();
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    // Менеджер WebDriver
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @Before
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(props.getProperty(BASE_URL));
    }


    @After
    public void after() {
        InitManager.quitFramework();
    }
}
