package com.demoqa.pages.forms;

import com.demoqa.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsPage extends HomePage {

    private By practiceForm = By.xpath("//span[text()='Practice Form']");

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    public PracticeFormPage clickPracticeForm() {
        scrollTo(practiceForm);
        clickJS(practiceForm);
        return new PracticeFormPage(driver);
    }
}