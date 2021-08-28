package ru.appline.framework.utils;

import io.cucumber.plugin.event.*;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

import static io.cucumber.plugin.event.Status.FAILED;

public class MyAllureListener extends AllureCucumber5Jvm {

    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
            if (testStepFinished.getResult().getStatus().equals(FAILED)){
                Allure.getLifecycle().addAttachment("screenshots" , "image/jpeg" , null ,
                        ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES));
            }
        });
        super.setEventPublisher(publisher);
    }

}
