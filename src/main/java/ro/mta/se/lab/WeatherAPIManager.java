package ro.mta.se.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

/**
 * Aceasta clasa este folosita pentru a apela API-ul OpenWeatherMap si
 * pentru a intoarce o variabila de tip Map<string, object> pentru a mentine
 * datele din json.
 * Am folosit pentru parsarea jsonului libraria Gson.
 */
public class WeatherAPIManager
{
    // Aceasta functie intoarce o variabila de tip Map dintr-un string intreg de json
    public static Map<String,Object> jsonToMap(String str)
    {
        Map<String,Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }

    // Aceasta functie apeleaza API-ul si intoarce datele de tip json-ul sub forma de Map.
    public static Map<String,Object> getJsonMapFromAPI(String city)
    {
        String API_KEY = "56697fbd93a575c4e6a3f639f52ca44c";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+API_KEY+"&units=metric";
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null)
                result.append(line);
            rd.close();

            result = result.deleteCharAt(result.indexOf("[",0));
            result = result.deleteCharAt(result.indexOf("]",0));
            Map<String, Object> resMap = jsonToMap(result.toString());
            return resMap;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
