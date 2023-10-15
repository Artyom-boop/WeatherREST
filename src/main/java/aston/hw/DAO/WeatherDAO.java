package aston.hw.DAO;

import aston.hw.Utils.PropertiesLoader;
import aston.hw.model.City;
import aston.hw.model.Weather;
import aston.hw.responses.YandexWeatherResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The WeatherDAO class provides data access methods for interacting with a PostgreSQL database to store weather-related data.
 * It allows adding weather data, retrieving cities, and managing cities and their weather information.
 */
public class WeatherDAO {
    private final Connection connection;

    /**
     * Constructs a WeatherDAO object and establishes a connection to the PostgreSQL database.
     * Loads database connection properties from a configuration file using PropertiesLoader.
     *
     * @throws RuntimeException if there is an error in database driver loading or database connection
     */
    public WeatherDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
            Properties properties = propertiesLoader.getProperties();
            connection = DriverManager.getConnection
                    (properties.getProperty("db.host"),
                            properties.getProperty("db.login"),
                            properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds weather data to the database for a specific city.
     *
     * @param yandexWeatherResponse the weather data to be added
     * @throws SQLException if there is an error during database operations
     */
    public void addDataWeather(YandexWeatherResponse yandexWeatherResponse) throws SQLException {
        int cityId = getCityIdForName(yandexWeatherResponse.getCity());
        if (cityId < 0) {
            addCity(yandexWeatherResponse.getCity(), yandexWeatherResponse.getLat(), yandexWeatherResponse.getLon());
            cityId = getCityIdForName(yandexWeatherResponse.getCity());
        }
        String query = "insert into weather(time, temperature, condition, city_id) values (?, ?, ?, ?)";
        if (cityId > 0) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                statement.setInt(2, yandexWeatherResponse.getTemperature());
                statement.setString(3, yandexWeatherResponse.getCondition());
                statement.setInt(4, cityId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new SQLException();
        }
    }

    /**
     * Retrieves a list of cities along with their weather information from the database.
     *
     * @return a list of City objects representing cities and their weather data
     * @throws RuntimeException if there is an error during database operations
     */
    public List<City> getCites() {
        List<City> cities = new ArrayList<>();
        String query = "select * from city";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDouble("latitude"), resultSet.getDouble("longitude"));
                city.setWeatherList(getWeatherListForCity(city.getId()));
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    /**
     * Adds a new city to the database with the specified name, latitude, and longitude.
     *
     * @param name the name of the city to be added
     * @param lat  the latitude of the city
     * @param lon  the longitude of the city
     * @throws RuntimeException if there is an error during database operations
     */
    public void addCity(String name, Double lat, Double lon) {
        String query = "insert into city(name, latitude, longitude) values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, lat);
            statement.setDouble(3, lon);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of weather records for a specific city from the database.
     *
     * @param cityId the ID of the city to retrieve weather data for
     * @return a list of Weather objects representing weather records for the city
     * @throws RuntimeException if there is an error during database operations
     */
    private List<Weather> getWeatherListForCity(Integer cityId) {
        String query = "select * from weather where city_id=?";
        List<Weather> weatherList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cityId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Weather weather = new Weather(resultSet.getInt("id"),
                                            resultSet.getTimestamp("time"),
                                            resultSet.getInt("temperature"),
                                            resultSet.getString("condition"),
                                            resultSet.getInt("city_id"));
                weatherList.add(weather);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weatherList;
    }

    /**
     * Gets the ID of a city based on its name.
     *
     * @param name the name of the city to retrieve the ID for
     * @return the ID of the city if found, or -1 if not found
     * @throws RuntimeException if there is an error during database operations
     */
    private int getCityIdForName(String name) {
        String query = "select * from city where name=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
