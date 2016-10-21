package domain;

/**
 * Created by Nico on 26/9/2016.
 */
public class Wind {


    private float direction;
    private float velocity;

    public Wind(float direction, float velocity) {

        this.direction = direction;
        this.velocity = velocity;
    }


    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "direction=" + direction +
                ", velocity=" + velocity +
                '}';
    }
}
