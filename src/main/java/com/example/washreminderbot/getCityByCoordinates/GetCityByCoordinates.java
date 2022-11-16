package com.example.washreminderbot.getCityByCoordinates;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCityByCoordinates implements CanGetCityByCoordinates {
    String openWeatherApi;
    String url;

    public GetCityByCoordinates() {
        openWeatherApi = System.getenv("WEATHER_API");
    }

    private JSONObject getCityByCoordinates(double lat, double lon) {
        try {
            url = String.format("http://api.openweathermap.org/geo/1.0/reverse?lat=%s&lon=%s&appid=%s",
                    lat, lon, openWeatherApi);
            var obj = new URL(url);
            var connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            var in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            var response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            var JSONArrayCity = new JSONArray(response.toString());
            return (JSONObject) JSONArrayCity.get(0);
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public String getCity(double lat, double lon) {
        var citiesJSON = getCityByCoordinates(lat, lon);
        if (citiesJSON == null) {
            return null;
        }
        var list_cities = citiesJSON.getJSONObject("local_names");
        return list_cities.getString("ru");
    }
}
