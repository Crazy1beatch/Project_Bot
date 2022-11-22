package com.example.washreminderbot.weathersearch;

import com.example.washreminderbot.service.GetFromOpenWeather;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetWeatherFromOpenWeather implements CanHaveWeather {
    private final String url;
    public GetWeatherFromOpenWeather() {
        url = String.format("http://api.openweathermap.org/data/2.5/" +
                "find?units=metric&lang=ru&APPID=%s", System.getenv("WEATHER_API"));
        this.openWeather = new GetFromOpenWeather();
    }
    GetFromOpenWeather openWeather;
    private JSONObject getWeather(String city) {
            var tmpUrl = String.format("%s&q=%s", url, city);
            return openWeather.getResponse(tmpUrl);
    }

    @Override
    public List<WeatherCondition> getListOfWeather(String city) {
        var weatherJSON = getWeather(city);
        if (weatherJSON == null) {
            return null;
        }
        var result = new ArrayList(); // todo Gson
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
