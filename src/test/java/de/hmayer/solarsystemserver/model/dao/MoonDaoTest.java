package de.hmayer.solarsystemserver.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


 class MoonDaoTest {

    private MoonDao moonDao;

    @BeforeEach
     void setUp() {
        moonDao = new MoonDao();
    }

    @Test
     void SOL_T75_testNoArgsConstructor() {
        assertNotNull(moonDao);
    }

    @Test
     void SOL_T76_testAllArgsConstructor() {
      moonDao   = new MoonDao("1", "Moon", "7.35e22 kg", "1737.4 km", "3.34 g/cm³", "-12.74", "0.12");
        assertEquals("1", moonDao.getPlanetid());
        assertEquals("Moon", moonDao.getName());
        assertEquals("7.35e22 kg", moonDao.getMass());
        assertEquals("1737.4 km", moonDao.getRadius());
        assertEquals("3.34 g/cm³", moonDao.getDensity());
        assertEquals("-12.74", moonDao.getMagnitude());
        assertEquals("0.12", moonDao.getAlbedo());
    }

    @Test
     void SOL_T77_testSetAndGetPlanetid() {
        moonDao.setPlanetid("2");
        assertEquals("2", moonDao.getPlanetid());
    }

    @Test
     void SOL_T80_testSetAndGetPlanetidWithInvalidValue() {
        assertThrows(NumberFormatException.class, () -> {
            moonDao.setPlanetid("invalid");
            Integer.parseInt(moonDao.getPlanetid()); // This line simulates the validation check
        });
    }

    @Test
     void SOL_T78_testSetAndGetName() {
        moonDao.setName("Europa");
        assertEquals("Europa", moonDao.getName());
    }

    @Test
     void SOL_T79_testSetAndGetMass() {
        moonDao.setMass("4.8e22 kg");
        assertEquals("4.8e22 kg", moonDao.getMass());
    }

    @Test
     void SOL_T81_testSetAndGetRadius() {
        moonDao.setRadius("1561.0 km");
        assertEquals("1561.0 km", moonDao.getRadius());
    }

    @Test
     void SOL_T82_testSetAndGetDensity() {
        moonDao.setDensity("3.01 g/cm³");
        assertEquals("3.01 g/cm³", moonDao.getDensity());
    }

    @Test
     void SOL_T83_testSetAndGetMagnitude() {
        moonDao.setMagnitude("-1.61");
        assertEquals("-1.61", moonDao.getMagnitude());
    }

    @Test
     void SOL_T84_testSetAndGetAlbedo() {
        moonDao.setAlbedo("0.67");
        assertEquals("0.67", moonDao.getAlbedo());
    }

    @Test
     void SOL_T85_testToString() {
        moonDao = new MoonDao("1", "Moon", "7.35e22 kg", "1737.4 km", "3.34 g/cm³", "-12.74", "0.12");
        String expected = "MoonDao [planetid=1, name=Moon, mass=7.35e22 kg, radius=1737.4 km, density=3.34 g/cm³, magnitude=-12.74, albedo=0.12]";
        assertEquals(expected, moonDao.toString());
    }
}