package com.example.washreminderbot.weathersearch;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetWeatherFromOpenWeather implements CanHaveWeather {
    String openWeatherApi;
    String url;

    public GetWeatherFromOpenWeather() {
        openWeatherApi = System.getenv("WEATHER_API");
        url = String.format("http://api.openweathermap.org/data/2.5/" +
                "find?units=metric&lang=ru&APPID=%s", openWeatherApi);
    }

    private JSONObject getWeather(String city) {
        try {
            url += String.format("&q=%s", city);
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
            return new JSONObject(response.toString());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public List<WeatherCondition> getListOfWeather(String city) {
        var weatherJSON = getWeather(city);
        if (weatherJSON == null) {
            return null;
        }
        var result = new ArrayList(); // todo Gson
        System.out.println(weatherJSON);
        for (Object weather : weatherJSON.getJSONArray("list")) {
            JSONObject tmpJSON = (JSONObject) weather;
            String tmpRain = tmpJSON.get("rain").toString().equals("null") ? null : tmpJSON.get("rain").toString();
            String tmpSnow = tmpJSON.get("snow").toString().equals("null") ? null : tmpJSON.get("snow").toString();
            var tmpCondition = new WeatherCondition(tmpRain, tmpSnow);
            result.add(tmpCondition);
        }
        return result;
    }
}
