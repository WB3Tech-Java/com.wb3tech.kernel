package com.wb3tech.kernel.valueobject;

import java.util.regex.Pattern;

public class EmailAddress extends ValueObject {

    private static final String emailRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    private String emailAddress;

    EmailAddress(String emailAddress)  {
        this.setEmailAddress(emailAddress);
    }

    /***
     * Creates an Email Address compliant with RFC 5322 standards.
     * Reference: https://howtodoinjava.com/regex/java-regex-validate-email-address/
     * @param emailAddress
     * @return EmailAddress
     * @throws IllegalArgumentException
     */
    public static EmailAddress Of(String emailAddress) {
        return new EmailAddress(emailAddress);
    }

    public String toString() {
        return this.emailAddress;
    }

    private void setEmailAddress(String emailAddress) {
        var addressWithDefaultsApplied = this.applyStringDefaults(emailAddress);
        this.emailAddress = this.validateEmailAddress(addressWithDefaultsApplied);
    }

    private String validateEmailAddress(String emailAddress) {
        var isValid = EMAIL_PATTERN.matcher(emailAddress).find();
        if(!isValid) { throw new IllegalArgumentException(String.format("This is not valid email address '%s'.", emailAddress)); }
        return emailAddress;
    }

}
