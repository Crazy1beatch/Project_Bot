package com.example.washreminderbot.weathersearch;

public class WeatherCondition {
    private final String rainCondition;
    private final String snowCondition;

    public WeatherCondition(String rainCondition, String snowCondition) {
        this.rainCondition = rainCondition;
        this.snowCondition = snowCondition;
    }

    public String getSnowCondition() {
        return this.snowCondition;
    }

    public String getRainCondition() {
        return this.rainCondition;
    }
}
