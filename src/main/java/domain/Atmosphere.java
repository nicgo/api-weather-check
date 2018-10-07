package domain;

/**
 * Created by Nico on 26/9/2016.
 */
public class Atmosphere {

    private float humidity;
    private float pressure;
    private float visibility;
    private int rising; //changing conditions probability

    public Atmosphere(float humidity, float pressure, float visibility, int rising) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.rising = rising;
    }



    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getRising() {
        return rising;
    }

    public void setRising(int rising) {
        this.rising = rising;
    }

    @Override
    public String  toString() {
        return "Atmosphere{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", visibility=" + visibility +
                ", rising=" + rising +
                '}';
    }
}
