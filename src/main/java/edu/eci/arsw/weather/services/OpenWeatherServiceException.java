package edu.eci.arsw.weather.services;


public class OpenWeatherServiceException extends Exception {

    public OpenWeatherServiceException(String message) {
        super(message);
    }

    public OpenWeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
