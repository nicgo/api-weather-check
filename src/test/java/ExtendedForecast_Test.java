import builder.ExtendedForecast_Builder;
import domain.Day;
import domain.ExtendedForecast;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Nico on 11/10/2016.
 */
public class ExtendedForecast_Test {

    @Test
    public void test_ExtendedForecast_dataCreation(){
        ExtendedForecast_Builder extendedForecast_builder = new ExtendedForecast_Builder().create_Default();
        ExtendedForecast extendedForecast = extendedForecast_builder.create();

        Assert.assertThat(extendedForecast.getDate().get(Calendar.DAY_OF_MONTH), Is.is(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        Assert.assertThat(extendedForecast.getDay(),Is.is(Day.FRI));
        Assert.assertEquals(12,(long)extendedForecast.getLow());
        Assert.assertEquals(29,(long)extendedForecast.getHigh());
        Assert.assertEquals("Hazy",extendedForecast.getDescription());
    }

    /*
    @Test
    public void test_WriteExtendedForecastOnDB() throws SQLException {
        Forecast_Builder forecast_builder = new Forecast_Builder().create_Default();
        Forecast forecast = forecast_builder.create();

        DAOExtendedForecast daoExtendedForecast = new DAOExtendedForecast();
        daoExtendedForecast.inserInto(forecast.getExtendedForecasts().get(1),forecast);


    }*/
}
