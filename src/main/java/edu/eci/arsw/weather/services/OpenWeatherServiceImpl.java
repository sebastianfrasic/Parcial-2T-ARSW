package edu.eci.arsw.weather.services;


import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.CacheInterface;
import edu.eci.arsw.weather.persistence.OpenWeatherPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service("openWeatherServices")
public class OpenWeatherServiceImpl implements OpenWeatherServices {

    @Autowired
    @Qualifier("connection")
    HttpConnectionService httpConnectionService = null;

    @Autowired
    @Qualifier("openWeatherCache")
    CacheInterface cacheInterface;


    @Override
    public Weather getWeatherOfACity(String nombre) throws OpenWeatherServiceException {
        Weather weather;
        try {
            if (cacheInterface.isOnCache(nombre)) {
                weather = cacheInterface.getWeatherOfACity(nombre);
            } else {
                weather = httpConnectionService.getWeatherOfACity(nombre);
                weather.setLocalDateTime(LocalDateTime.now());
                cacheInterface.putOnCache(weather);
            }
        } catch (OpenWeatherServiceException | OpenWeatherPersistenceException e) {
            throw new OpenWeatherServiceException(e.getMessage(), e);
        }
        return weather;
    }
}
