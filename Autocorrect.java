import java.util.HashMap;
import java.util.Map;

public class Autocorrect {
    private Map<String, String> dictionary;

    public Autocorrect() {
        // Initialize dictionary with common screenplay terms
        dictionary = new HashMap<>();
        dictionary.put("INT.", "INT. - Interior");
        dictionary.put("EXT.", "EXT. - Exterior");
        // Add more terms as needed
    }

    public String suggestCorrection(String word) {
        return dictionary.getOrDefault(word, word);
    }
}
