package domain;

/**
 * Created by Nico on 28/9/2016.
 */
public enum Day {
    FRI("Fri"),
    MON("Mon"),
    SAT("Sat"),
    SUN("Sun"),
    THU("Thu"),
    TUE("Tue"),
    WED("Wed");

    String value;

    Day(String value) {
    }

    public Day fromValue(String unmappedDay) {
        Day thisDay = null;
        for (Day day : Day.values()) {
            if (day.value.equalsIgnoreCase(unmappedDay)) {
                thisDay = day;
            }
        }
        return thisDay;
    }

}
