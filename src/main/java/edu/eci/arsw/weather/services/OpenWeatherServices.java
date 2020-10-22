package edu.eci.arsw.weather.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public interface OpenWeatherServices {

    String getWeatherOfACity(String nombre) throws OpenWeatherServiceException, UnirestException;

}
