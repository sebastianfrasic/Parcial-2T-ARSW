package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Clase que realiza la persitencia del caché en el servidor mediante un arreglo concurrente durante 5 minutos
 */
@Service("openWeatherCache")
public class OpenWeatherCache implements CacheInterface {

    public static final long MINUTES_IN_CACHE = 5;
    public final List<Weather> weatherArrayList = new CopyOnWriteArrayList<>();

    /**
     * Obtiene los datos del clima de una ciudad desde el caché
     * @param city Ciudad
     * @return Obtiene los datos del clima de una ciudad desde el caché
     */
    public Weather getWeatherOfACity(String city) {
        Weather weather = null;
        for (Weather w : weatherArrayList) {
            if (city.equals(w.getCity())) {
                weather = w;
                break;
            }
        }
        return weather;
    }

    /**
     * Coloca en caché los datos del clima de una ciudad
     * @param weather
     */
    public void putOnCache(Weather weather) {
        weatherArrayList.add(weather);
    }

    /**
     * Remueve del caché los datos del clima de una ciudad
     * @param weather
     */
    public void removeFromCache(Weather weather) {
        weatherArrayList.remove(weather);
    }

    /**
     * Dice si una ciudad está en el caché
     * @param city Ciudad enviada
     * @return Verdadero si la ciudad está en caché, falso de lo contrario
     */
    public boolean isOnCache(String city) {
        boolean isOnCache = true;
        Weather weather1 = getWeatherOfACity(city);
        if (weather1 == null) {
            isOnCache = false;
        } else if (LocalDateTime.now().isAfter(weather1.getLocalDateTime().plusMinutes(MINUTES_IN_CACHE))) {
            removeFromCache(weather1);
            isOnCache = false;
        }
        return isOnCache;
    }
}
