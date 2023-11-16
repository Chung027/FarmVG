package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {
    CropManager cropManager = new CropManager();
    AnimalManager animalManager = new AnimalManager();
    Scanner scanner = new Scanner(System.in);
    public void save(){
        File folder = new File("folder"); // Skapa en folder som jag ska lägga mina animal och crop lista i file.txt
        if(!(folder.exists())){     // om folder är inte finns skapar programmet folder
            folder.mkdir();
        }
        File fileCrop = new File("folder/crop.txt"); // skapa file till min crops lista
        if(!(fileCrop).exists()) {
            try {
                FileWriter fileWriterCrop = new FileWriter(fileCrop);
                BufferedWriter bfCrop = new BufferedWriter(fileWriterCrop);
                for(int i = 0; i < cropManager.cropsList.size(); i++) { // jag använder for loop för att loopa in hela
                    bfCrop.write(cropManager.cropsList.get(i).getCsv());// lista för det skriver in min file
                    if (i < cropManager.cropsList.size() - 1) { // den if sat använder för att ta bort sista tom rad
                        bfCrop.newLine();                      // i cropfile.txt
                    }
                }
                bfCrop.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File fileAnimal = new File("folder/animal.txt");
        if(!(fileAnimal).exists()){
            try{
                FileWriter fileWriterAnimal = new FileWriter(fileAnimal);
                BufferedWriter bfAnimal = new BufferedWriter(fileWriterAnimal);
                for (int i = 0; i < animalManager.animalsList.size(); i++) {
                    bfAnimal.write(animalManager.animalsList.get(i).getCsv());
                    if (i < animalManager.animalsList.size() -1 ){
                        bfAnimal.newLine();
                    }
                }
                bfAnimal.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void menu() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Chung farm menu\n_________________________________________________");
            System.out.println("1: Crop menu");
            System.out.println("2: Animal menu");
            System.out.println("3: Save");
            System.out.println("4: Exit");
            System.out.print("Choose menu: ");
            String menuChoice = scanner.nextLine();
            switch (menuChoice) {
                case "1":
                    cropManager.cropMenu();
                    break;
                case "2":
                    animalManager.animalMenu(cropManager);
                    break;
                case "3":
                    save();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Please write in numbers in screen");
            }
        }
        System.out.println("GoodBye. See you later!");

    }
}
