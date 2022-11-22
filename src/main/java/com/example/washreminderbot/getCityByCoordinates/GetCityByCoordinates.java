package com.example.washreminderbot.getCityByCoordinates;

import com.example.washreminderbot.service.GetFromOpenWeather;
import org.json.JSONObject;

public class GetCityByCoordinates implements CanGetCityByCoordinates {
    String openWeatherApi;

    public GetCityByCoordinates() {
        openWeatherApi = System.getenv("WEATHER_API");
        this.openWeather = new GetFromOpenWeather();
    }

    GetFromOpenWeather openWeather;
    private JSONObject getCityByCoordinates(double lat, double lon) {
        var url = String.format("http://api.openweathermap.org/geo/1.0/reverse?lat=%s&lon=%s&appid=%s",
                lat, lon, openWeatherApi);
        return openWeather.getResponse(url);
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
