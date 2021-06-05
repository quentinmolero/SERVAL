package fr.serval.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            return reader.readLine();
        }
        catch (IOException e) {
            return "";
        }
    }

    public static int readIntFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static boolean isUserChoiceNotInRangeOfChoices(int userChoice, int minimumChoiceValue, int maximumChoiceValue) {
        return userChoice >= minimumChoiceValue && userChoice <= maximumChoiceValue;
    }
}
