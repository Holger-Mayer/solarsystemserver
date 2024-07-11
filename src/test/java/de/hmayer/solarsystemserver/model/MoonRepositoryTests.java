package de.hmayer.solarsystemserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;


class MoonRepositoryTests {
    
    @Test
    void SOL_T38_initTest() {

        MoonRepository sut = new MoonRepository();

        assertEquals(177,sut.findAll().size());
    }



    @Test void SOL_T39_findMoonByIdTest_successfull() {

        MoonRepository sut = new MoonRepository();

        Optional<Moon> result =  sut.findById(2);

        assertTrue(result.isPresent());
        Moon mResult = result.get();

        assertEquals("Deimos",mResult.getName());
        assertEquals(2,mResult.getId());
    }


    @Test void SOL_T40_findMoonByIdTest_notFound() {

        Repository<Moon> sut = new Repository<Moon>("Moons.csv",moondata -> {
            Moon moon = new Moon();
            moon.load(moondata);
            return moon;
        });
        Optional<Moon> result =  sut.findById(11001);

        assertFalse(result.isPresent());
       
    }

    @Test void SOL_T41_findMoonByPlanetIdTest_PlanetnotFound() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(11001);

        assertEquals(0,result.size());
    }

    @Test void SOL_T42_findMoonByPlanetIdTest_NoMoons() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(2);

        assertEquals(0,result.size());
    }

    @Test void SOL_T43_findMoonByPlanetIdTest_MoonsFound() {

        MoonRepository sut = new MoonRepository();

        List<Moon> result =  sut.findByPlanetId(4);

        assertEquals(2,result.size());
        assertEquals(1,result.stream().filter(m -> "Deimos".equals(m.getName())).toList().size());
        assertEquals(1,result.stream().filter(m -> "Phobos".equals(m.getName())).toList().size());
    }


    @Test
    void SOL_T44_deleteMoonByIDTests_wrongID_NothingDeleted() {

        MoonRepository sut = new MoonRepository();

        sut.delete(12938);

        assertEquals(177,sut.findAll().size());
    }

    @Test
    void SOL_T45_deleteMoonByIDTests_correctID_Deleted() {

        MoonRepository sut = new MoonRepository();

        Optional<Moon> moon = sut.findById(9);

        sut.delete(9);

        assertEquals(176, sut.findAll().size());
        assertFalse(sut.findById(9).isPresent());

        sut.add(moon.get());

    }

    @Test
    void SOL_T46_deleteMoonsFromPlanet_correct_Deleted() {

        MoonRepository sut = new MoonRepository();

        sut.deleteByPlanetId(4);
        assertEquals(175, sut.findAll().size());
        assertEquals(0,sut.findByPlanetId(4).size());
    }

    @Test
    void SOL_T47_deleteMoonsFromPlanet_invalid_Planetid() {

        MoonRepository sut = new MoonRepository();

        sut.deleteByPlanetId(42365);
        assertEquals(177, sut.findAll().size());
   
    }

    @Test
    void SOL_T99_deleteAllMoons() {

        MoonRepository sut = new MoonRepository();

        sut.deleteAll();

        assertEquals(0, sut.findAll().size());
    }


    @Test
    void SOL_T100_reloadMoons() {

        MoonRepository sut = new MoonRepository();

        sut.deleteAll();

        sut.reload();

        assertEquals(177, sut.findAll().size());
    }

}

