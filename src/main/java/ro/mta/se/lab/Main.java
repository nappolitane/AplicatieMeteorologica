package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ro.mta.se.lab.controller.WeatherController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application
{
    /**
     * Aceasta variabila este o lista de ConfigEntry si este responsabila de
     * mentinerea in memorie a fisierului de configurare
     */
    private ObservableList<ConfigEntry> configData = FXCollections.observableArrayList();

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Functia implementeaza citirea fisierului de configurare si adaugarea
     * datelor in lista de variabile de tipul ConfigEntry care vor fi folosite
     * ulterior in clasa WeatherController
     */
    private int initConfigData(String fName)
    {
        try {
            File fObj = new File(fName);
            Scanner myReader = new Scanner(fObj);
            while (myReader.hasNextLine()) { // Se citeste linie cu linie fisierul de configurare
                String data = myReader.nextLine();
                String[] entries = data.split(" "); // Se imparte fiecare linie in cuvinte(intrari)
                if(entries.length != 5)
                    return 1;
                /* Se adauga fiecare dintre cuvintele(intrarile) din fisier in aceasta lista */
                configData.add(new ConfigEntry(Long.parseLong(entries[0]),entries[1],
                        Float.parseFloat(entries[2]),Float.parseFloat(entries[3]),entries[4]));
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return 0;
    }

    public void start(Stage primaryStage)
    {
        int status = initConfigData("src/main/resources/config_file.txt");
        if(status == 1){
            System.out.println("Config file error: incorrect format!");
            System.exit(status);
        }

        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().getResource("/view/WeatherView.fxml"));
            loader.setController(new WeatherController(configData)); // Se trimite ca parametru lista cu datele din fisierul de configurare
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
