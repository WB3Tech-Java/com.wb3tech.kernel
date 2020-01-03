package com.wb3tech.kernel;

public abstract class Entity implements Identifiable {

    private Identity id;

    protected void setId(Identity id) {
        if (id == null) { throw new NullPointerException("The identity is null."); }
        if (id.isInvalid()) { throw new IllegalStateException("The identity is not valid."); }
        this.id = id.copy();
    }

    public Identity getId() {
        return this.id.copy();
    }

    protected void assignNewIdentity() {
        this.id = Identity.New();
    }

}
