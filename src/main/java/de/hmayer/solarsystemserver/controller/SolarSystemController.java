package de.hmayer.solarsystemserver.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import de.hmayer.solarsystemserver.model.Moon;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.Planet;
import de.hmayer.solarsystemserver.model.PlanetRepository;

@Controller
public class SolarSystemController {
    
private PlanetRepository planetRepository;
    private MoonRepository moonRepository;

    public SolarSystemController(PlanetRepository planetRepository, MoonRepository moonRepository) {
        this.planetRepository = planetRepository;
        this.moonRepository = moonRepository;
    }


    @GetMapping("/home")
	public String getSolarSystemPlanets(Model model) {
		model.addAttribute("planets", planetRepository.findAll());
		return "/index.html";
	}

	 @GetMapping("/planets/{id}")
    public String  getPlanet(@PathVariable("id") Integer id, Model model) {

        Optional<Planet> planet = planetRepository.findById(id);

        if (planet.isPresent()) {

            List<Moon> moons = moonRepository.findByPlanetId(planet.get().getId());

			model.addAttribute("planet", planet.get());
            model.addAttribute("moons",moons);
            return "/planet.html";
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found");
    }

    @GetMapping("/moons/{id}")
    public String  getMoon(@PathVariable("id") Integer id, Model model) {

        Optional<Moon> moon = moonRepository.findById(id);

        if (moon.isPresent()) {

			model.addAttribute("moon", moon.get());
            return "/moon.html";
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found");
    }
}
