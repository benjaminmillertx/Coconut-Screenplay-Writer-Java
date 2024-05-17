import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class DashShortcuts {
    private static final Map<String, String> shortcuts = new HashMap<>();

    static {
        shortcuts.put("-j", "JOHN DOE");
        shortcuts.put("-s", "SARAH SMITH");
        shortcuts.put("-ext", "EXT. LOCATION - DAY");
        shortcuts.put("-int", "INT. LOCATION - NIGHT");
        // Add more shortcuts as needed
    }

    public static void initialize(TextArea textArea) {
        textArea.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().isWhitespaceKey() || event.getCode().isNavigationKey()) {
                return;
            }

            String text = textArea.getText();
            String[] words = text.split("\\s+");
            if (words.length > 0) {
                String lastWord = words[words.length - 1];
                if (shortcuts.containsKey(lastWord)) {
                    String replacement = shortcuts.get(lastWord);
                    text = text.substring(0, text.lastIndexOf(lastWord)) + replacement;
                    textArea.setText(text);
                    textArea.positionCaret(text.length());
                }
            }
        });
    }
}
