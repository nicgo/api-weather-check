package builder;

import domain.Wind;
import org.springframework.stereotype.Component;

@Component
public class Wind_Builder {
    private float direction;
    private float velocity;

    public Wind create(){
        Wind wind=new Wind(direction,velocity);
        return wind;
    }

    public Wind_Builder create_Default(){
        this.direction=50;
        this.velocity=100;
        return this;
    }

    public Wind_Builder with_direction(float direction){
        this.direction=direction;
        return this;
    }

    public Wind_Builder with_velocity(float velocity){
        this.velocity=velocity;
    return this;}
}
