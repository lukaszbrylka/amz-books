package com.amz.books.utils.assertions;

import com.amz.books.steps.CartPageSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartAssertions {

    public static void verifyOrderSummary(CartPageSteps steps, String expectedTitle) {

        String actualTitle = steps.getOrderSummaryTitle().toLowerCase();
        assertTrue(actualTitle != null && !actualTitle.isEmpty(), "Order summary title should be visible");
        assertEquals(expectedTitle, actualTitle, "Order summary title should match expected value");
    }
}
