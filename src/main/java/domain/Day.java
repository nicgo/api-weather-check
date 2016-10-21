package domain;

/**
 * Created by Nico on 28/9/2016.
 */
public enum Day {
    Mon, Tue,Wed,Thu,Fri,Sat,Sun;

    public Day getEnum(String day)
    {
        Day thisDay=null;
        switch (day){
            case "Sun": thisDay=Day.Sun;
            break;
            case "Mon": thisDay=Day.Mon;
                break;
            case "Tue": thisDay=Day.Tue;
                break;
            case "Wed": thisDay=Day.Wed;
                break;
            case "Thu": thisDay=Day.Thu;
                break;
            case "Fri": thisDay=Day.Fri;
                break;
            case "Sat": thisDay=Day.Sat;
                break;
        }
        return thisDay;
    }

}
