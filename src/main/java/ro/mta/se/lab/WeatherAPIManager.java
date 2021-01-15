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

public class WeatherAPIManager
{
    public static Map<String,Object> jsonToMap(String str)
    {
        Map<String,Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }

    public static Map<String,Object> getStringFromJson(String city)
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
