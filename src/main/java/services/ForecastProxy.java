package services;

import builder.*;
import domain.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nico on 21/10/2016.
 */
@Component
public class ForecastProxy {


    public Forecast getForecastbyJSON(JSONObject jsonObject) throws JSONException {

        Forecast forecast;

        Location location;
        CurrentDay currentDay;
        Wind wind;
        Atmosphere atmosphere;
        List <ExtendedForecast> extendedForecastList=new ArrayList<ExtendedForecast>();

        //get JSON master
        JSONObject master= jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("channel");

        //get JSONObjects for domain
        JSONObject JSONcurrentDay=master.getJSONObject("item").getJSONObject("condition");
        JSONObject JSONlocation=master.getJSONObject("location");
        JSONObject JSONwind=master.getJSONObject("wind");
        JSONObject JSONatmosphere=master.getJSONObject("atmosphere");
        JSONArray JSONextendedForecast = master.getJSONObject("item").getJSONArray("forecast");

        //creates Object location
        Location_Builder location_builder = new Location_Builder().with_Country(JSONlocation.getString("country"))
                .with_Region(JSONlocation.getString("region"))
                .with_City(JSONlocation.getString("city"));
        location=location_builder.create();


        //Parse date from JSON to Calendar type
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm a Z", Locale.US);
        try {
            cal.setTime(sdf.parse(JSONcurrentDay.getString("date")));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Creates Object CurrentDay
        CurrentDay_Builder currentDay_builder=new CurrentDay_Builder().with_date(cal)
                .whit_temp(JSONcurrentDay.getLong("temp"))
                .whit_description(JSONcurrentDay.getString("text"));
        currentDay=currentDay_builder.create();

        //Creates Object Wind
        Wind_Builder wind_builder = new Wind_Builder().with_direction(JSONwind.getLong("direction"))
                .with_velocity(JSONwind.getLong("speed"));
        wind=wind_builder.create();

        //Creates Object Atmosphere
        Atmosphere_Builder atmosphere_builder = new Atmosphere_Builder().with_Humidity(JSONatmosphere.getLong("humidity"))
                .with_pressure(JSONatmosphere.getLong("pressure"))
                .with_rising(JSONatmosphere.getInt("rising"))
                .with_visibility(JSONatmosphere.getLong("visibility"));
        atmosphere=atmosphere_builder.create();

        for (int i = 0; i < JSONextendedForecast.length(); ++i) {
            JSONObject rec = JSONextendedForecast.getJSONObject(i);
            String date = rec.getString("date");
            String day = rec.getString("day");
            float high = rec.getLong("high");
            float low = rec.getLong("low");
            String description = rec.getString("text");

            //Parse date from JSON to Calendar type
            Calendar calEXT = Calendar.getInstance();
            SimpleDateFormat sdfEXT = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            try {
                calEXT.setTime(sdfEXT.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Parse day from JSON to Day type
            Day dayExtFore=null;
            for (Day days:Day.values()) {
                if (day.equals(days.name())){
                    dayExtFore=days.fromValue(day);
                }
            }

            ExtendedForecast_Builder extendedForecast_builder=new ExtendedForecast_Builder().with_date(calEXT)
                    .with_day(dayExtFore)
                    .with_high(high)
                    .with_low(low)
                    .with_description(description);
            extendedForecastList.add(extendedForecast_builder.create());
        }

        Forecast_Builder forecast_builder=new Forecast_Builder().whit_Day(currentDay)
                .whit_Location(location)
                .with_Atmosphere(atmosphere)
                .with_Wind(wind)
                .with_Extend_Fore_List(extendedForecastList);
        forecast=forecast_builder.create();

        return forecast;
    }

}
