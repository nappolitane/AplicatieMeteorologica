package ro.mta.se.lab.model;

import java.util.Date;

/**
 * Aceasta clasa modelul din patternul de MVC.
 * In aceasta clasa sunt pastrate valorile returnate de API pentru a le afisa
 * in interfata grafica
 */
public class WeatherModel
{
    String mCountry;
    String mCity;
    Date mDateTime;
    String mWeatherDescription;
    Double mTemperature;
    Double mWindSpeed;
    Double mHumidity;

    public WeatherModel(){ }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String Country) {
        this.mCountry = Country;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String City) {
        this.mCity = City;
    }

    public Date getmDateTime() {
        return mDateTime;
    }

    public void setmDateTime(Date DateTime) {
        this.mDateTime = DateTime;
    }

    public String getmWeatherDescription() {
        return mWeatherDescription;
    }

    public void setmWeatherDescription(String WeatherDescription) {
        this.mWeatherDescription = WeatherDescription;
    }

    public Double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(Double Temperature) {
        this.mTemperature = Temperature;
    }

    public Double getmWindSpeed() {
        return mWindSpeed;
    }

    public void setmWindSpeed(Double WindSpeed) {
        this.mWindSpeed = WindSpeed;
    }

    public Double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(Double Humidity) {
        this.mHumidity = Humidity;
    }
}
