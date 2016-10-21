package resources;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.DAOForecast;
import services.ForecastFromDb_Service;
import services.ForecastFromYahooAPI_Service;
import services.ForecastProxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.sql.SQLException;

@Path("/forecast")
public class WeatherResource {//This resources returns the entity Forecast from DB

    @Autowired
    ForecastFromYahooAPI_Service forecastFromYahooAPI_service;
    @Autowired
    ForecastFromDb_Service forecastFromDb_service;

    @GET
    @Path("/get/{city}/{region}")
    @Produces("text/plain")
    public String getYahooForecast(@PathParam("city") String city,@PathParam("region") String region) throws JSONException {

        String URL=forecastFromYahooAPI_service.getURL(city,region);
        JSONObject jsonObject=forecastFromYahooAPI_service.getJSONResponse(URL);

        return forecastFromYahooAPI_service.createForecastByJSON(jsonObject);

    }

    @GET
    @Path("/read/{city}")
    @Produces("text/plain")
    public String loadForecastFromDB(@PathParam("city") String city) throws SQLException {

        return forecastFromDb_service.getForecast(city);

    }
}
