import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        int defaultLightQuantity = 20;
        LightController lightController = new LightController(Arrays.asList(Colour.RED,Colour.GREEN,Colour.WHITE),defaultLightQuantity);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        try {
            while (true  && !exit) {
                System.out.println("Please input a light pattern [ sequence, colour, alternate] or type exit to quit.");
                String line = scanner.nextLine().trim();
                System.out.printf("User input was: %s%n", line);
                if (line.equalsIgnoreCase("exit")){
                    exit = true;
                }else{
                    lightController.startSequence(line);
                }
            }
        } catch(IllegalStateException e) {
            System.out.println("System.in was closed; exiting");
        }
    }
}
