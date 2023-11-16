package org.example;

import java.util.ArrayList;
public class Crop extends Entity{
    public String cropType;
    private int quantity;
    ArrayList <Animal> animals = new ArrayList<>();
    public static int nextIdCrop = 1;
    public Crop(String name, String cropType, int quantity) {
        super(nextIdCrop, name);
        nextIdCrop ++;
        this.cropType = cropType;
        this.quantity = quantity;
    }
    public Crop (int id,String name, String cropType, int quantity){
        super(id,name);
        if ( id > nextIdCrop) {
            nextIdCrop = id + 1 ;
        }
        this.cropType = cropType;
        this.quantity = quantity;

    }
    public String getCsv(){
        return getId() + "," + name + "," + cropType + "," + quantity;
    }
    @Override
    public String getDescription() {
        return super.getDescription() + cropType + " ,quantity is " + quantity;
    }
    public void addCrop(int quantity){
        this.quantity = quantity;
    }
    public boolean takeCrop(int quantity){
        this.quantity = quantity;
        return true;
    }
    public String getCropType(){
        return cropType;
    }
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
