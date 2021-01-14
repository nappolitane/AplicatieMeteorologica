package ro.mta.se.lab.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ro.mta.se.lab.ConfigEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class WeatherController
{
    private ObservableList<ConfigEntry> confData;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    public WeatherController(ObservableList<ConfigEntry> configData) {
        this.confData = configData;
    }

    private String getNameofCountry(String country)
    {
        switch (country)
        {
            case "RU":
                return "Russia";
            case "RO":
                return "Romania";
            case "GB":
                return "Marea Britanie";
            case "DE":
                return "Germania";
            case "SE":
                return "Suedia";
            case "HU":
                return "Ungaria";
            case "ES":
                return "Spania";
            case "FR":
                return "Franta";
            case "PT":
                return "Portugalia";
            case "BE":
                return "Belgia";
            default:
                return country;
        }
    }

    @FXML
    public void initialize()
    {
        ObservableList<String> countries = FXCollections.observableArrayList();
        for(ConfigEntry c : confData) {
            if(!countries.contains(getNameofCountry(c.getmCountry())))
                countries.add(getNameofCountry(c.getmCountry()));
        }
        countryChoiceBox.getItems().clear();
        countryChoiceBox.setItems(countries);
        cityChoiceBox.setDisable(true);

        countryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<String> cities = FXCollections.observableArrayList();
                for(ConfigEntry c : confData) {
                    if(newValue.equals(getNameofCountry(c.getmCountry())))
                        cities.add(c.getmCity());
                }
                cityChoiceBox.setDisable(false);
                cityChoiceBox.getItems().clear();
                cityChoiceBox.setItems(cities);
            }
        });
    }
}
