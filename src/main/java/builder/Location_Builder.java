package builder;

import domain.Location;
import org.springframework.stereotype.Component;

@Component
public class Location_Builder {
    private String city;
    private String region;
    private String country;

    public Location create(){
        Location location = new Location(city,region,country);
        return location;
    }

    public Location_Builder create_Default(){
        this.city="Vcp";
        this.region="Region";
        this.country="Country";
        return this;
    }

    public Location_Builder with_City(String city){
        this.city=city;
        return this;
    }

    public Location_Builder with_Region(String region){
        this.region=region;
        return this;
    }

    public Location_Builder with_Country(String country){
        this.country=country;
        return this;
    }
}
