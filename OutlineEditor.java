import javafx.scene.control.TextArea;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutlineEditor {

    public static void generateOutline(TextArea scriptTextArea, TextArea outlineTextArea) {
        String scriptText = scriptTextArea.getText();
        StringBuilder outline = new StringBuilder();

        Pattern pattern = Pattern.compile("((INT\\.|EXT\\.) [A-Z .-]+ - [A-Z .-]+)");
        Matcher matcher = pattern.matcher(scriptText);

        while (matcher.find()) {
            outline.append(matcher.group(1)).append("\n");
        }

        outlineTextArea.setText(outline.toString());
    }

    public static void openOutlineEditor(TextArea scriptTextArea) {
        Stage outlineStage = new Stage();
        TextArea outlineTextArea = new TextArea();
        outlineTextArea.setEditable(false);

        generateOutline(scriptTextArea, outlineTextArea);

        VBox outlineRoot = new VBox(outlineTextArea);
        Scene outlineScene = new Scene(outlineRoot, 600, 400);
        outlineStage.setTitle("Screenplay Outline");
        outlineStage.setScene(outlineScene);
        outlineStage.show();
    }
}
