package aston.hw.servlets;

import aston.hw.DAO.WeatherDAO;
import aston.hw.Utils.Utils;
import aston.hw.responses.YandexWeatherResponse;
import aston.hw.services.YandexWeatherService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * The YandexWeatherServlet class is a servlet that handles requests for Yandex weather data.
 * It allows clients to specify latitude and longitude coordinates as parameters to get weather information for a specific location.
 * The servlet responds with weather data in text/html format.
 */
@WebServlet("/weather/yandex")
public class YandexWeatherServlet extends HttpServlet {
    private static final YandexWeatherService yandexWeatherService = new YandexWeatherService();
    private static final WeatherDAO weatherDAO = new WeatherDAO();

    /**
     * Handles GET requests to retrieve weather data based on specified latitude and longitude coordinates.
     *
     * @param req  the HttpServletRequest object representing the client's request
     * @param resp the HttpServletResponse object for sending the weather data response
     * @throws IOException if an I/O error occurs during the response generation
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String lat = req.getParameter("lat");
        String lon = req.getParameter("lon");

        if (Utils.isNumeric(lat) && Utils.isNumeric(lon)) {
            yandexWeatherService.setLat(Double.parseDouble(lat));
            yandexWeatherService.setLon(Double.parseDouble(lon));
        }
        PrintWriter printWriter = resp.getWriter();
        YandexWeatherResponse yandexWeatherResponse = yandexWeatherService.request();
        printWriter.println(yandexWeatherResponse);
        printWriter.close();

        try {
            weatherDAO.addDataWeather(yandexWeatherResponse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}