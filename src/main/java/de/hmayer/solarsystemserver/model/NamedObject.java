package de.hmayer.solarsystemserver.model;

/**
 * NamedObject is used for the representation of an id,name pair.
 * These are used to reduce traffic when only names are necessary in 
 * the rest interface to return
 * 
 * @author Holger Mayer 
 */
public class NamedObject {
    /**
     *  The unique id of the object
     */
    private Integer id;
    private String name;

    public NamedObject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
