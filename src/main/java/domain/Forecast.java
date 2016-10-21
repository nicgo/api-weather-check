package domain;

import java.util.List;

/**
 * Created by Nico on 26/9/2016.
 */
public class Forecast {

    private Location location;
    private CurrentDay day;
    private Atmosphere atmosphere;
    private Wind wind;
    private List<ExtendedForecast> extendedForecasts;

    public Forecast(Location location, CurrentDay day, Atmosphere atmosphere, Wind wind, List<ExtendedForecast> extendedForecasts) {
        this.location = location;
        this.day = day;
        this.atmosphere = atmosphere;
        this.wind = wind;
        this.extendedForecasts = extendedForecasts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentDay getDay() {
        return day;
    }

    public void setDay(CurrentDay day) {
        this.day = day;
    }


    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<ExtendedForecast> getExtendedForecasts() {
        return extendedForecasts;
    }

    public void setExtendedForecasts(List<ExtendedForecast> extendedForecasts) {
        this.extendedForecasts = extendedForecasts;
    }

    @Override
    public String toString() {
        return "Forecast:\n" +
                "location=" + location +
                "\nday=" + day +
                "\natmosphere=" + atmosphere +
                "\nwind=" + wind +
                "\nextendedForecasts=" + extendedForecasts;
    }
}

