import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorCoding {

    public static void openColorCoding(TextArea scriptTextArea) {
        Stage colorStage = new Stage();
        VBox root = new VBox();

        ColorPicker colorPicker = new ColorPicker();
        Button applyColorButton = new Button("Apply Color");

        applyColorButton.setOnAction(e -> {
            Color selectedColor = colorPicker.getValue();
            String colorCode = String.format("#%02X%02X%02X",
                    (int)(selectedColor.getRed() * 255),
                    (int)(selectedColor.getGreen() * 255),
                    (int)(selectedColor.getBlue() * 255));
            applyColorToSelection(scriptTextArea, colorCode);
        });

        root.getChildren().addAll(colorPicker, applyColorButton);
        Scene scene = new Scene(root, 300, 200);
        colorStage.setScene(scene);
        colorStage.setTitle("Color Coding");
        colorStage.show();
    }

    private static void applyColorToSelection(TextArea textArea, String color) {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            String replacement = String.format("[color=%s]%s[/color]", color, selectedText);
            textArea.replaceSelection(replacement);
        }
    }
}
