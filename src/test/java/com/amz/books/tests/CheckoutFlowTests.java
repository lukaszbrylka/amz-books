package com.amz.books.tests;

import com.amz.books.driver.DriverManager;
import com.amz.books.model.Book;
import com.amz.books.pages.BooksPage;
import com.amz.books.pages.CartPage;
import com.amz.books.pages.CheckoutPage;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckoutFlowTests extends BaseTest {

    private BooksPage booksPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private String bookTitle;
    private List<Book> searchResults;


    @BeforeEach
    void initPages() {
        booksPage = new BooksPage(DriverManager.driver());
        cartPage = new CartPage(DriverManager.driver());
        checkoutPage = new CheckoutPage(DriverManager.driver());
    }

    @Test
    @Order(1)
    void shouldCartNumberIncreaseAfterAddingBook() {
        bookTitle = "thinking in java";
        booksPage.searchForBookByTitle(bookTitle)
                .addBookToCart(2);
        searchResults = booksPage.getSearchResults();
        assertTrue(booksPage.isCartValueIncreased(), "Cart value should increase after adding item");
    }

    @Test
    @Order(2)
    void shouldNavigateToBasketPage() {
        cartPage = booksPage.goToCartPageViaOpenedSidePanel();
        assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
    }

    @Test
    @Order(3)
    void shouldVerifyOrderSummary() {
        Book expectedBookData = searchResults.get(1);
        assertTrue(cartPage.isOrderSummaryDetailsCorrect(expectedBookData), "Order summary should be correct");
    }

    @Test
    @Order(4)
    void shouldCheckoutAsNewCustomer() {
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.enterEmail("test@user.com");
        checkoutPage.continueAsGuest();
        assertTrue(checkoutPage.isEmailAccepted(), "Email should be accepted");
    }

    @Test
    @Order(5)
    void shouldVerifyCheckoutSummary() {
        assertTrue(checkoutPage.isCheckoutSummaryCorrect(), "Checkout summary should be correct");
    }

    @Test
    @Order(6)
    void shouldFillDeliveryAddress() {
        checkoutPage.fillDeliveryAddress("Tom Riddle", "123 Test St", "Wroclaw", "50-001", "Poland");
        assertTrue(checkoutPage.isAddressSaved(), "Delivery address should be saved");
    }

    @Test
    @Order(7)
    void shouldVerifyPaymentSectionIsDisabled() {
        assertTrue(checkoutPage.isPaymentSectionDisabled(), "Payment section should be disabled for editing");
    }


    @Test
    @Order(8)
    void shouldContinueToPayment() {
        checkoutPage.clickContinueToPayment();
        assertTrue(checkoutPage.isOnPaymentPage(), "Should navigate to payment page");
    }


    @Test
    @Order(9)
    void shouldVerifyAddressSectionsAreDisabled() {
        assertTrue(checkoutPage.areAddressSectionsDisabled(), "Address sections should be disabled for editing");
    }

    @Test
    @Order(10)
    void shouldEnterCardDetails() {
        checkoutPage.enterCardDetails("4111111111111111", "12", "2028", "123");
        assertTrue(checkoutPage.isCardAccepted(), "Card details should be accepted");
    }

}
