package com.amz.books.tests;

import com.amz.books.steps.BooksPageSteps;
import com.amz.books.steps.CartPageSteps;
import com.amz.books.steps.CheckoutPageSteps;
import com.amz.books.driver.DriverManager;
import com.amz.books.utils.assertions.CartAssertions;
import com.amz.books.utils.assertions.CheckoutAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckoutFlowTests extends BaseTest {

    private BooksPageSteps booksSteps;
    private CartPageSteps cartPageSteps;
    private CheckoutPageSteps checkoutPageSteps;
    private String bookTitle;


    @BeforeEach
    void initSteps() {
        booksSteps = new BooksPageSteps(DriverManager.getInstance().getDriver());
        cartPageSteps = new CartPageSteps(DriverManager.getInstance().getDriver());
        checkoutPageSteps = new CheckoutPageSteps(DriverManager.getInstance().getDriver());
    }
    // this is NOT example of "Chain of responsibility" pattern, it is just method chaining.

    @Test
    void shouldCompleteCheckoutFlow() {
        bookTitle = "thinking in java";

        booksSteps.openBooksPage()
                .searchBook(bookTitle)
                .addBookToCart(2)
                .goToCartPageFromSidePanel();

        CartAssertions.verifyOrderSummary(cartPageSteps, bookTitle);

        cartPageSteps.proceedToCheckout()
                .enterEmail("test@user.com");


        CheckoutAssertions.verifyCheckoutSummary(checkoutPageSteps);

        checkoutPageSteps
                .fillAddress("Tom Riddle", "123 Test St", "Wroclaw", "50-001", "Poland");
        CheckoutAssertions.verifyPaymentSectionDisabled(checkoutPageSteps);


        checkoutPageSteps.continueToPayment();
        CheckoutAssertions.verifyAddressSectionsDisabled(checkoutPageSteps);


        checkoutPageSteps.enterCardDetails("4111111111111111", "12", "2028", "123");


    }
}