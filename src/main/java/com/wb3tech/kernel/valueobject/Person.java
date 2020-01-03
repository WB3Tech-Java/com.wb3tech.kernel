package com.wb3tech.kernel.valueobject;

import com.wb3tech.kernel.ValueObject;

public class Person extends ValueObject {

    private String givenName;
    private String surname;

    Person(String givenName, String surname) {
        this.setGivenName(givenName);
        this.setSurname(surname);
    }

    public static Person Of(String givenName, String surname) {
        return new Person(givenName, surname);
    }

    public String getSurname() {
        return this.surname;
    }

    public String getGivenName() {
        return this.givenName;
    }

    private void setGivenName(String givenName) {
        this.givenName = this.applyStringDefaults(givenName);
    }

    private void setSurname(String surname) {
        this.surname = this.applyStringDefaults(surname);
    }


}
