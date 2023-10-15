package aston.hw.model;

import java.sql.Timestamp;

/**
 * The Weather class represents weather data, including information such as the unique ID, timestamp, temperature, weather condition, and associated city ID.
 */
public class Weather {
    private Integer id;
    private Timestamp time;
    private Integer temperature;
    private String condition;
    private Integer cityId;

    /**
     * Constructs a Weather object with the specified weather data.
     *
     * @param id         the unique ID of the weather record
     * @param time       the timestamp of the weather data
     * @param temperature the temperature at the recorded time
     * @param condition   the weather condition description
     * @param cityId     the ID of the city associated with the weather data
     */
    public Weather(Integer id, Timestamp time, Integer temperature, String condition, Integer cityId) {
        this.id = id;
        this.time = time;
        this.temperature = temperature;
        this.condition = condition;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
