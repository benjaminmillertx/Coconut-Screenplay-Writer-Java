import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

        // Top Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exportMenuItem = new MenuItem("Export");
        fileMenu.getItems().addAll(saveMenuItem, exportMenuItem);
        menuBar.getMenus().addAll(fileMenu);

        saveMenuItem.setOnAction(e -> saveScript());
        exportMenuItem.setOnAction(e -> exportScript());

        // Sidebar
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(10));
        sidebar.setSpacing(10);
        sidebar.setStyle("-fx-background-color: #3b5998;"); // Facebook blue
        sidebar.setPrefWidth(200);

        Button collaborationButton = createSidebarButton("Collaboration");
        Button dashShortcutsButton = createSidebarButton("Dash Shortcuts");
        Button outlineEditorButton = createSidebarButton("Outline Editor");
        Button sceneReorderingButton = createSidebarButton("Scene Reordering");
        Button notesButton = createSidebarButton("Notes");
        Button colorCodingButton = createSidebarButton("Color Coding");

        collaborationButton.setOnAction(e -> {
            Collaboration.connectToServer();
            receiveCollaborationUpdates();
        });
        dashShortcutsButton.setOnAction(e -> DashShortcuts.initialize(scriptTextArea));
        outlineEditorButton.setOnAction(e -> OutlineEditor.openOutlineEditor(scriptTextArea));
        sceneReorderingButton.setOnAction(e -> SceneReordering.openSceneReordering(scriptTextArea));
        notesButton.setOnAction(e -> NotesFeature.openNotes(scriptTextArea));
        colorCodingButton.setOnAction(e -> ColorCoding.openColorCoding(scriptTextArea));

        sidebar.getChildren().addAll(collaborationButton, dashShortcutsButton, outlineEditorButton,
                sceneReorderingButton, notesButton, colorCodingButton);

        // Main Content Area
        VBox mainContent = new VBox();
        mainContent.setPadding(new Insets(10));
        mainContent.setSpacing(10);

        Label scriptLabel = new Label("Script:");
        mainContent.getChildren().addAll(scriptLabel, scriptTextArea);

        // Main Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(mainContent);

        Scene scene = new Scene(mainLayout, 800, 600);

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

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-weight: bold;");
        return button;
    }
}
