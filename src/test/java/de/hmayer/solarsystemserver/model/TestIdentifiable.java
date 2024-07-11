package de.hmayer.solarsystemserver.model;


public class TestIdentifiable implements Identifiable {
    private Integer id;
    private String name;

    public TestIdentifiable(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "TestIdentifiable [id=" + id + ", name=" + name + "]";
    }
}