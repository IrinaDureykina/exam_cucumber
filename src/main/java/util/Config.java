package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE_PATH = "src/test/resources/application.properties";

    private static final Properties prop = new Properties();

    static {

        try (InputStream input = Files.newInputStream(Paths.get(CONFIG_FILE_PATH))) {
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperties(String key) {
        return prop.getProperty(key);
    }
}
