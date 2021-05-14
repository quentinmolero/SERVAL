package fr.serval.cli;

import java.util.Scanner;

public class CLIController {
    public static int readUserChoice(int minimumChoiceValue, int maximumChoiceValue) {
        int userChoice;
        do {
            System.out.println("Quel est votre choix ? (Entrez un numéro valide)");
            userChoice = readIntFromUserInput();
        } while (!isUserChoiceNotInRangeOfChoices(userChoice, minimumChoiceValue, maximumChoiceValue));
        return userChoice;
    }

    public static String readStringUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static int readIntFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static boolean isUserChoiceNotInRangeOfChoices(int userChoice, int minimumChoiceValue, int maximumChoiceValue) {
        return userChoice >= minimumChoiceValue && userChoice <= maximumChoiceValue;
    }
}
