package Lotto;

import java.util.Scanner;

/**
 * Main application to test the Lotto class
 */
public class LottoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lotto lotto = new Lotto();

        System.out.println("Welkom bij de Lotto Test App!");

        boolean keepPlaying = true;
        while (keepPlaying) {
            // Get user input for their lottery numbers
            System.out.println("\nVoer uw drie lotto nummers in (0-10):");
            System.out.print("Eerste nummer: ");
            int userNumber1 = getValidNumber(scanner);

            System.out.print("Tweede nummer: ");
            int userNumber2 = getValidNumber(scanner);

            System.out.print("Derde nummer: ");
            int userNumber3 = getValidNumber(scanner);

            // Get the amount of money to wager
            System.out.print("Hoeveel geld wilt u inzetten (in Euro's): ");
            int moneyPutIn = getValidMoney(scanner);

            // Manually set a high current payout for testing purposes
            // This simulates a situation where the payout is high enough for a win
            System.out.print("Wilt u een hoge payout simuleren voor test doeleinden? (ja/nee): ");
            String simulateHighPayout = scanner.next().toLowerCase();
            if (simulateHighPayout.startsWith("j")) {
                lotto.setCurrentPayout(1000); // Set high payout for testing
                System.out.println("Huidige payout is op 1000 Euro gezet voor test doeleinden.");
            } else {
                lotto.setCurrentPayout(0); // Regular payout
            }

            // Play the lottery
            System.out.println("\nLotto nummers worden getrokken...");
            int[] lottoNumbers = lotto.getNumbers(userNumber1, userNumber2, userNumber3, moneyPutIn);

            // Show results
            System.out.println("Getrokken Lotto nummers: [" + lottoNumbers[0] + ", " + lottoNumbers[1] + ", " + lottoNumbers[2] + "]");
            System.out.println("Uw nummers: [" + userNumber1 + ", " + userNumber2 + ", " + userNumber3 + "]");
            System.out.println("Heeft u gewonnen? " + lotto.getDidYouWin());
            System.out.println("Uw uitbetaling: " + lotto.getDidYouWin() + " Euro");

            // Ask if user wants to play again
            System.out.print("\nWilt u nog een keer spelen? (ja/nee): ");
            String playAgain = scanner.next().toLowerCase();
            keepPlaying = playAgain.startsWith("j");
        }

        System.out.println("Bedankt voor het spelen. Tot ziens!");
        scanner.close();
    }

    /**
     * Helper method to get a valid number between 0 and 10
     */
    private static int getValidNumber(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number >= 0 && number <= 10) {
                    return number;
                } else {
                    System.out.print("Voer een getal tussen 0 en 10 in: ");
                }
            } catch (Exception e) {
                System.out.print("Ongeldige invoer. Voer een getal tussen 0 en 10 in: ");
                scanner.next(); // Clear invalid input
            }
        }
    }

    /**
     * Helper method to get a valid money amount
     */
    private static int getValidMoney(Scanner scanner) {
        int money;
        while (true) {
            try {
                money = scanner.nextInt();
                if (money > 0) {
                    return money;
                } else {
                    System.out.print("Voer een bedrag groter dan 0 in: ");
                }
            } catch (Exception e) {
                System.out.print("Ongeldige invoer. Voer een geldig bedrag in: ");
                scanner.next(); // Clear invalid input
            }
        }
    }
}