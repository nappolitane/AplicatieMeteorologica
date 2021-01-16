package ro.mta.se.lab.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import ro.mta.se.lab.ConfigEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ro.mta.se.lab.LogsManager;
import ro.mta.se.lab.WeatherAPIManager;
import ro.mta.se.lab.WeatherAPIManagerTest;
import ro.mta.se.lab.model.WeatherModel;

import java.util.Date;
import java.util.Map;

/**
 * Aceasta clasa este implementarea Controllerului din patternul de MVC.
 * In aceasta clasa se initializeaza choiceBox-urile din interfata grafica cu
 * datele din fisierul de configurare si se adauga un listener pentru a actualiza
 * datele afisate.
 */
public class WeatherController
{
    private WeatherModel weatherModel; // Aceasta variabila este legatura catre modelul din patternul MVC

    private ObservableList<ConfigEntry> confData; // Aceasta variabila este lista cu datele din fisierul de configurare

    /**
     * Aici sunt declarate variabilele legate la interfata grafica
     */
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

    // Constructorul initializeaza cele doua variabile care apartin clasei
    public WeatherController(ObservableList<ConfigEntry> configData)
    {
        this.weatherModel = new WeatherModel();
        this.confData = configData;
    }

    // Aceasta functie intoarce Numele intreg al unei tari in functie de codul respectiv
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

    /**
     * Dupa cum spune si numele aceasta functie parseaza json-ul primit de la API
     * si salveaza datele in variabila Model a patternului MVC
     */
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

    // Aceasta functie actualizeaza datele din interfata grafica
    @FXML
    private String showWeatherInfo()
    {
        String str = weatherModel.getmCountry() + "; " + weatherModel.getmCity() + "; ";

        dateLabel.setText(weatherModel.getmDateTime().toString());
        dateLabel.setVisible(true);
        str += weatherModel.getmDateTime().toString() + "; ";

        weatherLabel.setText("Astazi este: " + weatherModel.getmWeatherDescription());
        weatherLabel.setVisible(true);
        str += "Astazi este: " + weatherModel.getmWeatherDescription() + "; ";

        temperatureLabel.setText(weatherModel.getmTemperature() + " grade Celsius");
        temperatureLabel.setVisible(true);
        str += weatherModel.getmTemperature() + " grade Celsius; ";

        windLabel.setText("Vant: " + weatherModel.getmWindSpeed() + " m/s");
        windLabel.setVisible(true);
        str += "Vant: " + weatherModel.getmWindSpeed() + " m/s; ";

        humidityLabel.setText("Umiditate: " + weatherModel.getmHumidity() + "%");
        humidityLabel.setVisible(true);
        str+= "Umiditate: " + weatherModel.getmHumidity() + "%; ";

        return str;
    }

    /**
     * Aceasta functie este initializatorul programului.
     * Se adauga in lista ChoiceBox-urilor tarile si orasele din fisierul de configurare.
     * Se adauga un listener pe choiceBoxul pentru tara si daca se schimba tara se schimba
     * si lista de orase din choiceboxul de orase
     * Se aduga un listener pentru choiceboxul de orase pentru a apela API-ul si respectiv
     * pentru a actualiza informatiile din interfata grafica
     */
    @FXML
    public void initialize()
    {
        dateLabel.setVisible(false);
        weatherLabel.setVisible(false);
        temperatureLabel.setVisible(false);
        windLabel.setVisible(false);
        humidityLabel.setVisible(false);

        // Se initializeaza choiceboxul de tari cu tarile din fisierul de configurare
        ObservableList<String> countries = FXCollections.observableArrayList();
        for(ConfigEntry c : confData) {
            if(!countries.contains(getNameofCountry(c.getmCountry())))
                countries.add(getNameofCountry(c.getmCountry()));
        }
        countryChoiceBox.getItems().clear();
        countryChoiceBox.setItems(countries);
        cityChoiceBox.setDisable(true);

        // Listener pentru choiceboxul de tari pentru a schimba lista de orase din choiceboxul de orase
        countryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
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

        // Listener pentru choiceboxul de orase pentru a apela API-ul si a actualiza datele din interfata grafica
        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                weatherModel.setmCity(newValue);

                Map<String, Object> map = WeatherAPIManager.getJsonMapFromAPI(newValue); // Se aduc datele din API
                if(map == null) {
                    System.out.println("Error receiving JSON!");
                    return;
                }

                parseJsonDataToModel(map); // Se pun valorile din json in model

                String weatherInfo = showWeatherInfo(); // Se afiseaza informatiile in interfata grafica

                LogsManager logger = new LogsManager();
                logger.logThis(weatherInfo); // Se introduce log-ul respectiv afisarii
            }
        });
    }
}
