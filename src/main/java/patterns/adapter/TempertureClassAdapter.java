package patterns.adapter;

/**
 * Created by Nico on 13/10/2016.
 */
public class TempertureClassAdapter extends CelciusTemperature implements TemperatureInfo {
    @Override
    public float getTemperatureInC() {
        return temperatureInC;
    }

    @Override
    public float getTemperatureInF() {
        return cToF(temperatureInC);
    }

    @Override
    public void setTemperatureInC(float temperatureInC) {
        this.temperatureInC = temperatureInC;
    }

    @Override
    public void setTemperatureInF(float temperatureInF) {
        this.temperatureInC = fToC(temperatureInF);
    }

    private float fToC(float f) {
        return ((f - 32) * 5 / 9);
    }

    private float cToF(float c) {
        return ((c * 9 / 5) + 32);
    }

}

