package de.hmayer.solarsystemserver.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;


import de.hmayer.solarsystemserver.model.Planet;

class PlanetTests {
    

     @Test
    void SOL_T6_InitializePlanet() {
        Planet sut = new Planet();

        assertNull(sut.getId());
        assertNull(sut.getName());
        assertNull(sut.getDiameter());
        assertNull(sut.getMass());
        assertNull(sut.getInclination());
        assertNull(sut.getEccentricity());
        assertNull(sut.getSemiMajorAxis());
        assertNull(sut.getSurfaceGravity());
        assertNull(sut.getOrbitalPeriod());
        assertNull(sut.getSiderealRotation());
        assertNull(sut.getSatellites());
  
    }

      @Test
    void SOL_T7_setGetId() {
        Integer testValue = 13;

        Planet sut = new Planet();

        sut.setId(testValue);
        assertEquals(testValue, sut.getId());
    }

    @Test
    void SOL_T8_setGetName() {
        String testValue = "Mars";

        Planet sut = new Planet();

        sut.setName(testValue);
        assertEquals(testValue, sut.getName());
    }


    @Test
    void SOL_T9_setGetDiameter() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setDiameter(testValue);
        assertEquals(testValue, sut.getDiameter());
    }

    @Test
    void SOL_T10_setGetMass() {
        String testValue = "1.0E37";

        Planet sut = new Planet();

        sut.setMass(testValue);
        assertEquals(testValue, sut.getMass());
    }

    @Test
    void SOL_T11_setGetInclination() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setInclination(testValue);
        assertEquals(testValue, sut.getInclination());
    }

    @Test
    void SOL_T12_setGetEccentricity() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setEccentricity(testValue);
        assertEquals(testValue, sut.getEccentricity());
    }

    @Test
    void SOL_T13_setGetSemiMajorAxis() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setSemiMajorAxis(testValue);
        assertEquals(testValue, sut.getSemiMajorAxis());
    }

    @Test
    void SOL_T14_setGetSurfaceGravity() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setSurfaceGravity(testValue);
        assertEquals(testValue, sut.getSurfaceGravity());
    }

    @Test
    void SOL_T15_setGetOrbitalPeriod() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setOrbitalPeriod(testValue);
        assertEquals(testValue, sut.getOrbitalPeriod());
    }

    @Test
    void SOL_T16_setGetSiderealRotation() {
        Double testValue = 2.0;

        Planet sut = new Planet();

        sut.setSiderealRotation(testValue);
        assertEquals(testValue, sut.getSiderealRotation());
    }

    @Test
    void SOL_T17_setGetSatellites() {
        Integer testValue = 3;

        Planet sut = new Planet();

        sut.setSatellites(testValue);
        assertEquals(testValue, sut.getSatellites());
    }

      @Test
    void SOL_T18_loadStringList_toShort() {
        List<String> testValues = List.of("3", "Earth", "12756.3");
     
        Planet sut = new Planet();

        sut.load(testValues);

        assertEquals(3,sut.getId());
        assertEquals("Earth",sut.getName());
        assertEquals(12756.3,sut.getDiameter());
        assertNull(sut.getMass());
        assertNull(sut.getInclination());
        assertNull(sut.getEccentricity());
        assertNull(sut.getSemiMajorAxis());
        assertNull(sut.getSurfaceGravity());
        assertNull(sut.getOrbitalPeriod());
        assertNull(sut.getSiderealRotation());
        assertNull(sut.getSatellites());

    }

    @Test
    void SOL_T19_loadStringList_OK() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        sut.load(testValues);

        assertEquals(5,sut.getId());
        assertEquals("Jupiter",sut.getName());
        assertEquals(142984,sut.getDiameter());
        assertEquals("1.899×10^27",sut.getMass());
        assertEquals(1.3053,sut.getInclination());
        assertEquals(0.04838624,sut.getEccentricity());
        assertEquals(5.202887,sut.getSemiMajorAxis());
        assertEquals(24.79,sut.getSurfaceGravity());
        assertEquals(11.86,sut.getOrbitalPeriod());
        assertEquals(0.4135,sut.getSiderealRotation());
        assertEquals(0,sut.getSatellites());
    }

    @Test
    void SOL_T_20_loadStringList_ID_NotInteger() {
        List<String> testValues = List.of("A5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_21_loadStringList_Diameter_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "Q142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T22_loadStringList_Inclination_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "W1.3053", "0.04838624", "5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T23_loadStringList_Eccentricity_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "W0.04838624", "5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_24_loadStringList_SemiMajorAxis_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "W5.202887"," 24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_25_loadStringList_SurfaceGravity_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887","W24.79","11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_26_loadStringList_OrbitalPeriod_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887","24.79","W11.86","0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_27_loadStringList_SiderealRotation_NotDouble() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887","24.79","11.86","W0.4135","63");
   
        Planet sut = new Planet();

        assertThrows(
                java.lang.NumberFormatException.class,
                () -> sut.load(testValues),
                "Expected load() to throw, but it didn't");

    }

    @Test
    void SOL_T_28_loadStringList_Satellites_IgnoreNotInteger() {
        List<String> testValues = List.of("5", "Jupiter", "142984", "1.899×10^27", "1.3053", "0.04838624", "5.202887","24.79","11.86","0.4135","W63");
   
        Planet sut = new Planet();

        sut.load(testValues);
    
        assertEquals(5,sut.getId());
        assertEquals("Jupiter",sut.getName());
        assertEquals(142984,sut.getDiameter());
        assertEquals("1.899×10^27",sut.getMass());
        assertEquals(1.3053,sut.getInclination());
        assertEquals(0.04838624,sut.getEccentricity());
        assertEquals(5.202887,sut.getSemiMajorAxis());
        assertEquals(24.79,sut.getSurfaceGravity());
        assertEquals(11.86,sut.getOrbitalPeriod());
        assertEquals(0.4135,sut.getSiderealRotation());
        assertEquals(0,sut.getSatellites());
    }
}
