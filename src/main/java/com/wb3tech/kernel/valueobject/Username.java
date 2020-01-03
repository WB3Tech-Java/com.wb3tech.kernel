package com.wb3tech.kernel.valueobject;

import com.wb3tech.kernel.ValueObject;

public class Username extends ValueObject {

    public String value;

    Username(String value) {
        this.value = this.applyStringDefaults(value);
        if (this.value.length() < 3) { throw new IllegalArgumentException("Username must be at least 3 characters."); }
    }

    public static Username Of(String value) {
        return new Username(value);
    }

    public String toString() {
        return this.value;
    }
}
