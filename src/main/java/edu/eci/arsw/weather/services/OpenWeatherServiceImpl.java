package edu.eci.arsw.weather.services;


import edu.eci.arsw.weather.model.Weather;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("openWeatherServices")
public class OpenWeatherServiceImpl implements OpenWeatherServices {

    @Autowired
    @Qualifier("connection")
    HttpConnectionService httpConnectionService = null;


    @Override
    public Weather getWeatherOfACity(String nombre) throws OpenWeatherServiceException {
        try {
            return httpConnectionService.getWeatherOfACity(nombre);
        } catch (OpenWeatherServiceException e) {
            throw new OpenWeatherServiceException(e.getMessage(), e);
        }
    }
}
