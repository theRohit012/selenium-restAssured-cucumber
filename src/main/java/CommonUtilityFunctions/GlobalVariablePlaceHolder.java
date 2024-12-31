package CommonUtilityFunctions;

import java.util.Properties;

public class GlobalVariablePlaceHolder {
    static Properties prop = ConfigReader.initProperties();

    /**
     * @method for taking the value from config file and use in feature or other files
     * @param placeHolder
     * @return
     */
    public static String resolveGlobalVariables(String placeHolder){
        if(placeHolder.startsWith("@")){
            String key = placeHolder.substring(1);
            return prop.getProperty(key,"unknown place Holder");
        }
        return placeHolder;
    }
}
