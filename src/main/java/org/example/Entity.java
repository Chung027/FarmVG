package org.example;

public class Entity {
    public int id;
    protected String name;
    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getDescription() {
        return id + ": " + name + ", ";
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
