package de.hmayer.solarsystemserver.model.dao;


/**
 *  This class represents a planet and its parameter.
 *  Hint : The number of satellites is currently not used.
 * 
 * @author Holger Mayer
 */
public class PlanetDao {
    private String id;
    private String  name;
    private String  diameter;
    private String  mass;
    private String  inclination;
    private String  eccentricity;
    private String  semimajoraxis;
    private String  surfacegravity;
    private String  orbitalperiod;
    private String  siderealrotation;
    private String  satellites;

    public PlanetDao(){
        /*
        id = "0";
        name = "";
        diameter = "-1.0";
        mass = "-1.0";
        inclination = "-1.0";
        eccentricity = "-1.0";
        semimajoraxis = "-1.0";
        surfacegravity = "-1.0";
        orbitalperiod = "-1.0";
        siderealrotation = "-1.0";
        satellites = "0";
        */
    }

    public PlanetDao(String id, String name, String diameter, String mass, String inclination, String eccentricity,
            String semimajoraxis, String surfacegravity, String orbitalperiod, String siderealrotation,
            String satellites) {
        this.id = id;
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
        this.inclination = inclination;
        this.eccentricity = eccentricity;
        this.semimajoraxis = semimajoraxis;
        this.surfacegravity = surfacegravity;
        this.orbitalperiod = orbitalperiod;
        this.siderealrotation = siderealrotation;
        this.satellites = satellites;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getInclination() {
        return inclination;
    }

    public void setInclination(String inclination) {
        this.inclination = inclination;
    }

    public String getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(String eccentricity) {
        this.eccentricity = eccentricity;
    }

    public String getSemimajoraxis() {
        return semimajoraxis;
    }

    public void setSemimajoraxis(String semimajoraxis) {
        this.semimajoraxis = semimajoraxis;
    }

    public String getSurfacegravity() {
        return surfacegravity;
    }

    public void setSurfacegravity(String surfacegravity) {
        this.surfacegravity = surfacegravity;
    }

    public String getOrbitalperiod() {
        return orbitalperiod;
    }

    public void setOrbitalperiod(String orbitalperiod) {
        this.orbitalperiod = orbitalperiod;
    }

    public String getSiderealrotation() {
        return siderealrotation;
    }

    public void setSiderealrotation(String siderealrotation) {
        this.siderealrotation = siderealrotation;
    }

    public String getSatellites() {
        return satellites;
    }

    public void setSatellites(String satellites) {
        this.satellites = satellites;
    }

    @Override
    public String toString() {
        return "PlanetDao [id=" + id + ", name=" + name + ", diameter=" + diameter + ", mass=" + mass + ", inclination="
                + inclination + ", eccentricity=" + eccentricity + ", semimajoraxis=" + semimajoraxis
                + ", surfacegravity=" + surfacegravity + ", orbitalperiod=" + orbitalperiod + ", siderealrotation="
                + siderealrotation + ", satellites=" + satellites + "]";
    }


}
