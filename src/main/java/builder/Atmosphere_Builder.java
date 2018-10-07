package builder;

import domain.Atmosphere;
import org.springframework.stereotype.Component;


@Component
public class Atmosphere_Builder {

    private float humidity;
    private float pressure;
    private float visibility;
    private int rising;

    public Atmosphere create() {
        return new Atmosphere(humidity,pressure,visibility,rising);
    }

    public Atmosphere_Builder create_Default() {
        this.humidity=40;
        this.pressure=70;
        this.visibility=95;
        this.rising=2;
        return this;
    }

    public Atmosphere_Builder with_Humidity(float humidity){
        this.humidity=humidity;
    return this;}

    public Atmosphere_Builder with_pressure(float pressure){
        this.pressure=pressure;
        return this;
    }

    public Atmosphere_Builder with_visibility(float visibility){
        this.visibility=visibility;
        return this;
    }

    public Atmosphere_Builder with_rising(int rising){
        this.rising=rising;
        return this;
    }


}
