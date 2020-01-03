package com.wb3tech.kernel.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kernel - Value Object - Username")
public class UsernameTests {

    @Test @Tag("Small")
    @DisplayName("Should instantiate a valid username")
    public void InstantiateUsername() {

        var username = Username.Of("WB3Tech");

        Assertions.assertEquals("WB3Tech", username.toString());

    }

    @Test @Tag("Small")
    @DisplayName("Should instantiate a valid username with minimum valid characters")
    public void InstantiateUsernameWithMinCharacters() {

        var username = Username.Of("WB3");

        Assertions.assertEquals("WB3", username.toString());

    }

    @Test @Tag("Small")
    @DisplayName("Should trim all whitespace.")
    public void InstantiateUsernameTrimWhitespace() {

        var username = Username.Of("  WB3Tech  ");

        Assertions.assertEquals("WB3Tech", username.toString());

    }

    @Test @Tag("Small")
    @DisplayName("Should throw a InvalidArgumentException with message 'Username must be at least 3 characters.' " +
            "when provided less than 3 characters.")
    public void InvalidUsernameLessThan3Character() {

        var exception = assertThrows(IllegalArgumentException.class, () -> Username.Of("wb"));
        Assertions.assertEquals("Username must be at least 3 characters.", exception.getMessage());

    }

    @Test @Tag("Small")
    @DisplayName("Should throw a InvalidArgumentException with message 'Username must be at least 3 characters.' " +
            "when provided a null argument")
    public void InvalidUsernameNullArgument() {

        var exception = assertThrows(IllegalArgumentException.class, () -> Username.Of(null));
        Assertions.assertEquals("Username must be at least 3 characters.", exception.getMessage());

    }
}
