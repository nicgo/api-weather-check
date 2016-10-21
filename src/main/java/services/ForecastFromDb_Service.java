package services;

import domain.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.DAOForecast;

import java.sql.SQLException;

/**
 * Created by Nico on 20/10/2016.
 */
@Component
public class ForecastFromDb_Service {

    @Autowired
   private DAOForecast daoForecast;

    public String getForecast(String city) throws SQLException {
       Forecast forecast=daoForecast.selectFromWhere(city);
        if (forecast!=null){
            return forecast.toString();
        }
        return "NULL FORECAST";
    }


}
