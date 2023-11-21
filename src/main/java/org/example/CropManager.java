package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Crop> cropsList = new ArrayList<>();
    File fileCrop = new File("folder/crop.txt");
    public CropManager(){
        if(!(fileCrop.exists())) {
            cropsList.add(new Crop("corn", "grass", 5));
            cropsList.add(new Crop("pineapple", "fruit", 0));
            cropsList.add(new Crop("wheat", "grass", 12));
            cropsList.add(new Crop("grass", "grass", 3));
            cropsList.add(new Crop("potato", "root", 15));
            cropsList.add(new Crop("carrot", "root", 8));
            cropsList.add(new Crop("apple", "fruit", 3));
            cropsList.add(new Crop("soybean", "bean", 6));
        }
        else {
            getCrops();
        }
    }
    private void viewCrop(){
        for (int i = 0; i < cropsList.size(); i++) {
            System.out.println(cropsList.get(i).getDescription());
        }
    }
    private void addCrop(){
        boolean cropExist = false;
        int numbers = -1;
        System.out.println("Write in the crop you want to add?"); // be användare skriver in name crop de vill lägga till
        String cropName = scanner.nextLine().toLowerCase();
        for (Crop crops: cropsList){ // använda for each för att kolla i lista om det finns crop som användare skriver in
            if (crops.getName().equals(cropName)) { // om det stämmer så be de skriver in antal quantity de vill lägga till
                System.out.println("This crop is already in the list. How much additional quantity would you like?");
                try {
                    while (numbers < 0) { // om användare skriver in negativ nummer det program står stilla tills
                        String addQuantity = scanner.nextLine(); // de skriver in rätt nummer
                        int addQuantityNumber = Integer.parseInt(addQuantity);
                        if (addQuantityNumber > 0) {
                            crops.addCrop(crops.getQuantity() + addQuantityNumber); // lägga quantity till
                            cropExist = true;
                            break;
                        } else {
                            System.out.println("The quantity can't be negative numbers");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Please write in numbers");
                }
            }
        }
        if(!(cropExist)){ // om crop finns inte lista går den in denna condition.
            System.out.println("what is the type of crop?"); // be användare skriver in mer information
            String cropType = scanner.nextLine();
            System.out.println("How many quantities do you want to add?");
            try {
                while (numbers < 0) { // kontrollera om det skriver in negativ nummer.
                    String addQuantity = scanner.nextLine();
                    int addQuantityNumbers = Integer.parseInt(addQuantity);
                    if (addQuantityNumbers > 0) {
                        cropsList.add(new Crop(cropName, cropType, addQuantityNumbers)); // lägger ny animal till lista
                        System.out.println(cropName.toUpperCase() + " has been added to the list");
                        break;
                    } else {
                        System.out.println("The quantity cannot be negative numbers");
                    }
                }
            }
            catch (Exception e){
                System.out.println("Please write in numbers. Thank you");
            }
        }
    }
    private void deleteCrop() {
        viewCrop();
        boolean found = false;
        System.out.println("Write in the ID you want to remove from the list");
        try {
            String input = scanner.nextLine();
            int inputId = Integer.parseInt(input);
            for (int i = 0; i < cropsList.size(); i++) { // Loopa in hela lista om id som användare skriv in lika med
                if (inputId == cropsList.get(i).getId()) { // crop ID så det ska ta bort
                    cropsList.remove(i);
                    System.out.println("This crop with ID " + inputId + " has been removed from the list");
                    found = true;
                    break;
                }
            }
            if (!(found)) { // om det hittar inte crop ID. Det kommer in denna if sat som visa information. Sedan det
                System.out.println("This ID doesn't exist in the list"); // går ut ifrån funktion.
            }
        } catch (Exception e) {
            System.out.println("Please write in numbers. Thank you!");
        }
    }
    public ArrayList <Crop> getCrops(){
        try {
            FileReader fileReaderCrop = new FileReader(fileCrop);
            BufferedReader brCrop = new BufferedReader(fileReaderCrop);
            String line = brCrop.readLine();
            while (line != null) {
                String[] variable = line.split(",");
                int id = Integer.parseInt(variable[0]);
                String cropName = variable[1];
                String cropType = variable[2];
                int cropQuantity = Integer.parseInt(variable[3]);
                Crop crop = new Crop(id, cropName, cropType, cropQuantity);
                cropsList.add(crop);
                System.out.println(line);
                line = brCrop.readLine();
            }
            brCrop.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cropsList;
    }
    public void cropMenu() {
        boolean run = true;
        while (run) {
            String[] menu = {"View Crop", "Add Crop", "Remove Crop", "Back"};
            for (int i = 0; i < menu.length; i++) {
                System.out.println(i + 1 + ": " + menu[i]);
            }
            System.out.print("Choose menu: ");
            String menuChoice = scanner.nextLine();
            switch (menuChoice) {
                case "1":
                    viewCrop();
                    break;
                case "2":
                    addCrop();
                    break;
                case "3":
                    deleteCrop();
                    break;
                case "4":
                    run = false;
                    break;
            }
        }
    }
}
