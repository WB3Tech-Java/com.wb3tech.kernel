package com.wb3tech.kernel.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Kernel - Value Object - Money")
public class MoneyTests {


    @Test @Tag("Small") @DisplayName("Should create a default money object with about equal to 00.00 and currency of 'USD'")
    public void DefaultMoney() {
        var money = Money.Of("USD");
        assertEquals(00.00, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test @Tag("Small") @DisplayName("Should throw an IllegalArgumentException " +
            "with message 'You must provide a valid currency.'")
    public void ThrowIllegalArgumentExceptionForNullCurrency() {
        var exception = assertThrows(IllegalArgumentException.class, () -> Money.Of(null));
        Assertions.assertEquals("You must provide a valid currency.", exception.getMessage());

    }

    @Test @Tag("Small") @DisplayName("Should create Money object with 15.56 as amount and 'USD' as currency.")
    public void CreateMoney() {

        var money = Money.Of(15.56, "USD");
        assertEquals(15.56, money.getAmount());
        assertEquals("USD", money.getCurrency());

    }

    @Test @Tag("Small") @DisplayName("Should create a money object with amount -200")
    public void ShouldCreateNegativeMoney() {

        var negTwoHundred = Money.Of(-200, "USD");
        Assertions.assertEquals(-200, negTwoHundred.getAmount());

    }

    @Test @Tag("Small") @DisplayName("Should create Money object and round 15.566 up to 15.57 as amount and 'USD' as currency.")
    public void CreateMoneyRoundUp() {

        var money = Money.Of(15.566, "USD");
        assertEquals(15.57, money.getAmount());
        assertEquals("USD", money.getCurrency());

    }

    @Test @Tag("Small") @DisplayName("Should create Money object and round 15.544 down to 15.54 as amount and 'USD' as currency.")
    public void CreateMoneyRoundDown() {

        var money = Money.Of(15.544, "USD");
        assertEquals(15.54, money.getAmount());
        assertEquals("USD", money.getCurrency());

    }

    @Test @Tag("Small") @DisplayName("Should get a Money with amount of 13.33 when you add a money instance of 13.00 " +
            "and a second instance of 0.33")
    public void AddTwoMonies() {

        var thirteen = Money.Of(13, "USD");
        var thirtyThreeCents = Money.Of(.33, "USD");
        var total = thirteen.Plus(thirtyThreeCents);

        assertEquals(13.33, total.getAmount());
        assertEquals("USD", total.getCurrency());

    }

    @Test @Tag("Small") @DisplayName("Should get a Money with amount of 13.33 when you subtract a money instance of 13.66 " +
            "and a second instance of 0.33")
    public void SubtractTwoMonies() {

        var thirteenSixtySix = Money.Of(13.66, "USD");
        var thirtyThreeCents = Money.Of(.33, "USD");
        var total = thirteenSixtySix.Minus(thirtyThreeCents);

        assertEquals(13.33, total.getAmount());
        assertEquals("USD", total.getCurrency());

    }

    @Test @Tag("Small") @DisplayName("Money with amount of 13.00 USD should be less than Money with amount 15.00 USD")
    public void CompareAmountLessThan() {

        var thirteen = Money.Of(13.00, "USD");
        var fifteen = Money.Of(15.00, "USD");
        var isLessThan = thirteen.LessThan(fifteen);

        assertTrue(isLessThan);

    }

    @Test @Tag("Small") @DisplayName("Money with amount of 15.00 USD should be greater than Money with amount 13.00 USD")
    public void CompareAmountGreaterThan() {

        var thirteen = Money.Of(13.00, "USD");
        var fifteen = Money.Of(15.00, "USD");
        var isGreaterThan = fifteen.GreaterThan(thirteen);

        assertTrue(isGreaterThan);

    }

    @Test @Tag("Small") @DisplayName("Money with amount of 13.00 USD should equal money with amount 13.00 USD")
    public void CompareAmountEquals() {

        var fifteenInstanceOne = Money.Of(15.00, "USD");
        var fifteenInstanceTwo = Money.Of(15.00, "USD");
        var isGreaterThan = fifteenInstanceOne.Equals(fifteenInstanceTwo);

        assertTrue(isGreaterThan);

    }

    @Test @Tag("Small") @DisplayName("Should get a Money with amount of -5 when you subtract a money instance of 10 " +
            "and a second instance o 15")
    public void ShouldGetNegativeMoney() {

        var ten = Money.Of(10, "USD");
        var fifteen = Money.Of(15, "USD");
        var total = ten.Minus(fifteen);

        assertEquals(-5, total.getAmount());

    }

    @Test @Tag("Small") @DisplayName("Should throw an ArithmeticException" +
            "with message 'Cannot subtract monies of two different currencies'")
    public void SubtractShouldThrowArithmeticExceptionForNotSameCurrency() {


        var USDollar = Money.Of(13, "USD");
        var britishPounds = Money.Of(10, "GBP");

        var exception = assertThrows(ArithmeticException.class, () -> USDollar.Minus(britishPounds));
        Assertions.assertEquals("Cannot subtract monies of two different currencies.", exception.getMessage());


    }

    @Test @Tag("Small") @DisplayName("Should throw an ArithmeticException " +
            "with message 'Cannot add monies of two different currencies'")
    public void AddShouldThrowArithmeticExceptionForNotSameCurrency() {

        var USDollar = Money.Of(13, "USD");
        var britishPounds = Money.Of(10, "GBP");

        var exception = assertThrows(ArithmeticException.class, () -> USDollar.Plus(britishPounds));
        Assertions.assertEquals("Cannot add monies of two different currencies.", exception.getMessage());

    }

    @Test @Tag("Small") @DisplayName("Should throw an ArithmeticException " +
            "with message 'Cannot compare of two different currencies.' " +
            "when comparing amount-less-than for two different currencies.")
    public void AmountLessThanThrowArithmeticExceptionForNotSameCurrency() {

        var thirteen = Money.Of(13.00, "USD");
        var fifteen = Money.Of(15.00, "GBP");

        var exception = assertThrows(ArithmeticException.class, () -> thirteen.LessThan(fifteen));
        Assertions.assertEquals("Cannot compare of two different currencies.", exception.getMessage());

    }

    @Test @Tag("Small") @DisplayName("Should throw an ArithmeticException " +
            "with message 'Cannot compare of two different currencies.' " +
            "when comparing amount-greater-than for two different currencies.")
    public void AmountGreaterThanArithmeticExceptionForNotSameCurrency() {

        var thirteen = Money.Of(13.00, "USD");
        var fifteen = Money.Of(15.00, "GBP");

        var exception = assertThrows(ArithmeticException.class, () -> fifteen.GreaterThan(thirteen));
        Assertions.assertEquals("Cannot compare of two different currencies.", exception.getMessage());

    }

    @Test @Tag("Small") @DisplayName("Should throw an ArithmeticException " +
            "with message 'Cannot compare of two different currencies.' " +
            "when comparing amount-equals for two different currencies.")
    public void AmountEqualsArithmeticExceptionForNotSameCurrency() {

        var fifteenInstanceOne = Money.Of(15.00, "USD");
        var fifteenInstanceTwo = Money.Of(15.00, "GBP");

        var exception = assertThrows(ArithmeticException.class, () -> fifteenInstanceOne.Equals(fifteenInstanceTwo));
        Assertions.assertEquals("Cannot compare of two different currencies.", exception.getMessage());

    }

    @Test @Tag("Small") @DisplayName("A money instance of $1 should equal the second instance of $1")
    public void ShouldEqual() {

        var firstDollarInstance = Money.Of(1, "USD");
        var secondDollarInstance = Money.Of(1, "USD");

        assertTrue(firstDollarInstance.equals(secondDollarInstance));
    }

    @Test @Tag("Small") @DisplayName("A money instance of $1 should not an instnace of 1 GBP")
    public void TwoDifferentCurrenciesShouldNotEqual() {

        var oneDollar = Money.Of(1, "USD");
        var oneGBP = Money.Of(1, "GBP");

        assertFalse(oneDollar.equals(oneGBP));
    }

    @Test @Tag("Small") @DisplayName("A money instance of $1 should equal the second instance of $1")
    public void TwoDifferentAmountsShouldNotEqual() {

        var oneDollar = Money.Of(1, "USD");
        var twoDollar = Money.Of(2, "USD");

        assertFalse(oneDollar.equals(twoDollar));
    }

}
