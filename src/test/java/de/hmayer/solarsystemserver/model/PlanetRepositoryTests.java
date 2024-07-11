package de.hmayer.solarsystemserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;


class PlanetRepositoryTests {

    @Test
    void SOL_T33_initTest() {

        PlanetRepository sut = new PlanetRepository();

        assertEquals(9, sut.findAll().size());
    }

    @Test
    void SOL_T34_findPlanetByIdTest_successfull() {

        PlanetRepository sut = new PlanetRepository();

        Optional<Planet> result = sut.findById(3);

        assertTrue(result.isPresent());
        Planet pResult = result.get();

        assertEquals("Earth", pResult.getName());
        assertEquals(3, pResult.getId());
    }

    @Test
    void SOL_T35_findPlanetByIdTest_notFound() {

        PlanetRepository sut = new PlanetRepository();

        Optional<Planet> result = sut.findById(11);

        assertFalse(result.isPresent());

    }

    @Test
    void SOL_T36_deletePlanetByIDTests_wrongID_NothingDeleted() {

        PlanetRepository sut = new PlanetRepository();

        sut.delete(12938);

        assertEquals(9, sut.findAll().size());
    }

    @Test
    void SOL_T37_deletePlanetByIDTests_correctID_Deleted() {

        PlanetRepository sut = new PlanetRepository();

        Optional<Planet> planet = sut.findById(9);

        sut.delete(9);

        assertEquals(8, sut.findAll().size());
        assertFalse(sut.findById(9).isPresent());

        sut.add(planet.get());

    }

    @Test
    void SOL_T106_deleteAllPlanets() {

        PlanetRepository sut = new PlanetRepository();

        sut.deleteAll();

        assertEquals(0, sut.findAll().size());
    }


    @Test
    void SOL_T107_reloadPlanets() {

        PlanetRepository sut = new PlanetRepository();

        sut.deleteAll();

        sut.reload();

        assertEquals(9, sut.findAll().size());
    }

}
