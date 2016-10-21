package patterns.adapter;

/**
 * Created by Nico on 13/10/2016.
 */
public class CelciusTemperature {

    float temperatureInC;

    public CelciusTemperature() {
    }

    public float getTemperature() {
        return temperatureInC;
    }

    public void setTemperature(float temperatureInC) {
        this.temperatureInC = temperatureInC;
    }
}
