package edu.eci.arsw.weather.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("connection")
public class HttpConnectionService {

    public String getWeatherOfACity(String nombre) throws OpenWeatherServiceException {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest
                    .get("https://api.openweathermap.org/data/2.5/weather?q=" + nombre + "&appid=e6d589177c6d5fbf9467ccb98fab7dfb")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = response.getBody().getObject();
        return jsonObject.toString();
    }

}
