package edu.eci.arsw.weather.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Weather {

    private String countryCode;
    private String city;
    private String weather;
    private String description;
    private double temperatura;
    private double thermalSensation;
    private double latitud;
    private double longitud;
    private LocalDateTime localDateTime;

    public Weather() {
    }


    public Weather(String countryCode, String city, String weather, String description, double temperatura, double thermalSensation, double latitud, double longitud) {
        this.countryCode = countryCode;
        this.city = city;
        this.weather = weather;
        this.description = description;
        this.temperatura = temperatura;
        this.thermalSensation = thermalSensation;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getThermalSensation() {
        return thermalSensation;
    }

    public void setThermalSensation(double thermalSensation) {
        this.thermalSensation = thermalSensation;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather1 = (Weather) o;
        return Double.compare(weather1.temperatura, temperatura) == 0 &&
                Double.compare(weather1.thermalSensation, thermalSensation) == 0 &&
                Double.compare(weather1.latitud, latitud) == 0 &&
                Double.compare(weather1.longitud, longitud) == 0 &&
                Objects.equals(countryCode, weather1.countryCode) &&
                Objects.equals(city, weather1.city) &&
                Objects.equals(weather, weather1.weather) &&
                Objects.equals(description, weather1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, city, weather, description, temperatura, thermalSensation, latitud, longitud);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "countryCode='" + countryCode + '\'' +
                ", city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", description='" + description + '\'' +
                ", temperatura=" + temperatura +
                ", thermalSensation=" + thermalSensation +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
