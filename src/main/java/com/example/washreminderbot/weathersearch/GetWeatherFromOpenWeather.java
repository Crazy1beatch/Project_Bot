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
        var result = new ArrayList();
        System.out.println(weatherJSON);
//        JSONObject js = {"count":4,"cod":"200","message":"accurate","list":[{"dt":1666721283,"rain":null,"coord":{"lon":37.6156,"lat":55.7522},"snow":null,"name":"Moscow","weather":[{"icon":"02n","description":"небольшая облачность","main":"Clouds","id":801}],"main":{"temp":1.52,"temp_min":-2.14,"grnd_level":1003,"humidity":78,"pressure":1021,"sea_level":1021,"feels_like":-0.76,"temp_max":2.91},"id":524901,"clouds":{"all":20},"sys":{"country":"RU"},"wind":{"deg":146,"speed":2.06}},{"dt":1666722131,"rain":null,"coord":{"lon":-117.0002,"lat":46.7324},"snow":null,"name":"Moscow","weather":[{"icon":"04d","description":"пасмурно","main":"Clouds","id":804}],"main":{"temp":6.15,"temp_min":4.51,"humidity":83,"pressure":1020,"feels_like":2.29,"temp_max":8.03},"id":5601538,"clouds":{"all":100},"sys":{"country":"US"},"wind":{"deg":240,"speed":6.17}},{"dt":1666722117,"rain":null,"coord":{"lon":-75.5185,"lat":41.3368},"snow":null,"name":"Moscow","weather":[{"icon":"04d","description":"пасмурно","main":"Clouds","id":804}],"main":{"temp":17.2,"temp_min":15.81,"grnd_level":964,"humidity":90,"pressure":1017,"sea_level":1017,"feels_like":17.33,"temp_max":18.94},"id":5202009,"clouds":{"all":100},"sys":{"country":"US"},"wind":{"deg":150,"speed":1.87}},{"dt":1666721374,"rain":null,"coord":{"lon":37.6067,"lat":55.7617},"snow":null,"name":"Moscow","weather":[{"icon":"02n","description":"небольшая облачность","main":"Clouds","id":801}],"main":{"temp":1.21,"temp_min":-2.23,"grnd_level":1001,"humidity":78,"pressure":1021,"sea_level":1021,"feels_like":-1.12,"temp_max":2.81},"id":524894,"clouds":{"all":21},"sys":{"country":"RU"},"wind":{"deg":145,"speed":2.06}}]}result.add(weatherJSON.get("count"));
        for (Object weather : weatherJSON.getJSONArray("list")) {
            JSONObject tmpJSON = (JSONObject) weather;
            String tmpRain = tmpJSON.get("rain").toString().equals("null") ? null : tmpJSON.get("rain").toString();
            String tmpSnow = tmpJSON.get("snow").toString().equals("null") ? null : tmpJSON.get("snow").toString();
            var tmpCondition = new WeatherCondition(tmpRain, tmpSnow);
            result.add(tmpCondition);
        }
        return result; // создать класс, и возвращать экземпляр
    }
}
