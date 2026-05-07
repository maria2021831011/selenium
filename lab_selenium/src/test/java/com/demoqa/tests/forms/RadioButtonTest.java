package com.demoqa.tests.forms;

import com.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButtonTest extends BaseTest {

    @Test
    public void testFemaleRadio() {

        var formsPage = homePage.goToForms()
                .clickPracticeForm();

        formsPage.selectFemale();

        Assert.assertTrue(formsPage.isFemaleSelected());
    }
}