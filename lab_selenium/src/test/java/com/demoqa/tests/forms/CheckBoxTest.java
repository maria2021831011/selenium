package com.demoqa.tests.forms;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxTest extends BaseTest {
    @Test
    public void testcheckbox(){
        var formspage= homePage.goToForms().clickPracticeForm();
        formspage.clicksportsHobbyCheckbox();
        formspage.clickMusicCheckbox();
        formspage.clickReadingCheckbox();
        formspage.unclickReadingCheckbox();
        formspage.isReadingSelected();
        boolean isreadingcheckboxselected=formspage.isFemaleSelected();
        Assert.assertFalse(isreadingcheckboxselected,"\n reading is selected");
    }
}
