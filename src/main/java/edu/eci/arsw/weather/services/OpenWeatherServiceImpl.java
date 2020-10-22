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

    /**
     * Obtiene los datos del clima de una ciudad. Cuando se consulta una ciudad, esta permanece en caché durante 5 minutos. Al pasar 5 minutos se elimina del caché. Si la ciudad no está en caché se consulta directamente del API de Open Weather
     *
     * @param nombre La ciudad
     * @return los datos del clima de la ciudad
     * @throws OpenWeatherServiceException Si ocurre un error con la conexión con el API externo o si ocurre algo con el caché
     */
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
