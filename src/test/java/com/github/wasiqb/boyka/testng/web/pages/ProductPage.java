package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class ProductPage {

    /**
     * Product page instance.
     *
     * @return {@link ProductPage}
     */
    public static ProductPage productPage() {
        return new ProductPage();
    }

    private final Locator sauceLabsBackPackParent = buildLocator().web(cssSelector("")).build();
    private final Locator sauceLabsBackPackTitle = buildLocator().web(cssSelector("#item_4_title_link > div"))
            .build();
    private final Locator sauceLabsBackPackDesc = buildLocator().web(cssSelector("div:nth-child(1) > div.inventory_item_description > div.inventory_item_label > div")).build();
    private final Locator sauceLabsBackPackPrice = buildLocator().web(cssSelector("div:nth-child(1) > div.inventory_item_description > div.pricebar > div:nth-child(1) > div.inventory_item_description > div.pricebar > div.inventory_item_price"))
            .build();
    private final Locator sauceLabsBackPackAddToCardBtn = buildLocator().web(id("add-to-cart-sauce-labs-backpack"))
            .build();

    private final Locator shoppingCart = buildLocator().web(cssSelector("#shopping_cart_container > a > span"))
            .build();

    private ProductPage() {
        // Avoid explicit class initialisation
    }
}
