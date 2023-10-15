package aston.hw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The City class represents information about a city, including its unique ID, name, latitude, longitude, and a list of associated weather records.
 */
public class City {
    private Integer id;
    private String name;
    private Double lat;
    private Double lon;
    private List<Weather> weatherList;

    /**
     * Constructs a City object with the specified city information.
     *
     * @param id         the unique ID of the city
     * @param name       the name of the city
     * @param lat        the latitude of the city's location
     * @param lon        the longitude of the city's location
     */
    public City(Integer id, String name, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.weatherList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}
