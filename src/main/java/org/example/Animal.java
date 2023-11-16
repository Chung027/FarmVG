package org.example;

import java.util.ArrayList;

public class Animal extends Entity{
    public String species;
    private ArrayList <String> acceptableCropType;
    ArrayList<Crop> crops = new ArrayList<>();
    public static int nextIdAnimal = 1;
    public Animal(String name, String species,ArrayList<String> acceptableCropType) {
        super(nextIdAnimal, name);
        nextIdAnimal ++;
        this.species = species;
        this.acceptableCropType = acceptableCropType;
    }
    public Animal(String name, String species){
        super(nextIdAnimal,name);
        nextIdAnimal ++;
        this.species = species;
    }
    public Animal(int id,String name, String species, ArrayList<String> acceptableCropType){
        super(id,name);
        if(id > nextIdAnimal) {
            nextIdAnimal = id + 1;
        }
        this.species = species;
        this.acceptableCropType = acceptableCropType;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + species + ", can eat " + acceptableCropType;
    }
    public String getCsv(){
        return getId() + "," + getName() + "," + species + "," + getCsvCropType(acceptableCropType);
    }
    public String getCsvCropType (ArrayList<String> acceptableCropType){
        return String.join("/",acceptableCropType);
        // denna csv använder för att dela crop type i min array list acceptableCropType med "/" som ligger in animal lista
    }
    public void feed (Crop crop){
        crops.add(crop);
    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ArrayList <String> getAcceptableCropType() {
        return acceptableCropType;
    }

    public void setAcceptableCropType(ArrayList<String> acceptableCropType) {
        this.acceptableCropType = acceptableCropType;
    }

}
