import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScreenplayApp extends Application {
    private TextArea scriptTextArea;

    @Override
    public void start(Stage primaryStage) {
        scriptTextArea = new TextArea();
        Button saveButton = new Button("Save");
        Button exportButton = new Button("Export");
        Button collaborationButton = new Button("Collaboration");
        Button dashShortcutsButton = new Button("Dash Shortcuts");
        Button outlineEditorButton = new Button("Outline Editor");
        Button sceneReorderingButton = new Button("Scene Reordering");
        Button notesButton = new Button("Notes");
        Button colorCodingButton = new Button("Color Coding");

        saveButton.setOnAction(e -> saveScript());
        exportButton.setOnAction(e -> exportScript());
        collaborationButton.setOnAction(e -> {
            Collaboration.connectToServer();
            receiveCollaborationUpdates();
        });
        dashShortcutsButton.setOnAction(e -> DashShortcuts.initialize(scriptTextArea));
        outlineEditorButton.setOnAction(e -> OutlineEditor.openOutlineEditor(scriptTextArea));
        sceneReorderingButton.setOnAction(e -> SceneReordering.openSceneReordering(scriptTextArea));
        notesButton.setOnAction(e -> NotesFeature.openNotes(scriptTextArea));
        colorCodingButton.setOnAction(e -> ColorCoding.openColorCoding(scriptTextArea));

        VBox root = new VBox(scriptTextArea, saveButton, exportButton, collaborationButton,
                dashShortcutsButton, outlineEditorButton, sceneReorderingButton,
                notesButton, colorCodingButton);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Screenplay Writing App");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Collaboration.disconnect());
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void saveScript() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Script");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(scriptTextArea.getText());
                writer.write("\n\nNotes:\n" + NotesFeature.getNotesAsText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void exportScript() {
        // Logic for exporting script
        System.out.println("Script exported successfully.");
    }

    private void receiveCollaborationUpdates() {
        new Thread(() -> {
            while (true) {
                String receivedMessage = Collaboration.receiveMessage();
                if (receivedMessage != null) {
                    // Update UI with received message on the JavaFX Application Thread
                    javafx.application.Platform.runLater(() -> scriptTextArea.setText(receivedMessage));
                }
            }
        }).start();
    }
}
