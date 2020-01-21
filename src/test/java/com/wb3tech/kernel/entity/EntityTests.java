package com.wb3tech.kernel.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kernel - Entity")
public class EntityTests {

    @Test
    @Tag("Small")
    @DisplayName("Entity should have a valid Identity.")
    public void ContainsValidIdentity() {
        var entity = new ExampleEntity();
        entity.setValidIdentity();
        Assertions.assertTrue(entity.getId().isValid());
    }

    @Test
    @Tag("Small")
    @DisplayName("When a null Identity is provided, the entity should throw a NullPointerException with the message 'The identity is null.' .")
    public void IdentityThrowNullPointerException() {

        var exception = assertThrows(NullPointerException.class, () -> ExampleEntity.Of(null));
        Assertions.assertEquals("The identity is null.", exception.getMessage());
    }

    @Test
    @Tag("Small")
    @DisplayName("When an invalid Identity is provided, the entity should throw a IllegalStateException with the message 'The identity is not valid.' .")
    public void IdentityThrowIllegalStateException() {

        var exception = assertThrows(IllegalStateException.class, () -> ExampleEntity.Of(Identity.Default()));
        Assertions.assertEquals("The identity is not valid.", exception.getMessage());

    }

    @Test
    @Tag("Small")
    @DisplayName("When an valid Identity is provided, should be able to get a copy of the identity .")
    public void GetCopyOfEntityIdentity() {

        var identity = Identity.New();
        var entity = new ExampleEntity();
        entity.setIdentity(identity);

        Assertions.assertEquals(identity.toString(), entity.getId().toString());
        Assertions.assertNotEquals(identity.hashCode(), entity.getId().hashCode());

    }

}

class ExampleEntity extends Entity {

    public void setValidIdentity() {
        this.assignNewIdentity();
    }

    public void setIdentity(Identity id) {
        this.setId(id);
    }

    public static Entity Of(Identity identity) {
       var entity = new ExampleEntity();
       entity.setIdentity(identity);
       return entity;
    }

}
