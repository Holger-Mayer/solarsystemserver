package de.hmayer.solarsystemserver.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hmayer.solarsystemserver.model.Moon;
import de.hmayer.solarsystemserver.model.MoonRepository;
import de.hmayer.solarsystemserver.model.Planet;
import de.hmayer.solarsystemserver.model.PlanetRepository;
import de.hmayer.solarsystemserver.model.dao.MoonDao;
import de.hmayer.solarsystemserver.model.dao.PlanetDao;

@Controller
public class SolarSystemController {

    private static final Logger logger = LoggerFactory.getLogger(SolarSystemController.class);

    private static final String HOME_PATH = "/home";
    private PlanetRepository planetRepository;
    private MoonRepository moonRepository;

    public SolarSystemController(PlanetRepository planetRepository, MoonRepository moonRepository) {
        this.planetRepository = planetRepository;
        this.moonRepository = moonRepository;
        logger.debug("Start SolarSystemController");
    }

    @GetMapping(HOME_PATH)
    public String getSolarSystemPlanets(Model model) {
        model.addAttribute("planets", planetRepository.findAll());
        return "index.html";
    }

    @GetMapping("/planets/{id}")
    public String getPlanet(@PathVariable("id") Integer id, Model model) {

        Optional<Planet> planet = planetRepository.findById(id);

        if (planet.isPresent()) {

            List<Moon> moons = moonRepository.findByPlanetId(planet.get().getId());

            model.addAttribute("planet", planet.get());
            model.addAttribute("moons", moons);
            return "planet.html";
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found");
    }

    @GetMapping("/moons/{id}")
    public String getMoon(@PathVariable("id") Integer id, Model model) {

        Optional<Moon> moon = moonRepository.findById(id);

        if (moon.isPresent()) {

            model.addAttribute("moon", moon.get());
            return "moon.html";
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found");
    }

    @GetMapping("/addmoon/{id}")
    public String addMoonPrepareForm(@PathVariable("id") Integer id, Model model) {

        Optional<Planet> planet = planetRepository.findById(id);

        if (planet.isPresent()) {

            model.addAttribute("planet", planet.get());

            MoonDao newMoon = new MoonDao();
            newMoon.setPlanetid(planet.get().getId().toString());
               logger.debug(newMoon.toString());
            model.addAttribute("moondao", newMoon);

            return "add_moon_form.html";
        }
 
          throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "entity not found");
    }

    @PostMapping("/addmoon")
    public String addMoonProcessForm(@ModelAttribute("moondao") MoonDao moonDao, RedirectAttributes redirectAttributes,
            Model model) {

        if (moonDao.getName().trim().length() <= 0) {
            Integer planetID = Integer.parseInt(moonDao.getPlanetid());
            Optional<Planet> planet = planetRepository.findById(planetID);
            if (planet.isPresent()) {
                model.addAttribute("planet", planet.get());
                model.addAttribute("moondao", moonDao);
                return "add_moon_form.html";
            }

            model.addAttribute("planets", planetRepository.findAll());
            return "index.html";
        }
        logger.debug(moonDao.toString());

        Moon newMoon = new Moon(moonDao);

        Optional<Integer> maxMoon = moonRepository.findAll().stream().map(Moon::getId).reduce(Integer::max);

        Integer maxId = 1;

        if (maxMoon.isPresent()) {
            maxId = maxMoon.get() + 1;
        }
        newMoon.setId(maxId);
        moonRepository.add(newMoon);

        return "redirect:/planets/" + moonDao.getPlanetid();
    }

    @GetMapping("/addplanet")
    public String addPlanetForm(Model model) {

        model.addAttribute("planet", new PlanetDao());
        return "add_planet_form.html";
    }

    @PostMapping("/addplanet")
    public String addPlanetFormResult(@ModelAttribute("planet") PlanetDao planetDao, Model model) {


        try {
            Planet planet = new Planet(planetDao);
            planetRepository.add(planet);
    
            if (planet.getName().trim().length() <= 0) {
                model.addAttribute("planet", planetDao);
                return "add_planet_form.html";
            }
    
            } catch (Exception e) {
                model.addAttribute("planet", planetDao);
                return "add_planet_form.html";
            }

    
        model.addAttribute("planets", planetRepository.findAll());
        return "index.html";
    }

    @GetMapping("/delete_planet/{id}")
    public String deletePlanet(@PathVariable("id") Integer id, Model model) {
        Optional<Planet> planet = planetRepository.findById(id);
    
        if (planet.isPresent()){
            moonRepository.deleteByPlanetId(id);
            planetRepository.delete(id);
        }

        model.addAttribute("planets", planetRepository.findAll());
        return "redirect:/home";
    }

    @GetMapping("/delete_moon/{id}")
    public String deleteMoon(@PathVariable("id") Integer id, Model model) {

        Optional<Moon> moon = moonRepository.findById(id);
    
        if (moon.isPresent()){
            Integer planetID = moon.get().getPlanetId();
            moonRepository.delete(id);

            return "redirect:/planets/" + planetID;
        }

        model.addAttribute("planets", planetRepository.findAll());
        return "index.html";
    }
}
