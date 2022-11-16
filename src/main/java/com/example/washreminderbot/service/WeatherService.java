package com.example.washreminderbot.service;

import com.example.washreminderbot.weathersearch.CanHaveWeather;

public class WeatherService {
    CanHaveWeather weatherApi;

    public WeatherService(CanHaveWeather weather) {
        weatherApi = weather;
    }

    public resultOfIsWeatherGood isWeatherGood(String city) {
        var weatherCondition = weatherApi.getListOfWeather(city);

        var result = true;
        for (var currentDayCondition : weatherCondition) {
            if (currentDayCondition.getRainCondition() != null ||
                    currentDayCondition.getSnowCondition() != null) {
                result = false;
                break;
            }
        }
        return new resultOfIsWeatherGood(result, weatherCondition.size() == 0 || weatherCondition != null);
    }
    // Result<>
    // Result.Ok(...)
    // Result.Fail(...)

//    public class Result<T> {
//        public Result(T value, boolean hasError) {
//
//        }
//    }

    public static class resultOfIsWeatherGood{ // Result...
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
