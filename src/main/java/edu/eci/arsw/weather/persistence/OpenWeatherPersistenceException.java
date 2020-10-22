package edu.eci.arsw.weather.persistence;

public class OpenWeatherPersistenceException extends Exception {

    public OpenWeatherPersistenceException(String message) {
        super(message);
    }

    public OpenWeatherPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
