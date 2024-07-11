package de.hmayer.solarsystemserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;


import de.hmayer.solarsystemserver.model.dao.MoonDao;

class MoonTests {

    @Test
    void SOL_T48_InitializeMoon() {
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
    void SOL_T49_setGetId() {
        Integer testValue = 13;

        Moon sut = new Moon();

        sut.setId(testValue);
        assertEquals(testValue, sut.getId());
    }

    @Test
    void SOL_T50_setGetName() {
        String testValue = "Deimos";

        Moon sut = new Moon();

        sut.setName(testValue);
        assertEquals(testValue, sut.getName());
    }

    @Test
    void SOL_T51_setGetPlanetId() {
        Integer testValue = 13;

        Moon sut = new Moon();

        sut.setPlanetId(testValue);
        assertEquals(testValue, sut.getPlanetId());
    }

    @Test
    void SOL_T52_setGetMass() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setMass(testValue);
        assertEquals(testValue, sut.getMass());
    }

    @Test
    void SOL_T53_setGetRadius() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setRadius(testValue);
        assertEquals(testValue, sut.getRadius());
    }

    @Test
    void SOL_T54_setGetDensity() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setDensity(testValue);
        assertEquals(testValue, sut.getDensity());
    }

    @Test
    void SOL_T55_setGetMagnitude() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setMagnitude(testValue);
        assertEquals(testValue, sut.getMagnitude());
    }

    @Test
    void SOL_T56_setGetAlbedo() {
        String testValue = "1.22e4";

        Moon sut = new Moon();

        sut.setAlbedo(testValue);
        assertEquals(testValue, sut.getAlbedo());
    }

    @Test
    void SOL_T57_loadStringList_toShort() {
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
    void SOL_T58_loadStringList_OK() {
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
    void SOL_T59_loadStringList_ToLong() {
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
    void SOL_T60_loadStringList_PlanetID_NotInteger() {
        List<String> testValues = List.of("P5", "Ananke", "0.0020", "14", "2.6", "19.1R", "0.04");

        Moon sut = new Moon();

        sut.load(testValues);

        assertNull(sut.getId());
        assertEquals("Ananke", sut.getName());
        assertEquals(0, sut.getPlanetId());
        assertEquals("0.0020", sut.getMass());
        assertEquals("14", sut.getRadius());
        assertEquals("2.6", sut.getDensity());
        assertEquals("19.1R", sut.getMagnitude());
        assertEquals("0.04", sut.getAlbedo());

    }

    @Test
    void SOL_T101_initializeFromConvenience() {

        Moon sut = new Moon(1, "Moon2", 3);
        assertEquals(1,sut.getId());
        assertEquals("Moon2", sut.getName());
        assertEquals(3, sut.getPlanetId());
        assertNull( sut.getMass());
        assertNull(sut.getRadius());
        assertNull(sut.getDensity());
        assertNull(sut.getMagnitude());
        assertNull(sut.getAlbedo());
    }

    @Test
    void SOL_T102_initializeFromValidDao() {

        MoonDao dao = new MoonDao("1", "Moon1", "1.0", "2.0", "3.0", "4.0", "5.0");

        Moon sut = new Moon(dao);

        assertNull(sut.getId());
        assertEquals("Moon1", sut.getName());
        assertEquals(1, sut.getPlanetId());
        assertEquals("1.0", sut.getMass());
        assertEquals("2.0", sut.getRadius());
        assertEquals("3.0", sut.getDensity());
        assertEquals("4.0", sut.getMagnitude());
        assertEquals("5.0", sut.getAlbedo());
    }

}
