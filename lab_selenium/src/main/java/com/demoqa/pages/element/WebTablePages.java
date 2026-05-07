package com.demoqa.pages.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Array;

import static java.lang.reflect.Array.set;

public class WebTablePages extends ElementPage {

    private By registrationAgeField = By.id("age");
    private By submitButton = By.id("submit");

    public WebTablePages(WebDriver driver) {
        super(driver);
    }

    public void clickEdit(String email) {
        By edit = By.xpath("//div[text()='" + email + "']//following::span[@title='Edit']");
        click(edit);
    }
    public void set(By locator, String value) {
        Array.set(locator, Integer.parseInt(value), 10); // default timeout
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public String getTableAge(String email) {
        By tableAge = By.xpath("//div[text()='" + email + "']//preceding::div[1]");
        return find(tableAge).getText();
    }
}