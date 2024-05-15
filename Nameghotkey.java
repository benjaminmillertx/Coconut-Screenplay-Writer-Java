import java.util.List;
import java.util.Scanner;

public class NameGeneratorTrigger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type 'nameg' to generate names:");
        String input = scanner.nextLine();

        if (input.trim().equalsIgnoreCase("nameg")) {
            List<String> generatedNames = OnlineNameGenerator.fetchNamesFromAPI();
            for (String name : generatedNames) {
                System.out.println(name);
            }
        } else {
            System.out.println("Invalid input. Type 'nameg' to generate names.");
        }

        scanner.close();
    }
}
