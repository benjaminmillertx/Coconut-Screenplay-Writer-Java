import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SceneReordering {
    private static final Pattern SCENE_PATTERN = Pattern.compile("((INT\\.|EXT\\.) [A-Z .-]+ - [A-Z .-]+)");

    public static void openSceneReordering(TextArea scriptTextArea) {
        Stage stage = new Stage();
        ObservableList<String> scenes = extractScenes(scriptTextArea.getText());
        ListView<String> listView = new ListView<>(scenes);

        listView.setOnDragDetected(event -> {
            if (!listView.getSelectionModel().isEmpty()) {
                int selectedIdx = listView.getSelectionModel().getSelectedIndex();
                listView.startDragAndDrop(TransferMode.MOVE).setContent(null);
                event.consume();
            }
        });

        listView.setOnDragOver(event -> {
            if (event.getGestureSource() != listView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        listView.setOnDragDropped(event -> {
            if (event.getGestureSource() == listView) {
                int selectedIdx = listView.getSelectionModel().getSelectedIndex();
                String draggedItem = listView.getItems().remove(selectedIdx);
                int dropIdx = listView.getSelectionModel().getSelectedIndex();
                listView.getItems().add(dropIdx, draggedItem);
                event.setDropCompleted(true);
                event.consume();
            }
        });

        listView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                updateScriptText(scriptTextArea, listView.getItems());
            }
            event.consume();
        });

        VBox root = new VBox(listView);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Reorder Scenes");
        stage.show();
    }

    private static ObservableList<String> extractScenes(String scriptText) {
        ObservableList<String> scenes = FXCollections.observableArrayList();
        Matcher matcher = SCENE_PATTERN.matcher(scriptText);
        while (matcher.find()) {
            scenes.add(matcher.group(1));
        }
        return scenes;
    }

    private static void updateScriptText(TextArea scriptTextArea, ObservableList<String> scenes) {
        StringBuilder newText = new StringBuilder();
        for (String scene : scenes) {
            newText.append(scene).append("\n");
        }
        scriptTextArea.setText(newText.toString());
    }
}
