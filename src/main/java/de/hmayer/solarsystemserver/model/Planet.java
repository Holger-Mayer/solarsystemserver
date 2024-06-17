package de.hmayer.solarsystemserver.model;

import java.util.List;

/**
 *  This class represents a planet and its parameter.
 *  Hint : The number of satellites is currently not used.
 * 
 * @author Holger Mayer
 */
public class Planet implements Identifiable{
    private Integer id;
    private String  name;
    private Double  diameter;
    private String  mass;
    private Double  inclination;
    private Double  eccentricity;
    private Double  semiMajorAxis;
    private Double  surfaceGravity;
    private Double  orbitalPeriod;
    private Double  siderealRotation;
    private Integer satellites;

    public Planet(){
        
    }

    public Planet(Integer id, String name, Double diameter) {
        this.id = id;
        this.name = name;
        this.diameter = diameter;
    }
    

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDiameter() {
        return diameter;
    }

    public String getMass() {
        return mass;
    }

    public Double getInclination() {
        return inclination;
    }

    public Double getEccentricity() {
        return eccentricity;
    }

    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public Double getSurfaceGravity() {
        return surfaceGravity;
    }

    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Double getSiderealRotation() {
        return siderealRotation;
    }

    public Integer getSatellites() {
        return satellites;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setInclination(Double inclination) {
        this.inclination = inclination;
    }

    public void setEccentricity(Double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setSemiMajorAxis(Double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public void setSurfaceGravity(Double surfaceGravity) {
        this.surfaceGravity = surfaceGravity;
    }

    public void setOrbitalPeriod(Double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public void setSiderealRotation(Double siderealRotation) {
        this.siderealRotation = siderealRotation;
    }

    public void setSatellites(Integer satellites) {
        this.satellites = satellites;
    }

    /**
     * load the class object from list of String data.
     * the order of the strings defines the parameter to be set.
     * There are no gaps possible
     * The unique id is loaded (parameter 0) in contrast to the moon class.
     * 
     * @param recordData at least 11 Strings must be in the record data list
     * 
     * 
     */
     public void load(List<String> recordData) {
        int index = 0;
              for (String string : recordData) {

            switch (index) {
                case 0:
                    this.setId(Integer.parseInt(string));
                    break;
                case 1:
                    this.setName(string);
                    break;
                case 2:
                this.setDiameter(Double.parseDouble(string));
                    break;
                case 3:
                this.setMass(string);
                    break;
                case 4:
                this.setInclination(Double.parseDouble(string));
                    break;
                case 5:
                this.setEccentricity(Double.parseDouble(string));
                    break;
                case 6:
                this.setSemiMajorAxis(Double.parseDouble(string));
                    break;
                case 7:
                this.setSurfaceGravity(Double.parseDouble(string));
                    break;
                case 8:
                this.setOrbitalPeriod(Double.parseDouble(string));
                    break;
                case 9:
                this.setSiderealRotation(Double.parseDouble(string));
                    break;
                case 10:
                this.setSatellites(0);
                    break;

                default:
                    // Ignore
            }

            index++;
        }
    }
    
}
