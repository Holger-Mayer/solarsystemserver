package de.hmayer.solarsystemserver.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.hmayer.solarsystemserver.model.Moon;

class MoonTests {

    @Test
    void InitializeMoon() {
        Moon sut = new Moon();

        assertNull(sut.getId());
        assertNull(sut.getName());
        assertNull(sut.getPlanetId());
        assertNull(sut.getMass());
        assertNull(sut.getRadius());
        assertNull(sut.getDensity());
        assertNull(sut.getMagnitude());
        assertNull(sut.getAlbedo());
    }

    @Test
    void setGetId() {
        Integer testValue = 13;

        Moon sut = new Moon();

        sut.setId(testValue);
        assertEquals(testValue, sut.getId());
    }

    @Test
    void setGetName() {
        String testValue = "Deimos";

        Moon sut = new Moon();

        sut.setName(testValue);
        assertEquals(testValue, sut.getName());
    }

    @Test
    void setGetPlanetId() {
        Integer testValue = 13;

        Moon sut = new Moon();

        sut.setPlanetId(testValue);
        assertEquals(testValue, sut.getPlanetId());
    }

    @Test
    void setGetMass() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setMass(testValue);
        assertEquals(testValue, sut.getMass());
    }

    @Test
    void setGetRadius() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setRadius(testValue);
        assertEquals(testValue, sut.getRadius());
    }

    @Test
    void setGetDensity() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setDensity(testValue);
        assertEquals(testValue, sut.getDensity());
    }

    @Test
    void setGetMagnitude() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setMagnitude(testValue);
        assertEquals(testValue, sut.getMagnitude());
    }

    @Test
    void setGetAlbedo() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setAlbedo(testValue);
        assertEquals(testValue, sut.getAlbedo());
    }

    @Test
    void loadStringList_toShort() {
        List<String> testValues = List.of("1", "Alpha", "10.0");

        Moon sut = new Moon();

        sut.load(testValues);

        assertNull(sut.getId());
        assertEquals("Alpha", sut.getName());
        assertEquals(1, sut.getPlanetId());
        assertEquals("10.0", sut.getMass());
        assertNull(sut.getRadius());
        assertNull(sut.getDensity());
        assertNull(sut.getMagnitude());
        assertNull(sut.getAlbedo());

    }

    @Test
    void loadStringList_OK() {
        List<String> testValues = List.of("5", "Ananke", "0.0020", "14", "2.6", "19.1R", "0.04");

        Moon sut = new Moon();

        sut.load(testValues);

        assertNull(sut.getId());
        assertEquals("Ananke", sut.getName());
        assertEquals(5, sut.getPlanetId());
        assertEquals("0.0020", sut.getMass());
        assertEquals("14", sut.getRadius());
        assertEquals("2.6", sut.getDensity());
        assertEquals("19.1R", sut.getMagnitude());
        assertEquals("0.04", sut.getAlbedo());
    }

    @Test
    void loadStringList_ToLong() {
        List<String> testValues = List.of("5", "Ananke", "0.0020", "14", "2.6", "19.1R", "0.04", "17212");

        Moon sut = new Moon();

        sut.load(testValues);

        assertNull(sut.getId());
        assertEquals("Ananke", sut.getName());
        assertEquals(5, sut.getPlanetId());
        assertEquals("0.0020", sut.getMass());
        assertEquals("14", sut.getRadius());
        assertEquals("2.6", sut.getDensity());
        assertEquals("19.1R", sut.getMagnitude());
        assertEquals("0.04", sut.getAlbedo());
    }

    @Test
    void loadStringList_PlanetID_NotInteger() {
        List<String> testValues = List.of("P5", "Ananke", "0.0020", "14", "2.6", "19.1R", "0.04");

        Moon sut = new Moon();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

        assertNull(sut.getId());
        assertNull(sut.getName());
        assertNull(sut.getPlanetId());
        assertNull(sut.getMass());
        assertNull(sut.getRadius());
        assertNull(sut.getDensity());
        assertNull(sut.getMagnitude());
        assertNull(sut.getAlbedo());

    }
}
