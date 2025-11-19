package com.amz.books.utils.assertions;

import com.amz.books.steps.CheckoutPageSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutAssertions {


    public static void verifyOrderSummary(CheckoutPageSteps steps) {
        assertTrue(steps.isOrderSummaryVisible(), "Order summary should be visible");
    }

    public static void verifyCheckoutSummary(CheckoutPageSteps steps) {
        assertTrue(steps.isCheckoutSummaryVisible(), "Checkout summary should be visible after email entry");
    }

    public static void verifyPaymentSectionDisabled(CheckoutPageSteps steps) {
        assertTrue(steps.isPaymentSectionDisabled(), "Payment section should be disabled for editing");
    }

    public static void verifyAddressSectionsDisabled(CheckoutPageSteps steps) {
        assertTrue(steps.areAddressSectionsDisabled(), "Delivery and Billing Address sections should be disabled");
    }
}