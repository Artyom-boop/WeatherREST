package aston.hw.services;

import aston.hw.model.YandexWeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.IOException;

/**
 * The YandexWeatherService class provides functionality to request weather data using the Yandex Weather API.
 */
public class YandexWeatherService {
    private static final String URI_API = "https://api.weather.yandex.ru/v2/forecast";

    /**
     * Specify the API Key Test Tariff
     */
    private static final String API_KEY = "KEY";
    private static final String LANG = "en_US";

    /**
     * lat and lon latitude and longitude, default coordinates of Saint Petersburg
     */
    private Double lat = 59.93428;
    private Double lon = 30.3351;

    /**
     * Performs a request for weather data using the Yandex Weather API.
     *
     * @return a YandexWeatherResponse object containing weather data
     * @throws IOException if an I/O error occurs during the request
     */
    public YandexWeatherResponse request() throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet(URI_API + "?lang=" + LANG + "&lat=" + lat + "&lon=" + lon);
            httpGet.addHeader("X-Yandex-API-Key", API_KEY);
            String responseFromApi = httpClient.execute(httpGet,
                    response -> EntityUtils.toString(response.getEntity())
            );
            return extractData(responseFromApi);
        }
    }

    /**
     * Gets the current latitude value for weather data requests.
     *
     * @return the latitude value
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Sets the latitude value for weather data requests.
     *
     * @param lat the latitude value
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Gets the current longitude value for weather data requests.
     *
     * @return the longitude value
     */
    public Double getLon() {
        return lon;
    }

    /**
     * Sets the longitude value for weather data requests.
     *
     * @param lon the longitude value
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Extracts weather data from the JSON response from the API and creates a YandexWeatherResponse object.
     *
     * @param allDataResponse the JSON response from the API
     * @return a YandexWeatherResponse object containing weather data
     * @throws JsonProcessingException if an error occurs during JSON processing
     */
    private YandexWeatherResponse extractData(String allDataResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(allDataResponse);
        Integer temperature = root.at("/fact/temp").asInt();
        String condition = root.at("/fact/condition").asText();
        String city = root.at("/geo_object/locality/name").asText();
        return new YandexWeatherResponse(city, lat, lon, temperature, condition);
    }
}
