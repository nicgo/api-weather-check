package domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nico on 26/9/2016.
 */
public class CurrentDay {

    private Calendar date;
    private float temp;
    private String description;

    public CurrentDay(Calendar date, float temp, String description) {
        this.date = date;
        this.temp = temp;
        this.description = description;
    }

    public int getIdDescription(){
        int descrip=0;
        switch (description) {
            case "Sunny":
                descrip =1;
                break;
            case "Hazy":
                descrip =2;
                break;
            case "Partly Cloudy":
                descrip =3;
                break;
            case "Cloudy":
                descrip =4;
                break;
            case "Rainy":
                descrip =5;
                break;
            case "Stormy":
                descrip =6;
                break;
            case "Snowy":
                descrip =7;
                break;
            case "Windy":
                descrip =8;
                break;
        }
        return descrip;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CurrentDay{" +
                "date=" + date.getTime() +
                ", temp=" + temp +
                ", description=" + description +
                '}';
    }
}
