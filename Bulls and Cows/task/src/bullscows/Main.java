package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String previousGenerateSecretNumber(int secretLength) {
        int low = (int) Math.pow(10, secretLength - 1);
        int high = (int) Math.pow(10, secretLength);

        Random random = new Random();

        long pseudoRandomNumber = random.nextInt(high - low + 1) + low;

        String randomNumber = String.valueOf(pseudoRandomNumber);

        StringBuilder sb = new StringBuilder();

        for (int i = randomNumber.length() - 1; i >= 0; i--) {
            int ch = Integer.parseInt(String.valueOf(randomNumber.charAt(i)));

            if(sb.indexOf(String.valueOf(ch)) != -1) {
                ch = sb.length() == 0 ? 1 : 0;
                while (sb.length() < secretLength && sb.indexOf(String.valueOf(ch)) != -1) {
                    ch++;
                }
            }

            if(sb.length() < secretLength) {
                sb.append(String.valueOf(ch));
            } else {
                break;
            }
        }

        return sb.toString();
    }

    public static String generateSecretNumber(int secretLength, int characterLength) {
        final String digits = "0123456789";
        final String alphabets = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder secretNumber = new StringBuilder();

        StringBuilder availableCharacters = new StringBuilder();
        String secretCharacters1 = "", secretCharacters2 = "";

        if (characterLength <= 10) {
            availableCharacters.append(digits.substring(0, characterLength));
            secretCharacters1 = String.format("0-%c", digits.charAt(characterLength-1));
            boolean hasFound = false;

            Random random = new Random();

            while (!hasFound) {
                char ch = availableCharacters.charAt(random.nextInt(characterLength));

                if (secretNumber.indexOf(Character.toString(ch)) == -1) {
                    secretNumber.append(ch);
                }

                if (secretNumber.length() == secretLength) {
                    hasFound = true;
                }
            }
        } else {
            availableCharacters.append(digits);
            availableCharacters.append(alphabets.substring(0, characterLength - 10));
            secretCharacters2 = String.format("a-%c", alphabets.charAt(characterLength - 10 - 1));

            boolean hasFound = false;

            Random random = new Random();

            while (hasFound == false) {
                char ch = availableCharacters.charAt(random.nextInt(characterLength));

                if (secretNumber.indexOf(Character.toString(ch)) == -1) {
                    secretNumber.append(ch);
                }

                if (secretNumber.length() == secretLength) {
                    hasFound = true;
                }
            }

        }

        String dots = "";
        for (int i = 0; i < secretLength; i++) {
            dots += "*";
        }

        if(secretCharacters2.isEmpty()) {
            System.out.printf("The secret is prepared: %s (0-%c).\n", dots, digits.charAt(characterLength - 1));
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%c).\n", dots, alphabets.charAt(characterLength - 10 - 1));
        }

        return secretNumber.toString();
    }

    public static void printGrade(int cows, int bulls) {
        if(cows == 0) {
            System.out.printf("Grade: %d bull.\n", bulls);
        } else if (bulls == 0) {
            System.out.printf("Grade: %d cows.\n", cows);
        } else {
            System.out.printf("Grade: %d bull and %d cow.\n", bulls, cows);
        }
    }

    public static void playGuessingGame(String secretNumber, Scanner scanner) {

        boolean hasGuessed = false;
        int turn = 1;

        while (!hasGuessed) {
            int cows = 0, bulls = 0;

            System.out.printf("Turn %d:\n", turn);
            String guess = scanner.next();

            if(guess.equals(secretNumber)) {
                hasGuessed = true;
                bulls = secretNumber.length();
            } else {
                for (int i = 0; i < guess.length(); i++) {
                    char ch = guess.charAt(i);

                    if (secretNumber.indexOf(ch) >= 0) {
                        if (i == secretNumber.indexOf(ch)) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }
            printGrade(cows, bulls);
            turn++;
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static int[] validateInput(Scanner sc) {
        boolean wrongSecretLength = false,
                wrongPossibleCharacters = false;

        String secretLengthStr = "";
        String possibleCharactersStr = "";

        int[] empty = {};

        int secretLength = 0, possibleCharacters = 0;

        try {
            System.out.println("Input the length of the secret code:");
            secretLengthStr = sc.nextLine();
            secretLength = Integer.parseInt(secretLengthStr);
        } catch (Exception e) {
            wrongSecretLength = true;
        }

        if (wrongSecretLength) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", secretLengthStr);
            return empty;
        }

        try {
            System.out.println("Input the number of possible symbols in the code:");
            possibleCharactersStr = sc.nextLine();
            possibleCharacters = Integer.parseInt(possibleCharactersStr);
        } catch (Exception e) {
            wrongPossibleCharacters = true;
        }

        if (wrongPossibleCharacters) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", possibleCharactersStr);
            return empty;
        }

        int[] validInputs = { secretLength, possibleCharacters };

        return validInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] inputs = validateInput(sc);

        if (inputs.length != 0) {
            int secretLength = inputs[0];
            int possibleCharacters = inputs[1];

            if(secretLength <= 36 && possibleCharacters <= 36) {

                if (possibleCharacters < secretLength || possibleCharacters == 0 || secretLength == 0) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with a %d unique symbols.\n", secretLength, possibleCharacters);
                } else {
                    String secretNumber = generateSecretNumber(secretLength, possibleCharacters);
                    System.out.println("Okay, let's start a game!");
                    playGuessingGame(secretNumber, sc);
                }
            } else {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }
        }
    }
}
