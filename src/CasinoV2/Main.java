package CasinoV2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // create person with name and wallet.
        Player person01 = new Player("Manfred", 1900);
        Player person02 = new Player("Janice", 20);

// play the casino
        Scanner scanner = new Scanner(System.in);
        boolean exitCasino = false;

        System.out.println("===================================");
        System.out.println("WELCOME TO THE GRAND CASINO");
        System.out.println("===================================");

        while (!exitCasino) {
            displayMainMenu();
            int game = getGame();

            switch (game) {
                case 1:
                    // ClawMachine
                    ClawMachine claw = new ClawMachine();

                    for (int i = 1; i <= 15; i++) {
                        System.out.println(claw.playGame(person01, 1)); // 1 Euro for one Game
                        System.out.println("Ready To Win: " + claw.readyToWinBigTime());
                        System.out.println("Ready To Win Big Gift: " + claw.getMoneyInTheBank());
                        System.out.println("Money In The Bank: " + claw.getMoneyInTheBank());
                        System.out.println("--------");
                    }
                    break;
                case 2:
                    // SlotMachine
                    SlotMachine machine = new SlotMachine();

                    int win = machine.playTheSlots(1000);  // пробуем сыграть
                    System.out.println("Result: " + (win > 0 ? "You won €" + win : "You lost"));
                    System.out.println("Current payout: " + machine.getCurrentPayout());
                    break;
                case 3:
                    // Lotto
                    Lotto.LottoCore.playLottoGame(scanner);
                    break;
                case 4:
                    // Roulette
                    Roulette roulette01 = new Roulette(1, 300);
                    roulette01.play(person01);
                    roulette01.play(person02);
                    break;
                case 5:
                    // Exit from the Casino, stop playing
                    exitCasino = true;
                    System.out.println("♣  ♠ sad to see you go ♥  ♦");
                    System.out.println("Thank you for visiting the Grand Casino. Goodbye!");
                    break;
            }
        }
        scanner.close();
    }

    private static void displayMainMenu () {
        System.out.println("\n♣️  ♥️ CASINO GAMES ♦️  ♠️");
        System.out.println("1. Grab for toys \uD83E\uDDF8 with the ClawMachine");
        System.out.println("2. Let the \uD83C\uDF52\uD83C\uDF47\uD83C\uDF50 decide your fate at the SlotMachine");
        System.out.println("3. Lucky fruits in the Lottery \uD83C\uDF49");
        System.out.println("4. 'rien ne va plus' \uD83D\uDCAB Spin the Roulette");
        System.out.println("5. Stop playing... see you next time! \uD83E\uDD29");
        System.out.print("\nPlease choose a game by entering the corresponding number, or stop playing: ");
        System.out.println();
    }

    private static int getGame (){
        Scanner scanner = new Scanner(System.in);
        int game;
        while (true) {
            try {
                game = scanner.nextInt();
                if (game >= 1 && game <= 5) {
                    return game;
                } else {
                    System.out.print("Please enter a number between 1 and 5: ");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Clear invalid input
            }
            scanner.close();
        }
    }

}

