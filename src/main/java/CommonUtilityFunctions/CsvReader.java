package CommonUtilityFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvReader {

    /**
     * @method Read a CSV File
     * @param path
     * @return
     */
    public static List<Map<String, String>> readCsv(String path) {
        List<Map<String, String>> data = new ArrayList<>();

        String line;
        String[] headers = null;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            if ((line = br.readLine()) != null) {
                String str = line.replace("\"", "").trim();
                headers = str.split(",");
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    if (i < values.length) {
                        row.put(headers[i], values[i]);
                    } else {
                        row.put(headers[i], "");
                    }
                }
                data.add(row);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
