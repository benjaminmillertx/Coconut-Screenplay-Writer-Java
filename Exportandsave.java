import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportSave {
    public void saveScript(String script) {
        try {
            FileWriter writer = new FileWriter("script.txt");
            writer.write(script);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
