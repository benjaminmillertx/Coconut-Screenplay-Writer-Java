import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScreenplayApp extends Application {
    private TextArea scriptTextArea;

    @Override
    public void start(Stage primaryStage) {
        scriptTextArea = new TextArea();
        Button saveButton = new Button("Save");
        Button exportButton = new Button("Export");

        VBox root = new VBox(scriptTextArea, saveButton, exportButton);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Screenplay Writing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
