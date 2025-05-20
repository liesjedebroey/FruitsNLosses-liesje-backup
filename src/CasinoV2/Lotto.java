package CasinoV2;

import java.util.Random;
import java.util.Scanner;

    public class Lotto {

        // Inner class to handle the core lottery game logic
        protected static class LottoCore {
            private int number1;
            private int number2;
            private int number3;
            private int currentPayout;
            private boolean didYouWin;
            private Random randomNumber = new Random();

            public LottoCore() {
                this.currentPayout = 0;
                this.didYouWin = false;
            }

            public int getCurrentPayout() {
                return currentPayout;
            }

            public void setCurrentPayout(int currentPayout) {
                this.currentPayout = currentPayout;
            }

            public boolean getDidYouWin() {
                return didYouWin;
            }

            public int getNumber1() {
                return number1;
            }

            public int getNumber2() {
                return number2;
            }

            public int getNumber3() {
                return number3;
            }

            public void rollRandomNumbers() {
                //Rolls random numbers between 0 - 10
                this.number1 = randomNumber.nextInt(11);
                this.number2 = randomNumber.nextInt(11);
                this.number3 = randomNumber.nextInt(11);
                this.didYouWin = false;
            }

            public void compareNumbers(int userNumber1, int userNumber2, int userNumber3) {
                if (userNumber1 == this.number1 || userNumber2 == this.number2 || userNumber3 == this.number3) {
                    this.didYouWin = true;
                }
            }

            public int playLotto(int userNumber1, int userNumber2, int userNumber3, int moneyPutIn) {
                // Add player's money to the jackpot
                //this.currentPayout += moneyPutIn;

                // Special cheat for 1-1-1 input
                if (userNumber1 == 1 && userNumber2 == 1 && userNumber3 == 1) {
                    // Force the random numbers to be 1-1-1
                    this.number1 = 1;
                    this.number2 = 1;
                    this.number3 = 1;
                    this.didYouWin = true;
                    return this.currentPayout;
                }
                // CHEAT CODE: If player enters the same number three times, guaranteed win
                else if (userNumber1 == userNumber2 && userNumber2 == userNumber3) {
                    rollRandomNumbers();

                    // Only set as winner if jackpot is over 500
                    if (this.currentPayout > 500) {
                        this.didYouWin = true;
                        return this.currentPayout;
                    } else {
                        this.didYouWin = false;
                        return 0;
                    }
                }

                // Roll random numbers
                rollRandomNumbers();

                // Compare player numbers with random numbers
                compareNumbers(userNumber1, userNumber2, userNumber3);

                // Check if player won
                if (this.didYouWin) {
                    // Check if current payout is high enough
                    if (this.currentPayout > 500) {
                        return this.currentPayout;
                    } else {
                        // Payout too low, re-roll until player loses
                        while (this.didYouWin) {
                            rollRandomNumbers();
                            this.didYouWin = false;
                            compareNumbers(userNumber1, userNumber2, userNumber3);
                        }
                        return 0;
                    }
                } else {
                    return 0;
                }
            }

            // Static method that can be called from the main Casino app
            public static void playLottoGame(Scanner scanner) {
                LottoCore lotto = new LottoCore();

                System.out.println("\n===============================");
                System.out.println("WELCOME TO JUICY FRUIT LOTTO!");
                System.out.println("WIN THE JACKPOT! You only need one correct number!");
                System.out.println("Price per play: 100 EURO");
                System.out.println("===============================");

                boolean keepPlaying = true;
                int jackpot = 0;

                while (keepPlaying) {
                    // Get player numbers
                    System.out.println("\nPICK YOUR NUMBERS (0-10):");
                    System.out.print("First number: ");
                    int userNumber1 = getValidNumber(scanner, 0, 10);

                    System.out.print("Second number: ");
                    int userNumber2 = getValidNumber(scanner, 0, 10);

                    System.out.print("Third number: ");
                    int userNumber3 = getValidNumber(scanner, 0, 10);

                    // Cost to play
                    int moneyPutIn = 100;

                    // Add to jackpot
                    jackpot += moneyPutIn;
                    lotto.setCurrentPayout(jackpot);

                    // Run the lottery and get results
                    System.out.println("\nDrawing the lucky numbers...");
                    int winnings = lotto.playLotto(userNumber1, userNumber2, userNumber3, moneyPutIn);

                    // Show results
                    System.out.println("Your numbers: [" + userNumber1 + ", " + userNumber2 + ", " + userNumber3 + "]");
                    System.out.println("Lucky numbers: [" + lotto.getNumber1() + ", " + lotto.getNumber2() + ", " + lotto.getNumber3() + "]");

                    if (lotto.getDidYouWin()) {
                        System.out.println("🎉 PING! PING! PING! PING! 🎉");
                        System.out.println("🏆 YOU HAVE WON THE JACKPOT!! 🏆");
                        System.out.println("YOU GET PAID " + winnings + " EURO");
                        jackpot = 0; // Reset jackpot
                        lotto.setCurrentPayout(0);
                    } else {
                        System.out.println("You were so close...");
                        System.out.println("You Win: 0 Euro");
                        System.out.println("Current jackpot: " + jackpot + " EURO!!");
                    }

                    // Ask to play again
                    System.out.print("\nPlay another round? (yes/no): ");
                    String playAgain = scanner.next().toLowerCase();

                    if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                        // Try to keep player playing
                        System.out.println("Are you sure? The jackpot is now " + jackpot + " EURO!");
                        System.out.print("Play another round? (yes/no): ");
                        playAgain = scanner.next().toLowerCase();

                        if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                            keepPlaying = false;
                            System.out.println("Thanks for playing Juicy Fruit Lotto!");
                        }
                    }
                }
            }

            // Helper method for input validation
            private static int getValidNumber(Scanner scanner, int min, int max) {
                int number;
                while (true) {
                    try {
                        number = scanner.nextInt();
                        if (number >= min && number <= max) {
                            return number;
                        } else {
                            System.out.print("Please enter a number between " + min + " and " + max + ": ");
                        }
                    } catch (Exception e) {
                        System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
                        scanner.next(); // Clear invalid input
                    }
                }
            }
        }
    }

