package de.hmayer.solarsystemserver.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import de.hmayer.solarsystemserver.model.Repository;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.Moon;

class MoonRepositoryTests {
    
    @Test
    void initTest() {

        MoonRepository sut = new MoonRepository();

        assertEquals(177,sut.findAll().size());
    }



    @Test void findMoonByIdTest_successfull() {

        MoonRepository sut = new MoonRepository();

        Optional<Moon> result =  sut.findById(2);

        assertTrue(result.isPresent());
        Moon mResult = result.get();

        assertEquals("Deimos",mResult.getName());
        assertEquals(2,mResult.getId());
    }


    @Test void findMoonByIdTest_notFound() {

        Repository<Moon> sut = new Repository<Moon>("Moons.csv",moondata -> {
            Moon moon = new Moon();
            moon.load(moondata);
            return moon;
        });
        Optional<Moon> result =  sut.findById(11001);

        assertFalse(result.isPresent());
       
    }

    @Test void findMoonByPlanetIdTest_PlanetnotFound() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(11001);

        assertEquals(0,result.size());
    }

    @Test void findMoonByPlanetIdTest_NoMoons() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(2);

        assertEquals(0,result.size());
    }

    @Test void findMoonByPlanetIdTest_MoonsFound() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(4);

        assertEquals(2,result.size());
        assertEquals(1,result.stream().filter(m -> "Deimos".equals(m.getName())).toList().size());
        assertEquals(1,result.stream().filter(m -> "Phobos".equals(m.getName())).toList().size());
    }


    @Test
    void deleteMoonByIDTests_wrongID_NothingDeleted() {

        MoonRepository sut = new MoonRepository();

        sut.delete(12938);

        assertEquals(177,sut.findAll().size());
    }

    @Test
    void deleteMoonByIDTests_correctID_Deleted() {

        MoonRepository sut = new MoonRepository();

        Optional<Moon> moon = sut.findById(9);

        sut.delete(9);

        assertEquals(176, sut.findAll().size());
        assertFalse(sut.findById(9).isPresent());

        sut.add(moon.get());

    }

    @Test
    void deleteMoonsFromPlanet_correct_Deleted() {

        MoonRepository sut = new MoonRepository();

        sut.deleteByPlanetId(4);
        assertEquals(175, sut.findAll().size());
        assertEquals(0,sut.findByPlanetId(4).size());
    }
}

