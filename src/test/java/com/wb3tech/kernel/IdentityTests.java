package com.wb3tech.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kernel - Identity ")
public class IdentityTests {

    private final String defaultUUID = "00000000-0000-0000-0000-000000000000";

    @Test @Tag("Small")
    @DisplayName("Instantiated identity should not be valid.")
    public void DefaultIdentityInvalid() {
        var identity = Identity.Default();
        Assertions.assertTrue(identity.isInvalid());
        Assertions.assertEquals(defaultUUID, identity.toString());
    }

    @Test @Tag("Small")
    @DisplayName("New identity should be valid.")
    public void ValidIdentity() {
        var identity = Identity.New();
        Assertions.assertTrue(identity.isValid());
        Assertions.assertNotEquals(defaultUUID, identity.toString());
    }

    @Test @Tag("Small")
    @DisplayName("Identity built from UUID should be valid.")
    public void ValidIdentityFromUUID() {
        var uuid = UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49");
        var identity = Identity.New(uuid);
        Assertions.assertTrue((identity.isValid()));
        Assertions.assertEquals("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49", identity.toString());
        Assertions.assertEquals(uuid, identity.value());
    }

    @Test @Tag("Small")
    @DisplayName("Identity with default UUID of [00000000-0000-0000-0000-000000000000] should be invalid.")
    public void IdentityWithDefaultInvalid() {
        var identity = Identity.New(UUID.fromString(defaultUUID));
        Assertions.assertTrue(identity.isInvalid());
        Assertions.assertEquals(defaultUUID, identity.toString());
    }

    @Test @Tag("Small")
    @DisplayName("New identity built from a null UUID should throw a NullArgumentException " +
            "with a message ['Identity UUID cannot be null.'] ")
    public void NullUUIDThrowsException() {

        var exception = assertThrows(NullPointerException.class, () -> Identity.New(null));
        Assertions.assertEquals("Identity UUID cannot be null.", exception.getMessage());

    }

    @Test @Tag("Small")
    @DisplayName("Two different identity instances with the same UUIDs should equal.")
    public void TwoInstancesEqual() {
        var id1 = Identity.New(UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
        var id2 = Identity.New(UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
        Assertions.assertTrue(id1.equals(id2));
    }

    @Test @Tag("Small")
    @DisplayName("Two different identity instances with different UUIDs should not equal.")
    public void TwoDifferentInstancesNotEqual() {
        var id1 = Identity.New(UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
        var id2 = Identity.New(UUID.fromString("cbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
        Assertions.assertFalse(id1.equals(id2));
    }

    @Test @Tag("Small")
    @DisplayName("Two different identity instances, first instance being invalid, " +
            "should throw an Illegal State exception " +
            "with a message of ['Cannot compare, current instance is in invalid.']")
    public void ThrowInvalidStateWhenComparingInvalidFirstInstance(){

        var exception = assertThrows(IllegalStateException.class, () -> {
            var id1 = Identity.Default();
            var id2 = Identity.New(UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
            var equals = id1.equals(id2);
        });
        Assertions.assertEquals("Cannot compare, current instance is in invalid.", exception.getMessage());
    }

    @Test @Tag("Small")
    @DisplayName("Two different identity instances, second instance being invalid, " +
            "should throw an IllegalStateException " +
            "with a message of ['Cannot compare, instance being compared is invalid.']")
    public void ThrowInvalidStateWhenComparingInvalidSecondInstance(){
        var exception = assertThrows(IllegalStateException.class, () -> {
            var id1 = Identity.New(UUID.fromString("bbe2ee9e-dda1-4d24-92c2-91e35ea55a49"));
            var id2 = Identity.Default();
            var equals = id1.equals(id2);
        });
        Assertions.assertEquals("Cannot compare, instance being compared is invalid.", exception.getMessage());
    }

    @Test @Tag("Small")
    @DisplayName("An identity copy should have the same toString value " +
            "with a different memory location than the original identity.")
    public void InstanceShouldCopy() {
        var original = Identity.New();
        var copy = original.copy();

        Assertions.assertEquals(original.toString(), copy.toString());
        Assertions.assertNotEquals(original.hashCode(), copy.hashCode());

    }

}
