package de.hmayer.solarsystemserver.model;

import java.util.List;

import de.hmayer.solarsystemserver.model.dao.MoonDao;

/**
 * The Moon class describes core parameter of a natural satellite of a planet.
 * The data can be loaded from an array of string.
 * 
 * The Moon class is identifiable so each moon has a unique id;
 * 
 * @author Holger Mayer
 */
public class Moon implements Identifiable {
    private Integer id;
    private String name;
    private Integer planetId;
    private String mass;
    private String radius;
    private String density;
    private String magnitude;
    private String albedo;

    public Moon() {

    }

    public Moon(Integer id, String name, Integer planetID) {
        this.id = id;
        this.name = name;
        this.planetId = planetID;
    }

    public Moon(MoonDao moonDao) {

        this.name = moonDao.getName();
        this.planetId = toInteger(moonDao.getPlanetid());
        this.mass = moonDao.getMass();
        this.radius = moonDao.getRadius();
        this.density = moonDao.getDensity();
        this.magnitude = moonDao.getMagnitude();
        this.albedo = moonDao.getAlbedo();

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * 
     * @return the id of the planet, the moon orbits around.
     */
    public Integer getPlanetId() {
        return planetId;
    }

    public String getMass() {
        return mass;
    }

    public String getRadius() {
        return radius;
    }

    public String getDensity() {
        return density;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getAlbedo() {
        return albedo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanetId(Integer planetID) {
        this.planetId = planetID;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public void setAlbedo(String albedo) {
        this.albedo = albedo;
    }

    /**
     * load the class object from list of String data.
     * the order of the strings defines the parameter to be set.
     * There are no gaps possible
     * The planet id (position 0) must be an string representing an integer value
     * The unique id of the moon is not loaded but set later after loading
     * 
     * @param recordData at least 7 Strings must be in the record data list
     * 
     * 
     */
    public void load(List<String> recordData) {
        int index = 0;

        for (String string : recordData) {

            switch (index) {
                case 0:
                    this.setPlanetId(toInteger(string));
                    break;
                case 1:
                    this.setName(string);
                    break;
                case 2:
                    this.setMass(string);
                    break;
                case 3:
                    this.setRadius(string);
                    break;
                case 4:
                    this.setDensity(string);
                    break;
                case 5:
                    this.setMagnitude(string);
                    break;
                case 6:
                    this.setAlbedo(string);
                    break;
                default:
                    // Ignore
            }

            index++;
        }
    }

    private Integer toInteger(String string) {

        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }
    }
}
