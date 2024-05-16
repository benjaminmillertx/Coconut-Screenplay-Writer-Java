import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class NameGenerator {
    public static void main(String[] args) {
        try {
            // Make HTTP request to the API
            URL url = new URL("https://api.example.com/names");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONArray namesArray = new JSONArray(response.toString());

            // Extract first and last names
            String[] firstNames = new String[namesArray.length()];
            String[] lastNames = new String[namesArray.length()];
            for (int i = 0; i < namesArray.length(); i++) {
                JSONObject nameObject = namesArray.getJSONObject(i);
                firstNames[i] = nameObject.getString("first_name");
                lastNames[i] = nameObject.getString("last_name");
            }

            // Generate random names
            int randomIndex = (int) (Math.random() * namesArray.length());
            String randomFirstName = firstNames[randomIndex];
            String randomLastName = lastNames[randomIndex];
            System.out.println("Random Name: " + randomFirstName + " " + randomLastName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
