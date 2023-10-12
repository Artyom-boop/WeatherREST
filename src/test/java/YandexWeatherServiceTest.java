import aston.hw.model.YandexWeatherResponse;
import aston.hw.services.YandexWeatherService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class YandexWeatherServiceTest {

    private static final YandexWeatherService yandexWeatherService = new YandexWeatherService();

    @Test
    public void requestTest() throws IOException {
        YandexWeatherResponse response = yandexWeatherService.request();
        checkResponse(response);
    }

    @Test
    public void requestChangeLatAndLonTest() throws IOException {
        yandexWeatherService.setLat(10.0);
        yandexWeatherService.setLon(10.0);
        YandexWeatherResponse response = yandexWeatherService.request();
        checkResponse(response);

    }

    private void checkResponse(YandexWeatherResponse response) {
        assertNotNull(response);
        assertNotNull(response.getCity());
        assertNotNull(response.getLat());
        assertNotNull(response.getLon());
        assertNotNull(response.getCondition());
        assertNotNull(response.getTemperature());
    }
}
