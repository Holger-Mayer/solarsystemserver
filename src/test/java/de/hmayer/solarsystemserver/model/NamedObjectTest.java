package de.hmayer.solarsystemserver.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class NamedObjectTest {

    private NamedObject namedObject;

    @BeforeEach
    void setUp() {
        namedObject = new NamedObject(1, "Test Object");
    }

    @Test
    void SOL_T103_testGetId() {
        assertEquals(1, namedObject.getId(), "ID should be 1");
    }

    @Test
    void SOL_T104_testGetName() {
        assertEquals("Test Object", namedObject.getName(), "Name should be 'Test Object'");
    }

    @Test
    void SOL_T105_testNamedObjectConstructor() {
        NamedObject newNamedObject = new NamedObject(2, "Another Object");
        assertEquals(2, newNamedObject.getId(), "ID should be 2");
        assertEquals("Another Object", newNamedObject.getName(), "Name should be 'Another Object'");
    }
}