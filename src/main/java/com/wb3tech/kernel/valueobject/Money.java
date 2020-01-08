package com.wb3tech.kernel.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money {

    private Currency currency;
    private BigDecimal amount = BigDecimal.valueOf(00.00);

    Money(String currency) {
        this.setCurrency(currency);
    }

    Money(double amount, String currency) {
        this.setAmount(amount);
        this.setCurrency(currency);
    }

    Money(double amount, Currency currency) {
        this.setAmount(amount);
        this.setCurrency(currency);
    }

    public static Money Of(String currency) {
        return new Money(currency);
    }

    public static Money Of(double amount, String currency) {
        return new Money(amount, currency);
    }

    public Money Plus(Money money) {
        if(this.isDifferentCurrency(money)) { throw new ArithmeticException("Cannot add monies of two different currencies."); }
        var totalAmount = this.amount.doubleValue() + money.getAmount();
        return new Money(totalAmount, this.currency);
    }

    public Money Minus(Money money) {
        if(this.isDifferentCurrency(money)) { throw new ArithmeticException("Cannot subtract monies of two different currencies."); }
        var totalAmount = this.amount.doubleValue() - money.getAmount();
        return new Money(totalAmount, this.currency);
    }

    public double getAmount() {
        return this.amount.doubleValue();
    }

    public String getCurrency() {
        return this.currency.getCurrencyCode();
    }

    public boolean equals(Money money) {
        if (this == money) return true;
        return Objects.equals(this.currency, money.currency) &&
                Objects.equals(this.amount, money.amount);
    }

    private void setCurrency(String currency) {
        if(currency == null) { throw new IllegalArgumentException("You must provide a valid currency."); };
        this.currency = Currency.getInstance(currency);
    }

    private void setCurrency(Currency currency) {
        this.currency = Currency.getInstance(currency.getCurrencyCode());
    }

    private void setAmount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
        this.amount= this.amount.setScale(2, RoundingMode.HALF_UP);
    }

    private boolean isSameCurrency(Money money) {
        return this.currency.getCurrencyCode().equals(money.getCurrency());
    }

    private boolean isDifferentCurrency(Money money) {
        return !this.isSameCurrency(money);
    }

}
