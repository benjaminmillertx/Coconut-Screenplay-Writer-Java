import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class NotesFeature {
    private static final Map<Integer, String> notesMap = new HashMap<>();

    public static void openNotes(TextArea scriptTextArea) {
        Stage notesStage = new Stage();
        VBox root = new VBox();

        TextArea notesTextArea = new TextArea();
        notesTextArea.setPromptText("Write your note here...");
        Button addNoteButton = new Button("Add Note");
        ListView<String> notesListView = new ListView<>();

        addNoteButton.setOnAction(e -> {
            int caretPosition = scriptTextArea.getCaretPosition();
            String noteText = notesTextArea.getText();
            if (!noteText.trim().isEmpty()) {
                notesMap.put(caretPosition, noteText);
                updateNotesListView(notesListView);
                notesTextArea.clear();
            }
        });

        root.getChildren().addAll(new Label("Add Note:"), notesTextArea, addNoteButton, new Label("Notes:"), notesListView);
        Scene scene = new Scene(root, 400, 300);
        notesStage.setScene(scene);
        notesStage.setTitle("Notes");
        notesStage.show();
    }

    private static void updateNotesListView(ListView<String> notesListView) {
        notesListView.getItems().clear();
        for (Map.Entry<Integer, String> entry : notesMap.entrySet()) {
            notesListView.getItems().add("Position " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public static String getNotesAsText() {
        StringBuilder notesText = new StringBuilder();
        for (Map.Entry<Integer, String> entry : notesMap.entrySet()) {
            notesText.append("Position ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return notesText.toString();
    }
}
