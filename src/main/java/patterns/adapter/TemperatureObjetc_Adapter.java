package patterns.adapter;

/**
 * Created by Nico on 13/10/2016.
 */
public class TemperatureObjetc_Adapter implements TemperatureInfo {

    CelciusTemperature celciusTemperature;

    public TemperatureObjetc_Adapter() {
        celciusTemperature = new CelciusTemperature();
    }

    @Override
    public float getTemperatureInC() {
        return celciusTemperature.getTemperature();
    }

    @Override
    public float getTemperatureInF() {
        return cToF(celciusTemperature.getTemperature());
    }

    @Override
    public void setTemperatureInC(float temperatureInC) {
        celciusTemperature.setTemperature(temperatureInC);
    }

    @Override
    public void setTemperatureInF(float temperatureInF) {
        celciusTemperature.setTemperature(fToC(temperatureInF));
    }

    private float fToC(float f) {
        return ((f - 32) * 5 / 9);
    }

    private float cToF(float c) {
        return ((c * 9 / 5) + 32);
    }
}
