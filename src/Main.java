import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("------------Welcome to simple calculator-----------");
        System.out.println("  To add a number write + and the number you want");
        System.out.println("To subtract a number write - and the number you want");
        System.out.println("        To go one operation back write back");
        System.out.println("  To end program and save all operations write end");
        System.out.println();
        Calculator calculator = new Calculator();
        boolean fileIsEmpty = calculator.checkIfFileIsEmpty();
        if (!fileIsEmpty) {
            calculator.fillOperations();
        }
        String input = "go";
        System.out.println("Starting number: " + calculator.getMemory());
        while (!input.startsWith("end")) {
            input = Console.readStringFromStdin("Enter: ");
            if (input.startsWith("+")) {
                calculator.addNumber(input);
            } else if (input.startsWith("-")) {
                calculator.subtractNumber(input);
            } else if (input.startsWith("back")) {
                calculator.doOperationBack();
            } else if (input.startsWith("end")) {
                calculator.saveAllOperations();
                break;
            }
        }
    }
}
