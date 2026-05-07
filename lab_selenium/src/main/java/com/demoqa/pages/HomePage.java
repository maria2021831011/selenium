package com.demoqa.pages;

import com.demoqa.base.BasePage;
import com.demoqa.pages.element.ElementPage;
import com.demoqa.pages.forms.FormsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By formsCard = By.xpath("//h5[text()='Forms']");
    private By elementsCard = By.xpath("//h5[text()='Elements']");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public FormsPage goToForms() {
        scrollTo(formsCard);
        clickJS(formsCard);
        return new FormsPage(driver);
    }
    public ElementPage goToElements(){
        scrollTo(elementsCard);
        clickJS(elementsCard);
        return  new ElementPage();
    }
}