package edu.eci.arsw.weather.test;



import edu.eci.arsw.weather.OpenWeatherApplication;
import edu.eci.arsw.weather.persistence.OpenWeatherPersistenceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OpenWeatherApplication.class})
public class OpenWeatherTest {

    @Test
    public void doSomething() throws OpenWeatherPersistenceException {

    }


}
