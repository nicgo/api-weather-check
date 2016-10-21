package domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nico on 26/9/2016.
 */
public class ExtendedForecast {

    private Calendar date;
    private Day day;
    private float low;
    private float high;
    private String description;

    public ExtendedForecast(Calendar date, Day day, float low, float high, String description) {
        this.date = date;
        this.day = day;
        this.low = low;
        this.high = high;
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nExtendedForecast\n" +
                "\ndate=" + date.getTime() +
                "\nday=" + day +
                "\nlow=" + low +
                "\nhigh=" + high +
                "\ndescription=" + description;
    }
}
