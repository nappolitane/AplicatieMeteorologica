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
    private ObservableList<ConfigEntry> configData = FXCollections.observableArrayList();

    public static void main(String[] args)
    {
        launch(args);
    }

    private void initConfigData(String fName)
    {
        try {
            File fObj = new File(fName);
            Scanner myReader = new Scanner(fObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entries = data.split(" ");
                configData.add(new ConfigEntry(Long.parseLong(entries[0]),entries[1],Float.parseFloat(entries[2]),Float.parseFloat(entries[3]),entries[4]));
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage)
    {
        initConfigData("src/main/resources/config_file.txt");
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(this.getClass().getResource("/view/WeatherView.fxml"));
            loader.setController(new WeatherController(configData));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
