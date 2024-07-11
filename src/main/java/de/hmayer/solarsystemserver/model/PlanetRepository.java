package de.hmayer.solarsystemserver.model;


import org.springframework.stereotype.Component;

/**
 * Autoloadable class for a planet repository
 * 
 * @author Holger Mayer
 */
@Component("planetrepository")
public class PlanetRepository extends Repository<Planet> {


    public PlanetRepository() {
        super("Planets.csv", planetdata -> {
            Planet planet = new Planet();
            planet.load(planetdata);
            return planet;
        });

    }

    public void reload() {

        super.reload("Planets.csv",  planetdata -> {
                Planet planet = new Planet();
                planet.load(planetdata);
                return planet;
            });
    }

}
