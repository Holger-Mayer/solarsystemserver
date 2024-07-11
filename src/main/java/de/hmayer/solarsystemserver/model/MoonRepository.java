package de.hmayer.solarsystemserver.model;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Autoloadable class for a moon repository
 * 
 * @author Holger Mayer
 */

@Component("moonrepository")
public class MoonRepository extends Repository<Moon> {

       public MoonRepository() {
        super("Moons.csv", moondata -> {
            Moon moon = new Moon();
            moon.load(moondata);
            return moon;
        });

        // We need to setup the unique id's after loading

        Integer index = 0;
        for (Moon moon : findAll()) {
            moon.setId(index++);

        }

    }

    public void reload(){

        super.reload("Moons.csv", moondata -> {
            Moon moon = new Moon();
            moon.load(moondata);
            return moon;
        });

          // We need to setup the unique id's after loading

          Integer index = 0;
          for (Moon moon : findAll()) {
              moon.setId(index++);
              
          }
    }

    /**
     * Additional find function collecting all moons for a given planet
     * 
     * @param id the id of the planet
     * @return a list of moons for the given planet - empty if nomoon exists
     */
    public List<Moon> findByPlanetId(Integer id) {
        return this.findAll()
                .stream()
                .filter(m -> id.equals(m.getPlanetId()))
                .toList();
    }

    /**
     * Addition delete function to delete all moons for a given planet
     * 
     * @param id the id of the planet
     */
    public void deleteByPlanetId(Integer id) {

        List<Moon> moonsToDelete = findByPlanetId(id);

        for (Moon moon : moonsToDelete) {
            this.delete(moon.getId());
        }
    }
}
