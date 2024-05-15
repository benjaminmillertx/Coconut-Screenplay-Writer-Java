import java.util.Scanner;

public class ScreenplayAppWithSceneGenerator {
    private static final String SCENE_TRIGGER = "sceneg";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Screenplay Writing App!");

        while (true) {
            System.out.print("Type your screenplay (type 'sceneg' to generate a new scene, 'exit' to quit):\n");
            StringBuilder screenplay = new StringBuilder();

            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                if (line.trim().equalsIgnoreCase(SCENE_TRIGGER)) {
                    generateScene();
                    continue;
                }
                screenplay.append(line).append("\n");
            }

            System.out.println("Your screenplay:\n" + screenplay.toString());

            System.out.print("Do you want to continue writing? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Thank you for using the Screenplay Writing App!");
        scanner.close();
    }

    private static void generateScene() {
        System.out.println("New scene generated:");
        System.out.println("INT. - Interior");
        System.out.println("Description of the scene goes here...");
        System.out.println("END OF SCENE");
    }
}
