package edu.eci.arsw.weather.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.model.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("connection")
public class HttpConnectionService {

    /**
     * Realiza conexión mediante Unirest al API de OpenWeather
     * @param nombre de la ciudad
     * @return Los datos climáticos filtrados de una ciudad
     * @throws OpenWeatherServiceException Si la ciudad no existe o si hay un error de conexión con el API
     */
    public Weather getWeatherOfACity(String nombre) throws OpenWeatherServiceException {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest
                    .get("https://api.openweathermap.org/data/2.5/weather?q=" + nombre + "&appid=e6d589177c6d5fbf9467ccb98fab7dfb")
                    .asJson();
        } catch (UnirestException e) {
            throw new OpenWeatherServiceException("Error de conexion con Open Weather", e);
        }
        JSONObject jsonObject = response.getBody().getObject();
        if (jsonObject.getInt("cod") == 404) {
            throw new OpenWeatherServiceException("Ciudad no encontrada");
        }
        return getWeather(jsonObject);
    }

    /**
     * Toma el JSON obtenido del API de Open Weather y crea otro objeto solamente con algunos de esos datos
     * @param jsonObject JSON del API de Open Weather
     * @return Objeto filtrado
     */
    private Weather getWeather(JSONObject jsonObject) {
        String countryCode = jsonObject.getJSONObject("sys").getString("country");
        String city = jsonObject.getString("name");
        String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = Math.round((jsonObject.getJSONObject("main").getDouble("temp") - 273.15) * 10d) / 10d;
        double thermalSensation = Math.round((jsonObject.getJSONObject("main").getDouble("feels_like") - 273.15) * 10d) / 10d;
        double latitud = Math.round(jsonObject.getJSONObject("coord").getDouble("lat") * 10d) / 10d;
        double longitud = Math.round(jsonObject.getJSONObject("coord").getDouble("lon") * 10d) / 10d;

        return new Weather(countryCode, city, weather, description, temperature, thermalSensation, latitud, longitud);
    }

}