import builder.CurrentDay_Builder;
import builder.Forecast_Builder;
import domain.*;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import persistence.DAOForecast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nico on 11/10/2016.
 */
public class Forecast_Test {

    @Test
    public void test_Forecast_dataCreationByDefault(){
        Forecast_Builder forecast_builder = new Forecast_Builder().create_Default();
        Forecast forecast= forecast_builder.create();

        Location location = forecast.getLocation();
        CurrentDay day=forecast.getDay();
        Atmosphere atmosphere=forecast.getAtmosphere();
        Wind wind=forecast.getWind();
        List<ExtendedForecast> extendedForecasts=forecast.getExtendedForecasts();

        Assert.assertEquals("Arg",location.getCountry());
        Assert.assertEquals("Cba",location.getRegion());
        Assert.assertEquals("Vcp",location.getCity());

        Assert.assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH),day.getDate().get(Calendar.DAY_OF_MONTH));//is it the same day from today?
        Assert.assertEquals(27,(long)day.getTemp());
        Assert.assertThat(day.getDescription(), Is.is("Cloudy"));

        Assert.assertEquals(40,(long)atmosphere.getHumidity());
        Assert.assertEquals(70,(long)atmosphere.getPressure());
        Assert.assertEquals(95,(long)atmosphere.getVisibility());
        Assert.assertEquals(2,(int) atmosphere.getRising());

        Assert.assertEquals(50,(long)wind.getDirection());
        Assert.assertEquals(100,(long)wind.getVelocity());

        Assert.assertEquals(3,extendedForecasts.size());
        for (ExtendedForecast ext_fore: extendedForecasts)
        {
            Assert.assertThat(ext_fore.getDay(), IsNull.notNullValue());
            Assert.assertThat(ext_fore.getDate(), IsNull.notNullValue());
            Assert.assertThat(ext_fore.getDescription(),IsNull.notNullValue());
            Assert.assertEquals(04,(long)ext_fore.getLow());
            Assert.assertEquals(12,(long)ext_fore.getHigh());
        }
    }

    /*
    @Test
    public void test_WriteForecastOnDB () throws SQLException {
        Forecast_Builder forecast_builder = new Forecast_Builder().create_Default();
        Forecast forecast=forecast_builder.create();
        CurrentDay_Builder newday = new CurrentDay_Builder().create_Default();
        DAOForecast daoForecast = new DAOForecast();
        daoForecast.inserInto(forecast);
    }
    */
}
