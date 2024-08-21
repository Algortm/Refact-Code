import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The InputValidator class handles user input validation.
 */
public class InputValidator {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a number between 1 and 9, and validates the input.
     *
     * @return a valid byte input between 1 and 9.
     */
    public byte getValidNumber() {
        byte input = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a number (1-9): ");
            try {
                input = scanner.nextByte();
                if (input >= 1 && input <= Generator.COUNT_ELEMENTS) {
                    validInput = true;
                } else {
                    System.out.println("Input must be a number between 1 and 9. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next();
            }
        }

        return input;
    }
}
