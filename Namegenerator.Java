
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OnlineNameGenerator {
    private static final String API_URL = "https://uinames.com/api/?amount=1000";

    public static void main(String[] args) {
        List<String> generatedNames = fetchNamesFromAPI();
        for (String name : generatedNames) {
            System.out.println(name);
        }
    }

    public static List<String> fetchNamesFromAPI() {
        List<String> names = new ArrayList<>();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name") + " " + jsonObject.getString("surname");
                names.add(name);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }
}
