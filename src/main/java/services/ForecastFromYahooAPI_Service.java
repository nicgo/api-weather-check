package services;

import domain.Forecast;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by Nico on 21/10/2016.
 */
@Component
public class ForecastFromYahooAPI_Service {

    @Autowired
    ForecastProxy forecastProxy;

    //prepares the URL to get the JSON from that city and Region
    public String getURL(String city, String region )
    {
        String URL_START="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22";
        String URL_MIDDLE="%2C%20";
        String URL_END="%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        String URL= URL_START+ city.trim() +URL_MIDDLE+ region.trim() +URL_END;

        return URL;
    }



    //Parse the string URL to JSON
    public JSONObject getJSONResponse(String URL)
    {
        JsonReader jsonReader=new JsonReader();
        JSONObject jsonObject=null;
        try {
            jsonObject=jsonReader.readJsonFromUrl(URL);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String createForecastByJSON (JSONObject jsonObject) throws JSONException {

        Forecast forecast=forecastProxy.getForecastbyJSON(jsonObject);

        return forecast.toString();
    }




}

