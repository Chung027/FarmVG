package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimalManager {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Animal> animalsList = new ArrayList<>();
    Crop choiceCrop;
    File fileAnimal = new File("folder/animal.txt");

    public AnimalManager() {
        if(!(fileAnimal.exists())) {
            animalsList.add(new Animal("cow", "Normande", new ArrayList<>(Arrays.asList("grass", "root"))));
            animalsList.add(new Animal("sheep", "Ovis aries", new ArrayList<>(Arrays.asList("bean","fruit"))));
            animalsList.add(new Animal("pig", "Landrace", new ArrayList<>(Arrays.asList("grass", "fruit", "root"))));
            animalsList.add(new Animal("goat", "Angora", new ArrayList<>(Arrays.asList("grass", "root"))));
            animalsList.add(new Animal("buffalo", "Bubalis", new ArrayList<>(Arrays.asList("root", "bean"))));
            animalsList.add(new Animal("duck", "Anas", new ArrayList<>(Arrays.asList("root", "bean"))));
        }
        else {
            getAnimal();
        }
    }
    private void viewAnimal(){
        for (Animal animal : animalsList) {
            System.out.println(animal.getDescription());
        }
        // denna funktion som jag använder för each för att loopa och skriver ut information för animal
    }
    private void addAnimal(){
        boolean foundAnimal = false;
        System.out.println("Which animal do you want to add to the list?");
        String nameAnimal = scanner.nextLine().toLowerCase();
        for(Animal animal: animalsList){
            if(animal.getName().equals(nameAnimal)){
                System.out.println("This animal is already on the list");
                foundAnimal = true;
                break;
            }
        }
        if(!(foundAnimal)) {
            System.out.println("Which species of this animal?");
            String speciesAnimal = scanner.nextLine();
            System.out.println("What type of crops does this animal eat?");
            String cropType = scanner.nextLine();
            animalsList.add(new Animal(nameAnimal, speciesAnimal, new ArrayList<>(Arrays.asList(cropType))));
            System.out.println("Successfully added a new animal ");
        }
        // denna funktion som jag använder for each kombinera med boolean variable när användare skriver in ett namn av
        // djur så de går genom hela lista med loop om det finns inte listan så det går ut ifrån funktion,
        // annat det kommer vidare till nästa if sat med boolean variable condition och
        // användare ska skriver in mer information och lägg in det till animal listan
    }
    private void removeAnimal(){
        viewAnimal();
        boolean found = false;
        System.out.println("Write the ID you want to delete");
        String input = scanner.nextLine(); // be användare att skriva inte id number som de vill ta bort
        try{    // måste ha en try cast för om dem skriver in bokstavar så hela program ska crash
            int inputID = Integer.parseInt(input);
            for (int i = 0; i < animalsList.size(); i++){ // använda en för i loop. Skapa interger i för att gå genom hela list
                if(inputID == animalsList.get(i).getId()){ // om användare skriver in id lika med animals id.
                    animalsList.remove(i).getId(); // det animals kommer borta från listan
                    System.out.println("Successfully delete the animal with ID " + inputID);
                    found = true;
                    break;
                }
            }
            if (!(found)) { // annat om användare skriver in ett nummers som inte finns i listan. Visa ut information.
                System.out.println("There aren't animal with this id");
            }
        }
        catch (Exception e){
            System.out.println("Please write in numbers. Thank you!");
        }
    }
    private void feedAnimal(ArrayList <Crop> crops){ // denna funktion som det tar med listan från crop
        for (Crop crop1: crops) {   // skriver ut crop lista med for each
            System.out.println(crop1.getDescription());
        }
        System.out.println("Write the crop IDs you want to pick");
        try {
            String pickCrop = scanner.nextLine();
            int cropId = Integer.parseInt(pickCrop);
            choiceCrop = null;  // skapar en variable för att spara det crops som användare ska välja ut.
            for (Crop crop : crops) { // med for each för att loopa genom hela lista
                if (crop.getId() == cropId) { // om det stämmer med crop id
                    choiceCrop = crop; // spara det crop i variable choiceCrop.
                }
            }
            if(choiceCrop != null) {
                viewAnimal();   // visa information i animal lista
                System.out.println("Pick the animal you want to feed. Enter their id");
                String pickAnimal = scanner.nextLine();
                int animalId = Integer.parseInt(pickAnimal);
                int currentCropQuantity = choiceCrop.getQuantity(); // Skapar denna variable för att lätta att använda det som det hämtar antal quantity av choiceCrop från Crop class
                Animal animal = getIdAnimal(animalId);// hämtar animal val genom getIdAnimal funktion
                if (animal != null) {
                    if (animal.getAcceptableCropType().contains(choiceCrop.getCropType()) && currentCropQuantity > 0) {
                        System.out.println("How many portions of " + choiceCrop.getName().toUpperCase() +
                                " do you want feed to " + animal.getName().toUpperCase() + "?");
                        String quantity = scanner.nextLine(); // jag använder contains() som det kontrollerar om animal acceptableCrop
                        int quantityFeed = Integer.parseInt(quantity); // index i array list som lika med cropType
                        if (currentCropQuantity > quantityFeed) { // använder takecrop() i crop class för att ta bort crop quantity
                            choiceCrop.takeCrop(currentCropQuantity - quantityFeed);// när man mata till djur
                            System.out.println(choiceCrop.getName().toUpperCase() + " feed to " + animal.getName().toUpperCase());
                        } else {
                            System.out.println(choiceCrop.getName().toUpperCase() + " is not enough");
                        }
                    } else if (!(animal.getAcceptableCropType().contains(choiceCrop.getCropType()))) {
                        System.out.println(animal.getName().toUpperCase() + " cannot eat this crop");
                        choiceCrop.takeCrop(currentCropQuantity); // om det accpetableCroptype array är inte lika med croptype
                    }                                              // djur ska inte äta det
                    else {
                        System.out.println(choiceCrop.getName().toUpperCase() + " is not enough");

                    }
                    animal.feed(choiceCrop);
                }
                else{
                    System.out.println("There is no such animal");
                }
            }
            else { // om id finns i list
                System.out.println("There is no such crop");
            }

        }
        catch (Exception e) {
            System.out.println("Please write in only numbers. Thank you");
        }
    }
    // andra sätt som jag skriver till feed funktion
    private void feedAnimalWithId(ArrayList<Crop> crops){ // andra sätt som be användare skriver in name crop och animal
        boolean foundCrop= false;
        boolean foundAnimal = false;
        for(Crop crop : crops){ // skriv ut information
            System.out.println(crop.getDescription());
        }
        System.out.println("Pick crops. Write in name:");   // be användare skriver in namn crop
        String nameCrop = scanner.nextLine();
        choiceCrop = null;    // skapar variable för att spara användare val crop
        for ( Crop crop : crops){   // kolla om det finns i list med for each
            if(crop.getName().equals(nameCrop)){
                choiceCrop = crop;
                foundCrop = true; // om det finns i list går det ut från loop.
                break;
            }
        }
        if(foundCrop == false){ // om hittar inte i crop list går det ut från denna funktion
            System.out.println("No crop exists");
        }
        else { // annat om det hitta crop går in denna condition
            Animal choiceAnimal = null; // skapar en variable för att spara animal val
            viewAnimal();
            System.out.println("Pick animal. Write in name");   // be användare skriver in name av djur
            String nameAnimal = scanner.nextLine();
            for (Animal animals : animalsList) {    // loopa hela lista för att hitta rätt.
                if (animals.getName().equals(nameAnimal)) {
                    choiceAnimal = animals; // spara i denna choiceAnimal variable
                    foundAnimal = true;
                    break;
                }
            }
            if(foundAnimal == false){ // om det hittar inte går det in denna condition och visa ut information, sen går det ut
                System.out.println("There is no animal!");
            }
            else{ // om det finns i lista gå den in denna condition
                if(choiceAnimal.getAcceptableCropType().contains(choiceCrop.getCropType()) && choiceCrop.getQuantity() > 0){
                    System.out.println("How many crops do you want to feed to the " + choiceAnimal.getName() + "?");
                    String quantity = scanner.nextLine();
                    int quantityFeed = Integer.parseInt(quantity);
                    if (choiceCrop.getQuantity() > quantityFeed) {
                        choiceCrop.takeCrop(choiceCrop.getQuantity() - quantityFeed);
                        System.out.println(choiceCrop.getName() + " feed to " + choiceAnimal.getName());
                    }
                    else{
                        System.out.println("Crop is not enough");
                    }
                }
                else if (!(choiceAnimal.getAcceptableCropType().contains(choiceCrop.getCropType()))){
                    System.out.println(choiceAnimal.getName() + " doesn't like this crop");
                }
                else{
                    System.out.println("Crop is not sufficient");
                }
            }
            choiceAnimal.feed(choiceCrop);
        }
    }
    public ArrayList <Animal> getAnimal(){ // funktionen ska läsa animal.txt, sen return den till animal lista
        try {
            FileReader fileReaderAnimal = new FileReader(fileAnimal);
            BufferedReader bfAnimal = new BufferedReader(fileReaderAnimal);
            String line = bfAnimal.readLine(); // läsa file animal
            while (line != null) { // använda loop för att läsa hela fil. när raden är tom så det ska inte läsa mer.
                String[] variable = line.split(","); // delar variable med ','
                int id = Integer.parseInt(variable[0]);
                String name = variable[1];
                String species = variable[2];
                String[] cropType = variable[3].split("/"); // delar variable i cropType variable med '/'
                ArrayList<String> cropTypeList = new ArrayList<>(Arrays.asList(cropType));
                Animal animal = new Animal(id, name, species, cropTypeList);
                animalsList.add(animal);
                System.out.println(line);
                line = bfAnimal.readLine();
            }
            bfAnimal.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return animalsList;
    }
    public Animal getIdAnimal(int id){
        for (Animal animals : animalsList){
            if (id == animals.getId()){
                return animals;
            }
        }
        return null;
    }
    // här börjar jag skapa med menu
    public void animalMenu(CropManager cropManager) {
        boolean run = true;
        while (run) {
            String[] menu = {"View animal", "Add animal", "Remove animal", "Feed animal", "Back"};
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ": " + menu[i]);
            }
            System.out.print("Choose menu: ");
            String menuChoice = scanner.nextLine();
            switch (menuChoice) {
                case "1":
                    viewAnimal();
                    break;
                case "2":
                    addAnimal();
                    break;
                case "3":
                    removeAnimal();
                    break;
                case "4":
                    feedAnimal(cropManager.cropsList); // första sätt som användare ska skriver in id.
                    //feedAnimalWithId(cropManager.cropsList); // andra sätt användare ska skriver in name
                    break;
                case "5":
                    run = false;
                    break;
            }
        }
    }
}
