package builder;

import domain.CurrentDay;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class CurrentDay_Builder {

    private Calendar date;
    private float temp;
    private String description;

    public CurrentDay create(){
        CurrentDay currentDay= new CurrentDay(date,temp,description);
        return currentDay;
    }

    public CurrentDay_Builder create_Default(){
        this.date =Calendar.getInstance();
        this.temp=27;
        this.description ="Sunny";
        return this;
    }

    public CurrentDay_Builder with_date(Calendar date){
        this.date=date;
        return this;
    }

    public CurrentDay_Builder whit_temp(float temp){
        this.temp=temp;
        return this;
    }

    public CurrentDay_Builder whit_description(String description){
        this.description=description;
        return this;
    }
}

