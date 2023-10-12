package aston.hw.model;

/**
 * The YandexWeatherResponse class represents weather data obtained from the Yandex Weather API.
 * It includes information such as the city name, latitude, longitude, temperature, and weather condition.
 */
public class YandexWeatherResponse {
    private Double lat;
    private Double lon;
    private String condition;
    private Integer temperature;
    private String city;

    /**
     * Constructs a YandexWeatherResponse object with the specified weather data.
     *
     * @param city        the name of the city for which the weather data is provided
     * @param lat         the latitude of the location
     * @param lon         the longitude of the location
     * @param temperature the temperature in the location
     * @param condition   the weather condition description
     */
    public YandexWeatherResponse(String city, Double lat, Double lon, Integer temperature, String condition) {
        this.city = city;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.condition = condition;
    }

    /**
     * Gets the temperature in the location.
     *
     * @return the temperature value
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature in the location.
     *
     * @param temperature the new temperature value
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the latitude of the location.
     *
     * @return the latitude value
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Sets the latitude of the location.
     *
     * @param lat the new latitude value
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Gets the longitude of the location.
     *
     * @return the longitude value
     */
    public Double getLon() {
        return lon;
    }

    /**
     * Sets the longitude of the location.
     *
     * @param lon the new longitude value
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Gets the weather condition description.
     *
     * @return the weather condition description
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the weather condition description.
     *
     * @param condition the new weather condition description
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets the name of the city for which the weather data is provided.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the name of the city for which the weather data is provided.
     *
     * @param city the new city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns a string representation of the weather data, including city name, latitude, longitude, temperature, and condition.
     *
     * @return a formatted string with weather information
     */
    @Override
    public String toString() {
        return "Weather in "+ city + "\n" +
                " Latitude:" + lat + '\n' +
                " Longitude: " + lon + '\n' +
                " temperature: " + temperature + '\n' +
                " condition: " + condition;
    }
}
