package de.hmayer.solarsystemserver.controller.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.hmayer.solarsystemserver.model.Moon;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.Planet;
import de.hmayer.solarsystemserver.model.PlanetRepository;


 // We need this to enable post and delete delete repsonses. Else ther will be http error 403
@WebMvcTest(SolarSystemRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SolarSystemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private MoonRepository moonRepository;

    private Planet planet1;
    private Planet planet2;
    private Moon moon1;
    private Moon moon2;

    @BeforeEach
    void setUp() {
        planet1 = new Planet(1, "Earth",1.0);
        planet2 = new Planet(2, "Mars",0.8);
        moon1 = new Moon(1, "Moon", 1);
        moon2 = new Moon(2, "Phobos", 2);

        Mockito.when(planetRepository.findAll()).thenReturn(Arrays.asList(planet1, planet2));
        Mockito.when(planetRepository.findById(1)).thenReturn(Optional.of(planet1));
        Mockito.when(planetRepository.findById(2)).thenReturn(Optional.of(planet2));
        Mockito.when(moonRepository.findByPlanetId(1)).thenReturn(Arrays.asList(moon1));
        Mockito.when(moonRepository.findById(1)).thenReturn(Optional.of(moon1));
        Mockito.when(moonRepository.findById(2)).thenReturn(Optional.of(moon2));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T138_testGetPlanets() throws Exception {
        mockMvc.perform(get("/api/planets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Earth")))
                .andExpect(jsonPath("$[1].name", is("Mars")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T139_testGetPlanetById() throws Exception {
        mockMvc.perform(get("/api/planets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Earth")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T140_testGetPlanetById_NotFound() throws Exception {
        mockMvc.perform(get("/api/planets/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T141_testCreatePlanet() throws Exception {
  
        mockMvc.perform(post("/api/planets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"Venus\",\"diameter\":1.0}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/planets/3")))
                .andExpect(jsonPath("$.name", is("Venus")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T142_testDeletePlanet() throws Exception {
        mockMvc.perform(delete("/api/planets/1"))
        .andExpect(status().is2xxSuccessful());
      
        Mockito.verify(planetRepository).delete(1);
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T143_testGetMoonsByPlanetId() throws Exception {
        mockMvc.perform(get("/api/moons/planet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Moon")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T144_testGetMoonById() throws Exception {
        mockMvc.perform(get("/api/moons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Moon")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T145_testGetMoonById_NotFound() throws Exception {
        mockMvc.perform(get("/api/moons/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Not yet discovered")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T146_testCreateMoon() throws Exception {
       
       
        mockMvc.perform(post("/api/moons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"Deimos\",\"planetId\":2}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/moons/3")))
                .andExpect(jsonPath("$.name", is("Deimos")));
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T147_testDeleteMoon() throws Exception {
        mockMvc.perform(delete("/api/moons/1"))
                .andExpect(status().isOk());

        Mockito.verify(moonRepository).delete(1);
    }

    @Test
    @WithMockUser(username = "hugo", password = "12345", roles = "USER")
    void SOL_T148_testReset() throws Exception {
        mockMvc.perform(get("/api/test/reset"))
                .andExpect(status().isOk());

        Mockito.verify(planetRepository).reload();
        Mockito.verify(moonRepository).reload();
    }
}