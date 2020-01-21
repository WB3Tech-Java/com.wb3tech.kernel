package com.wb3tech.kernel.entity;

import java.util.UUID;

/***
 * Forms the basis for any identifiable entity for the WB3Tech kernel.
 */
public class Identity {

    private UUID id;

    Identity(UUID id) {
        if (id == null) { throw new NullPointerException("Identity UUID cannot be null."); }
        this.id = id;
    }

    /***
     * Returns new instance with a value of 00000000-0000-0000-0000-000000000000.
     * @return Identity
     */
    public static Identity Default() {
        return new Identity(new UUID(0,0));
    }

    /***
     * Returns new instance with a random value.
     * @return Identity
     */
    public static Identity New() {
        return new Identity(UUID.randomUUID());
    }

    /***
     * Returns instance with a supplied UUID.
     * @param id UUID
     * @return Identity
     */
    public static Identity New(UUID id) {
        return new Identity(id);
    }

    public boolean isValid()  {
        return !this.id.toString().equals("00000000-0000-0000-0000-000000000000");
    }

    public boolean isInvalid()  {
        return !this.isValid();
    }

    public boolean equals(Identity identity) {
        if (this.isInvalid()) { throw new IllegalStateException("Cannot compare, current instance is in invalid."); }
        if (identity.isInvalid()) { throw new IllegalStateException("Cannot compare, instance being compared is invalid."); }
        return this.id.toString().equals(identity.toString());
    }

    public String toString() {
        return this.id.toString();
    }

    public UUID value() {
        return this.id;
    }

    public Identity copy() {
        return new Identity(this.id);
    }

}
