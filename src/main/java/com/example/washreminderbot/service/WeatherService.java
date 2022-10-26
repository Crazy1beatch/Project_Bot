package com.example.washreminderbot.service;

import com.example.washreminderbot.weathersearch.CanHaveWeather;
import com.example.washreminderbot.weathersearch.GetWeatherFromOpenWeather;
import com.example.washreminderbot.weathersearch.WeatherCondition;

public class WeatherService {
    CanHaveWeather weatherFromOpenWeather;

    public WeatherService() {
        weatherFromOpenWeather = new GetWeatherFromOpenWeather();
    }

    public resultOfIsWeatherGood isWeatherGood(String city) {
        var weatherCondition = weatherFromOpenWeather.getListOfWeather(city);

        var result = true;
        for (var currentDayCondition : weatherCondition) {
            if (currentDayCondition.getRainCondition() != null ||
                    currentDayCondition.getSnowCondition() != null) {
                result = false;
                break;
            }
        }
        var resultOfCheck = new resultOfIsWeatherGood(result, weatherCondition.size() == 0);
        return resultOfCheck;
    }
    public class resultOfIsWeatherGood{
        private final boolean resultOfCheck;
        private final boolean hasErrors;
        public resultOfIsWeatherGood(boolean resultOfCheck, boolean hasErrors){
            this.resultOfCheck = resultOfCheck;
            this.hasErrors = hasErrors;
        }

        public boolean getResult(){
            return this.resultOfCheck;
        }

        public boolean getErrors(){
            return hasErrors;
        }
    }
}
