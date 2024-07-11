package de.hmayer.solarsystemserver.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PlanetDaoTests {

    private PlanetDao planet;

    @BeforeEach
    void setUp() {
        planet = new PlanetDao("1", "Earth", "12742", "5.972E24", "7.25", "0.0167", "149.6E6", "9.807", "365.25",
                "23.93", "1");
    }

    @Test
    void SOL_T86_testDefaultInitializer() {

        PlanetDao sut = new PlanetDao();

        assertNull(sut.getId());
        assertNull(sut.getName());
        assertNull(sut.getDiameter());
        assertNull(sut.getMass());
        assertNull(sut.getInclination());
        assertNull(sut.getEccentricity());
        assertNull(sut.getSemimajoraxis());
        assertNull(sut.getSurfacegravity());
        assertNull(sut.getOrbitalperiod());
        assertNull(sut.getSiderealrotation());
        assertNull(sut.getSatellites());

    }

   
    @Test
    void SOL_T87_testSetAndGetId() {
        planet.setId("2");
        assertEquals("2", planet.getId(), "ID should be 2 after setting");
    }

 

    @Test
    void SOL_T88_testSetAndGetName() {
        planet.setName("Mars");
        assertEquals("Mars", planet.getName(), "Name should be Mars after setting");
    }

  

    @Test
    void SOL_T89_testSetAndGetDiameter() {
        planet.setDiameter("6792");
        assertEquals("6792", planet.getDiameter(), "Diameter should be 6792 after setting");
    }

   

    @Test
    void SOL_T90_testSetAndGetMass() {
        planet.setMass("6.39E23");
        assertEquals("6.39E23", planet.getMass(), "Mass should be 6.39E23 after setting");
    }

  

    @Test
    void SOL_T91_testSetAndGetInclination() {
        planet.setInclination("1.85");
        assertEquals("1.85", planet.getInclination(), "Inclination should be 1.85 after setting");
    }

 

    @Test
    void SOL_T92_testSetAndGetEccentricity() {
        planet.setEccentricity("0.0934");
        assertEquals("0.0934", planet.getEccentricity(), "Eccentricity should be 0.0934 after setting");
    }

  
    @Test
    void SOL_T93_testSetAndGetSemimajoraxis() {
        planet.setSemimajoraxis("227.9E6");
        assertEquals("227.9E6", planet.getSemimajoraxis(), "Semimajoraxis should be 227.9E6 after setting");
    }

 

    @Test
    void SOL_T94_testSetAndGetSurfacegravity() {
        planet.setSurfacegravity("3.721");
        assertEquals("3.721", planet.getSurfacegravity(), "Surfacegravity should be 3.721 after setting");
    }

    

    @Test
    void SOL_T95_testSetAndGetOrbitalperiod() {
        planet.setOrbitalperiod("687");
        assertEquals("687", planet.getOrbitalperiod(), "Orbitalperiod should be 687 after setting");
    }

  

    @Test
    void SOL_T96_testSetAndGetSiderealrotation() {
        planet.setSiderealrotation("24.62");
        assertEquals("24.62", planet.getSiderealrotation(), "Siderealrotation should be 24.62 after setting");
    }

  

    @Test
    void SOL_T97_testSetAndGetSatellites() {
        planet.setSatellites("2");
        assertEquals("2", planet.getSatellites(), "Satellites should be 2 after setting");
    }

    @Test
    void SOL_T98_testToString() {
        String expectedString = "PlanetDao [id=1, name=Earth, diameter=12742, mass=5.972E24, inclination=7.25, eccentricity=0.0167, semimajoraxis=149.6E6, surfacegravity=9.807, orbitalperiod=365.25, siderealrotation=23.93, satellites=1]";
        assertEquals(expectedString, planet.toString(), "toString method should return the expected string");
    }
}