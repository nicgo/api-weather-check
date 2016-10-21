package patterns.adapter;

/**
 * Created by Nico on 13/10/2016.
 */
public interface TemperatureInfo {

    public float getTemperatureInF();

    public void setTemperatureInF(float temperatureInF);

    public float getTemperatureInC();

    public void setTemperatureInC(float temperatureInC);
}
