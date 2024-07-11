package de.hmayer.solarsystemserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import de.hmayer.solarsystemserver.model.Moon;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.Planet;
import de.hmayer.solarsystemserver.model.PlanetRepository;
import de.hmayer.solarsystemserver.model.dao.MoonDao;
import de.hmayer.solarsystemserver.model.dao.PlanetDao;


@WebMvcTest(SolarSystemController.class)
@AutoConfigureMockMvc(addFilters = false)
 class SolarSystemControllerTests {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private PlanetRepository planetRepository;

    @MockBean
	private MoonRepository moonRepository;


    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T119_testGetSolarSystemPlanets() throws Exception {
        Planet planet = new Planet();
        planet.setId(1);
        planet.setName("Earth");

        when(planetRepository.findAll()).thenReturn(Arrays.asList(planet));

        mockMvc.perform(get("/home"))
            .andExpect(status().isOk())
            .andExpect(view().name("index.html"))
            .andExpect(model().attributeExists("planets"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T120_testGetPlanet() throws Exception {
        Planet planet = new Planet();
        planet.setId(1);
        planet.setName("Earth");
        Moon moon = new Moon();
        moon.setId(1);
        moon.setName("Moon");
        moon.setPlanetId(1);

        when(planetRepository.findById(anyInt())).thenReturn(Optional.of(planet));
        when(moonRepository.findByPlanetId(anyInt())).thenReturn(Arrays.asList(moon));

        mockMvc.perform(get("/planets/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("planet.html"))
            .andExpect(model().attributeExists("planet"))
            .andExpect(model().attributeExists("moons"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T121_testGetPlanet_NotFound() throws Exception {
        when(planetRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/planets/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T122_testAddPlanetForm() throws Exception {
        mockMvc.perform(get("/addplanet"))
            .andExpect(status().isOk())
            .andExpect(view().name("add_planet_form.html"))
            .andExpect(model().attributeExists("planet"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T123_testAddPlanetFormResult() throws Exception {
        PlanetDao planetDao = new PlanetDao("1", "Planet XXA", "12742", "5.972E24", "7.25", "0.0167", "149.6E6", "9.807", "365.25",
        "23.93", "1");
    
        mockMvc.perform(post("/addplanet")
            .flashAttr("planet", planetDao))
            .andExpect(status().isOk())
            .andExpect(view().name("index.html"))
            .andExpect(model().attributeExists("planets"));
    }


    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T124_testAddPlanetFormResult_EmptyName() throws Exception {
        PlanetDao planetDao = new PlanetDao("1", "     ", "12742", "5.972E24", "7.25", "0.0167", "149.6E6", "9.807", "365.25",
        "23.93", "1");
    
        mockMvc.perform(post("/addplanet")
            .flashAttr("planet", planetDao))
            .andExpect(status().isOk())
            .andExpect(view().name("add_planet_form.html"))
            .andExpect(model().attributeExists("planet"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T125_testAddPlanetFormResult_IdNotANumber() throws Exception {
        PlanetDao planetDao = new PlanetDao("A34", "Hugo", "12742", "5.972E24", "7.25", "0.0167", "149.6E6", "9.807", "365.25",
        "23.93", "1");
    
        mockMvc.perform(post("/addplanet")
            .flashAttr("planet", planetDao))
            .andExpect(status().isOk())
            .andExpect(view().name("add_planet_form.html"))
            .andExpect(model().attributeExists("planet"));
    }


    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T126_testDeletePlanet() throws Exception {
        Planet planet = new Planet();
        planet.setId(1);
        planet.setName("Earth");

        when(planetRepository.findById(anyInt())).thenReturn(Optional.of(planet));

        mockMvc.perform(get("/delete_planet/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T127_testDeleteNontExistingPlanet() throws Exception {
   
        when(planetRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/delete_planet/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T128_testDeleteMoon() throws Exception {
        Moon moon = new Moon();
        moon.setId(1);
        moon.setName("Moon");
        moon.setPlanetId(1);

        when(moonRepository.findById(anyInt())).thenReturn(Optional.of(moon));

        mockMvc.perform(get("/delete_moon/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/planets/1"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T129_testDeleteNotExistingMoon() throws Exception {
        Moon moon = new Moon();
       

        when(moonRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/delete_moon/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("index.html"));
    }

   
    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T130_testAddMoonForm() throws Exception {
        Optional<Planet> planet =  Optional.of(new Planet(1, "Planet XXA", 12742.0));

        when(planetRepository.findById(1)).thenReturn(planet);


        mockMvc.perform(get("/addmoon/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("add_moon_form.html"))
            .andExpect(model().attributeExists("planet"))
            .andExpect(model().attributeExists("moondao"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T131_testAddMoonForm_PlanetNotExiting() throws Exception {
      

        when(planetRepository.findById(1)).thenReturn(Optional.empty());


        mockMvc.perform(get("/addmoon/1"))
        .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T132_testGetMoon_NotFound() throws Exception {
        when(moonRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/moons/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T133_testGetMoon() throws Exception {
        Moon moon = new Moon();
        moon.setId(1);
        moon.setName("Luna");
        moon.setPlanetId(1);

        Optional<Moon> moonOpt = Optional.of(moon);

         when(moonRepository.findById(1)).thenReturn(moonOpt);

        mockMvc.perform(get("/moons/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("moon.html"))
            .andExpect(model().attributeExists("moon"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T134_testAddMoonFormResult() throws Exception {
        Optional<Planet> planet =  Optional.of(new Planet(1, "Planet XXA", 12742.0));

        when(planetRepository.findById(1)).thenReturn(planet);

        MoonDao moonDao = new MoonDao("1", "Luna", "1.0", "2.0", "3.0", "4.0", "5.0");
       
        mockMvc.perform(post("/addmoon")
            .flashAttr("moondao", moonDao))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/planets/1"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T135_testAddMoonFormResult_NoName() throws Exception {
        Optional<Planet> planet =  Optional.of(new Planet(1, "Planet XXA", 12742.0));

        when(planetRepository.findById(1)).thenReturn(planet);

        MoonDao moonDao = new MoonDao("1", "    ", "1.0", "2.0", "3.0", "4.0", "5.0");
       
        mockMvc.perform(post("/addmoon")
            .flashAttr("moondao", moonDao))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("moondao"))
            .andExpect(model().attributeExists("planet"))
            .andExpect(view().name("add_moon_form.html"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T136_testAddMoonFormResult_NoName_noPlanet() throws Exception {
       

        when(planetRepository.findById(1)).thenReturn(Optional.empty());

        MoonDao moonDao = new MoonDao("1", "    ", "1.0", "2.0", "3.0", "4.0", "5.0");
       
        mockMvc.perform(post("/addmoon")
            .flashAttr("moondao", moonDao))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("planets"))
            .andExpect(view().name("index.html"));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
     void SOL_T137_testAddMoonFormResult_NotFirstMoon() throws Exception {
        Optional<Planet> planet =  Optional.of(new Planet(1, "Planet XXA", 12742.0));

        List<Moon> moonList =  new ArrayList<>();
        Moon moon = new Moon(1, "First Moon", 1);
        moonList.add(moon);

        when (moonRepository.findAll()).thenReturn(moonList);
        when(planetRepository.findById(1)).thenReturn(planet);

        MoonDao moonDao = new MoonDao("1", "Luna", "1.0", "2.0", "3.0", "4.0", "5.0");
       
        mockMvc.perform(post("/addmoon")
            .flashAttr("moondao", moonDao))
                 .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/planets/1"));
    }


}
