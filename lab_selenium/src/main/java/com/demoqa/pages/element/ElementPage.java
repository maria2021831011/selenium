package com.demoqa.pages.element;

import com.demoqa.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementPage extends HomePage {

    private By webtablemenu = By.xpath("//span[text()='Web Tables']");

    public ElementPage(WebDriver driver) {
        super(driver);
    }

    public WebTablePages clickwebtables() {
        click(webtablemenu);
        return new WebTablePages(driver); // FIXED
    }
}