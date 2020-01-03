package com.wb3tech.kernel;

public abstract class ValueObject {

    protected String applyStringDefaults(String string) {
        return  string == null ? "" : string.trim();
    }

}
