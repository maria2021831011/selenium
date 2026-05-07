package com.saucedemo.pages;


import base.basepage;
import org.openqa.selenium.By;

public class ProductsPage extends basepage {

        private By productsHeader = By.xpath("//span[text()='Products']");

        public boolean isProductsHeaderDisplayed() {
            return find(productsHeader).isDisplayed();
        }
}
