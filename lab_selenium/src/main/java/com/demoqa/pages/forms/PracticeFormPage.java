package com.demoqa.pages.forms;

import com.demoqa.pages.forms.FormsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage extends FormsPage {

    private By femaleRadio = By.id("gender-radio-2");
    private By sportsHobbyCheckbox = By.id("hobbies-checkbox-1");
    private By readingHobbyCheckbox = By.id("hobbies-checkbox-2");
    private By musicHobbyCheckbox = By.id("hobbies-checkbox-3");
    private By submitButton = By.id("submit");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void selectFemale() {
        scrollTo(femaleRadio);
        clickJS(femaleRadio);
    }

    public boolean isFemaleSelected() {
        return find(femaleRadio).isSelected();
    }
    public void clicksportsHobbyCheckbox(){
        if(!find(sportsHobbyCheckbox).isSelected()){
            scrollTo(sportsHobbyCheckbox);
            clickJS(sportsHobbyCheckbox);
        }
    }


    public void clickReadingCheckbox() {
        if (!find(readingHobbyCheckbox).isSelected()) {
            scrollTo(readingHobbyCheckbox);
            clickJS(readingHobbyCheckbox);
        }
    }

    public void clickMusicCheckbox() {
        if (!find(musicHobbyCheckbox).isSelected()) {
            scrollTo(musicHobbyCheckbox);
            clickJS(musicHobbyCheckbox);
        }
    }

    public void unclickReadingCheckbox() {
        if (find(readingHobbyCheckbox).isSelected()) {
            scrollTo(readingHobbyCheckbox);
            clickJS(readingHobbyCheckbox);
        }
    }

    public boolean isReadingSelected() {
        return find(readingHobbyCheckbox).isSelected();
    }

    public void clickSubmitButton() {
//    scrollToElementJS(submitButton);
        click(submitButton);
    }
}