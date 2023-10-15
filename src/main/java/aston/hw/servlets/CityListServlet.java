package aston.hw.servlets;

import aston.hw.DAO.WeatherDAO;
import aston.hw.model.City;
import aston.hw.model.Weather;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/weather/city-list")
public class CityListServlet extends HttpServlet {
    private static final WeatherDAO weatherDAO = new WeatherDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        List<City> cityList = weatherDAO.getCites();
        PrintWriter printWriter = resp.getWriter();

        for (City city: cityList) {
            printWriter.println("<B>" + city.getName() + " latitude: " + city.getLat() +
                    " longitude: " + city.getLon() + "</B><p>");
            for (Weather weather: city.getWeatherList()) {
                printWriter.println("time: " + weather.getTime() + " temperature: " + weather.getTemperature()
                + " condition: " + weather.getCondition() + "<br>");
            }
            printWriter.println("<p>");
        }
        printWriter.close();
    }
}
