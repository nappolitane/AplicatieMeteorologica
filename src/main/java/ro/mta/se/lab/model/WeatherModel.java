package ro.mta.se.lab.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;

public class WeatherModel
{
    StringProperty mCountry;
    StringProperty mCity;
    FloatProperty mLon;
    FloatProperty mLat;

    StringProperty mWeatherMain;
    StringProperty mWeatherDescription;
    FloatProperty mTemperature;
    FloatProperty mWindSpeed;
    FloatProperty mRainVolume;
    FloatProperty mSnowVolume;

    public WeatherModel(StringProperty mCountry, StringProperty mCity, FloatProperty mLon,
                        FloatProperty mLat, StringProperty mWeatherMain, StringProperty mWeatherDescription,
                        FloatProperty mTemperature, FloatProperty mWindSpeed, FloatProperty mRainVolume,
                        FloatProperty mSnowVolume) {
        this.mCountry = mCountry;
        this.mCity = mCity;
        this.mLon = mLon;
        this.mLat = mLat;
        this.mWeatherMain = mWeatherMain;
        this.mWeatherDescription = mWeatherDescription;
        this.mTemperature = mTemperature;
        this.mWindSpeed = mWindSpeed;
        this.mRainVolume = mRainVolume;
        this.mSnowVolume = mSnowVolume;
    }

    public String getmCountry() {
        return mCountry.get();
    }

    public StringProperty mCountryProperty() {
        return mCountry;
    }

    public String getmCity() {
        return mCity.get();
    }

    public StringProperty mCityProperty() {
        return mCity;
    }

    public float getmLon() {
        return mLon.get();
    }

    public FloatProperty mLonProperty() {
        return mLon;
    }

    public float getmLat() {
        return mLat.get();
    }

    public FloatProperty mLatProperty() {
        return mLat;
    }

    public String getmWeatherMain() {
        return mWeatherMain.get();
    }

    public StringProperty mWeatherMainProperty() {
        return mWeatherMain;
    }

    public String getmWeatherDescription() {
        return mWeatherDescription.get();
    }

    public StringProperty mWeatherDescriptionProperty() {
        return mWeatherDescription;
    }

    public float getmTemperature() {
        return mTemperature.get();
    }

    public FloatProperty mTemperatureProperty() {
        return mTemperature;
    }

    public float getmWindSpeed() {
        return mWindSpeed.get();
    }

    public FloatProperty mWindSpeedProperty() {
        return mWindSpeed;
    }

    public float getmRainVolume() {
        return mRainVolume.get();
    }

    public FloatProperty mRainVolumeProperty() {
        return mRainVolume;
    }

    public float getmSnowVolume() {
        return mSnowVolume.get();
    }

    public FloatProperty mSnowVolumeProperty() {
        return mSnowVolume;
    }
}
