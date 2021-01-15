package ro.mta.se.lab.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import ro.mta.se.lab.ConfigEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ro.mta.se.lab.WeatherAPIManager;
import ro.mta.se.lab.model.WeatherModel;

import java.util.Date;
import java.util.Map;

public class WeatherController
{
    private WeatherModel weatherModel;

    private ObservableList<ConfigEntry> confData;

    @FXML
    private ChoiceBox<String> countryChoiceBox;
    @FXML
    private ChoiceBox<String> cityChoiceBox;

    @FXML
    private Label dateLabel;
    @FXML
    private Label weatherLabel;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label windLabel;
    @FXML
    private Label humidityLabel;

    public WeatherController(ObservableList<ConfigEntry> configData) {
        this.weatherModel = new WeatherModel();
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

    private void parseJsonDataToModel(Map<String, Object> jsonMap)
    {
        //Map<String, Object> weatherMap = WeatherAPIManager.jsonToMap(jsonMap.get("weather").toString()); NU MERGE
        Map<String, Object> mainMap = WeatherAPIManager.jsonToMap(jsonMap.get("main").toString());
        Map<String, Object> windMap = WeatherAPIManager.jsonToMap(jsonMap.get("wind").toString());

        String sdt = jsonMap.get("dt").toString();
        long dt = (long) Double.parseDouble(sdt);
        dt = dt - (2*3600); // local time to GMT 00:00
        String stz = jsonMap.get("timezone").toString();
        long tz = (long) Double.parseDouble(stz);
        long d = (dt * 1000) + (tz * 1000);
        Date now = new Date(d);
        weatherModel.setmDateTime(now);

        String sTemp = mainMap.get("temp").toString();
        Double dTemp = Double.parseDouble(sTemp);
        weatherModel.setmTemperature(dTemp);

        String desc = jsonMap.get("weather").toString();
        int start = desc.indexOf("description",0) + "description".length() + 1;
        int end = desc.indexOf(',', start);
        desc = desc.substring(start, end);
        weatherModel.setmWeatherDescription(desc);

        String sWind = windMap.get("speed").toString();
        Double dWind = Double.parseDouble(sWind);
        weatherModel.setmWindSpeed(dWind);

        String sHumidity = mainMap.get("humidity").toString();
        Double dHumidity = Double.parseDouble(sHumidity);
        weatherModel.setmHumidity(dHumidity);
    }

    @FXML
    private void showWeatherInfo()
    {
        dateLabel.setText(weatherModel.getmDateTime().toString());
        dateLabel.setVisible(true);

        weatherLabel.setText("Astazi este: " + weatherModel.getmWeatherDescription());
        weatherLabel.setVisible(true);

        temperatureLabel.setText(weatherModel.getmTemperature() + " grade Celsius");
        temperatureLabel.setVisible(true);

        windLabel.setText("Vant: " + weatherModel.getmWindSpeed() + " m/s");
        windLabel.setVisible(true);

        humidityLabel.setText("Umiditate: " + weatherModel.getmHumidity() + "%");
        humidityLabel.setVisible(true);
    }

    @FXML
    public void initialize()
    {
        dateLabel.setVisible(false);
        weatherLabel.setVisible(false);
        temperatureLabel.setVisible(false);
        windLabel.setVisible(false);
        humidityLabel.setVisible(false);

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
                weatherModel.setmCountry(newValue);
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

        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                weatherModel.setmCity(newValue);
                Map<String, Object> map = WeatherAPIManager.getStringFromJson(newValue);
                if(map == null) {
                    System.out.println("Error reading JSON!");
                    return;
                }

                parseJsonDataToModel(map);

                showWeatherInfo();
            }
        });
    }
}
