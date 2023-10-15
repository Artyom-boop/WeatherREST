package aston.hw.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The PropertiesLoader class provides a singleton instance for loading and accessing properties from a configuration file.
 * It loads the properties from the "config.properties" file using the class loader and provides a single instance for access.
 */
public class PropertiesLoader {
    private static PropertiesLoader instance;
    private Properties properties;

    /**
     * Private constructor to ensure the class is only instantiated from within.
     */
    private PropertiesLoader() {}

    /**
     * Gets the singleton instance of the PropertiesLoader class. If the instance does not exist, it loads the properties from the "config.properties" file.
     *
     * @return the singleton instance of PropertiesLoader
     * @throws RuntimeException if there is an error loading the properties file
     */
    public static PropertiesLoader getInstance() {
        if (instance != null)
            return instance;
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            instance = new PropertiesLoader();
            instance.properties = new Properties();
            instance.properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * Gets the loaded properties.
     *
     * @return the properties loaded from the configuration file
     */
    public Properties getProperties() {
        return properties;
    }
}
