package de.hmayer.solarsystemserver.controller.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.hmayer.solarsystemserver.model.PlanetRepository;
import jakarta.servlet.http.HttpServletRequest;
import de.hmayer.solarsystemserver.model.Planet;
import de.hmayer.solarsystemserver.model.Moon;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.NamedObject;

@CrossOrigin(origins = { "http://127.0.0.1:5500", "http://localhost:5500","http://localhost:8080" })
@RequestMapping("/api/")
@RestController
public class SolarSystemRestController {

    private PlanetRepository planetRepository;
    private MoonRepository moonRepository;

    public SolarSystemRestController(PlanetRepository planetRepository, MoonRepository moonRepository) {
        this.planetRepository = planetRepository;
        this.moonRepository = moonRepository;
    }

    @GetMapping("/test/reset")
    public String resetData() {

        planetRepository.reload();
        moonRepository.reload();
        return "";
    }

    @GetMapping("/planets")
    public List<NamedObject> getPlanets() {

        return planetRepository.findAll()
                .stream()
                .map(e -> new NamedObject(e.getId(), e.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/planets/{id}")
    public Planet findPlanetByID(@PathVariable("id") Integer id) {

        Optional<Planet> planet = planetRepository.findById(id);

        if (planet.isPresent()) {
            return planet.get();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found");
    }

    @DeleteMapping("/planets/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable("id") Integer id) {
        moonRepository.deleteByPlanetId(id);
        planetRepository.delete(id);
        return new ResponseEntity<>("Planet Deleted", HttpStatus.OK);
    }

    @PostMapping(path = "/planets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet newPlanet, HttpServletRequest request) {
        planetRepository.add(newPlanet);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(newPlanet.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPlanet);
    }

    @GetMapping("/moons/planet/{id}")
    public List<Moon> getMoonsByPlanetId(@PathVariable("id") Integer id) {

        return moonRepository.findByPlanetId(id);
    }

    @GetMapping("/moons/{id}")
    public Moon getMoonsByID(@PathVariable("id") Integer id) {

        Optional<Moon> moon = moonRepository.findById(id);

        if (moon.isPresent()) {
            return moon.get();
        }

        return new Moon(-1, "Not yet discovered", 0);
    }

    @DeleteMapping("/moons/{id}")
    public ResponseEntity<String> deleteMoon(@PathVariable("id") Integer id) {
        moonRepository.delete(id);

        return new ResponseEntity<>("Moon Deleted", HttpStatus.OK);
    }

    @PostMapping(path = "/moons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Moon> createPlanet(@RequestBody Moon newMoon, HttpServletRequest request) {
        moonRepository.add(newMoon);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(newMoon.getId())
                .toUri();
        return ResponseEntity.created(location).body(newMoon);
    }
}
