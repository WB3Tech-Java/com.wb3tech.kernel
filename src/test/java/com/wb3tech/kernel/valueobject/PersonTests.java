package com.wb3tech.kernel.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Kernel - Value Object - Person ")
public class PersonTests {

    @Test
    @Tag("Small")
    @DisplayName("Should create a new Person with a valid Identity when supplied with a given name, and surname")
    public void InstantiateNewPerson() {

        var person = Person.Of("Bill", "Bensing");

        Assertions.assertEquals("Bill", person.getGivenName());
        Assertions.assertEquals("Bensing", person.getSurname());
    }

    @Test
    @Tag("Small")
    @DisplayName("Should trim white spaces for supplied given name, and surname.")
    public void TrimWhiteSpacesForNames() {

        var person = Person.Of("  Bill  ", "  Bensing  ");

        Assertions.assertEquals("Bill", person.getGivenName());
        Assertions.assertEquals("Bensing", person.getSurname());
    }

    @Test
    @Tag("Small")
    @DisplayName("Should default to an empty string when supplied with a null given name, and surname.")
    public void EmptyStringForNullNames() {

        var person = Person.Of(null, null);

        Assertions.assertEquals("", person.getGivenName());
        Assertions.assertEquals("", person.getSurname());
    }

}
