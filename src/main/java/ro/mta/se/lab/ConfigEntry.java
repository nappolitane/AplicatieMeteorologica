package ro.mta.se.lab;

/**
 * Aceasta clasa este folosita pentru a pastra fiecare intrare din fisierul
 * de configurare intr-o lista de astfel de structura
 */
public class ConfigEntry {
    long mId;
    String mCity;
    float mLat;
    float mLon;
    String mCountry;

    public ConfigEntry(long mId, String mCity, float mLat, float mLon, String mCountry) {
        this.mId = mId;
        this.mCity = mCity;
        this.mLat = mLat;
        this.mLon = mLon;
        this.mCountry = mCountry;
    }

    public long getmId() {
        return mId;
    }

    public String getmCity() {
        return mCity;
    }

    public float getmLat() {
        return mLat;
    }

    public float getmLon() {
        return mLon;
    }

    public String getmCountry() {
        return mCountry;
    }

    @Override
    public String toString() {
        return "ConfigEntry{" +
                "mId=" + mId +
                ", mCity='" + mCity + '\'' +
                ", mLat=" + mLat +
                ", mLon=" + mLon +
                ", mCountry='" + mCountry + '\'' +
                '}';
    }
}
