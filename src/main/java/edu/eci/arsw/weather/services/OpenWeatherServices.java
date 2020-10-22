package edu.eci.arsw.weather.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.model.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public interface OpenWeatherServices {

    Weather getWeatherOfACity(String nombre) throws OpenWeatherServiceException, UnirestException;

}
