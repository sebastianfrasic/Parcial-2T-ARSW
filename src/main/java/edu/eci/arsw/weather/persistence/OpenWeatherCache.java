package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("openWeatherCache")
public class OpenWeatherCache implements CacheInterface {

    public static final long MINUTES_IN_CACHE = 5;
    public List<Weather> weatherArrayList = new CopyOnWriteArrayList<>();


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

    public void putOnCache(Weather weather) {
        weatherArrayList.add(weather);
    }

    public void removeFromCache(Weather weather) {
        weatherArrayList.remove(weather);
    }

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
