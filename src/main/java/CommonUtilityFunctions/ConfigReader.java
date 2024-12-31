package CommonUtilityFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties configProp;

    /**
     * @method Read and init a properties files
     * @param configFilePath
     * @return
     */
    public static Properties initProperties() {
        configProp = new Properties();

        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/ConfigurationFiles/config.properties");
            configProp.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configProp;
    }
}