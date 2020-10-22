package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

@Service
public interface CacheInterface {

    Weather getWeatherOfACity(String city) throws OpenWeatherPersistenceException;

    void putOnCache(Weather weather) throws OpenWeatherPersistenceException;

    void removeFromCache(Weather weather) throws OpenWeatherPersistenceException;

    boolean isOnCache(String city) throws OpenWeatherPersistenceException;
}
