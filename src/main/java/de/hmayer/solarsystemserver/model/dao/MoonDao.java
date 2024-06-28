package de.hmayer.solarsystemserver.model.dao;

public class MoonDao {
    private String planetid;
    private String name;
    private String mass;
    private String radius;
    private String density;
    private String magnitude;
    private String albedo;

    public MoonDao() {
        
    }

    public MoonDao(String planetid, String name, String mass, String radius, String density, String magnitude,
            String albedo) {
        this.planetid = planetid;
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.density = density;
        this.magnitude = magnitude;
        this.albedo = albedo;
    }

    public String getPlanetid() {
        return planetid;
    }

    public void setPlanetid(String planetid) {
        this.planetid = planetid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getAlbedo() {
        return albedo;
    }

    public void setAlbedo(String albedo) {
        this.albedo = albedo;
    }

    @Override
    public String toString() {
        return "MoonDao [planetid=" + planetid + ", name=" + name + ", mass=" + mass + ", radius=" + radius
                + ", density=" + density + ", magnitude=" + magnitude + ", albedo=" + albedo + "]";
    }

    
    
}
