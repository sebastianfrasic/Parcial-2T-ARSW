
package edu.eci.arsw.weather.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.services.OpenWeatherServiceException;
import edu.eci.arsw.weather.services.OpenWeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/weather")
public class OpenWeatherController {

    @Autowired
    @Qualifier("openWeatherServices")
    private OpenWeatherServices openWeatherServices;


    @GetMapping("/{nombre}")
    public ResponseEntity<?> getWeatherOfACity(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(openWeatherServices.getWeatherOfACity(nombre), HttpStatus.ACCEPTED);
        } catch (OpenWeatherServiceException | UnirestException ex) {
            Logger.getLogger(OpenWeatherController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
