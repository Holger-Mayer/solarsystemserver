package de.hmayer.solarsystemserver.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SolarSystemController {
    

    @GetMapping("/home")
	public String getSolarSystemPlanets() {
		
		return "/index.html";
	}
}
