package com.example.washreminderbot.weathersearch;

import org.json.JSONObject;

import java.util.List;

public interface CanHaveWeather {
//    public String getWeather(String city);
    public List<WeatherCondition> getListOfWeather(String city);
}
