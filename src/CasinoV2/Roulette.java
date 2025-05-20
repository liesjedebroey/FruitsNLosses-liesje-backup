package CasinoV2;
import java.util.Random;

public class Roulette {
    // fields
    private int amountOfTimesHouseLost;
    private int payout;
    private int winningNumber;

    private boolean guidedWin;

    // constructor
    public Roulette(int amountOfTimesHouseLost, int payout) {
        this.amountOfTimesHouseLost = amountOfTimesHouseLost;
        this.payout = payout;
    }

    // method to initiate the game, decide fraud or not and then play roulette
    public void play(Player player) {
        System.out.println("\n   ♣️ Welcome at the ♥️ Roulette Table ♦️ one game is €200 ♠️ ");
        System.out.println(" ");
        System.out.println(player.getName() + " starts with €" + player.getWallet());
        System.out.println(" ");

        while (player.getWallet() >= 200 && player.getWallet() < 2000) {
            System.out.println("♣  ♠ starting a new round ♥  ♦");

            player.walletOut(200);

            shouldFailSafeBeUsed(13);

            rollTheRoulette(13, player);
        }
        if (player.getWallet() < 200) {
            System.out.println("\uD83E\uDEE4 " + player.getName() + " has no money left in the wallet.");
            System.out.println("This is where the roulette stops spinning.");
            System.out.println("----------***----------");
        } else {
            System.out.println("\uD83D\uDC8E " + player.getName() + " has reached the max limit of €2000. Congratulations!");
            System.out.println("This is where the roulette stops spinning.");
            System.out.println("----------***----------");
        }
    }

    // method to check if the house has to always win (decide fraud is needed)
    private void shouldFailSafeBeUsed(int numberChosen) {
        guidedWin = amountOfTimesHouseLost >= 3 || payout >= 500;

        if (guidedWin) {
            System.out.println("m'kay \uD83D\uDE4A");
        } else {
            System.out.println("watching \uD83D\uDE48");
        }
    }

    // method to play the roulette and gambler wins/loses. unless the house doesn't want to.
    public void rollTheRoulette (int numberChosen, Player player) {
        Random numbers = new Random();
        winningNumber = numbers.nextInt(20) + 1;

        if (guidedWin && winningNumber == numberChosen) {
            do {
                winningNumber = numbers.nextInt(20) + 1;
            } while (winningNumber == numberChosen);
        }

        System.out.println("          \uD83C\uDFB2...rolling... \uD83C\uDFB2");
        System.out.println(" ");
        System.out.println("winning number \uD83C\uDF52 " + winningNumber + " \uD83C\uDF52");

        if (numberChosen == winningNumber) {
            System.out.println("Happy Days! " + player.getName() + " won €500 \uD83C\uDF4E\uD83C\uDF4F\uD83C\uDF4D.");
            System.out.println(" ");
            player.walletIn(500);
            System.out.println(" ");
            amountOfTimesHouseLost++;
            payout += 500;
        } else {
            System.out.println(player.getName() + " chose "+ numberChosen + ". FruitsNLosses!");
            System.out.println(" ");
        }
    }
}

/*
Level 4: Roulette( kost 200 EURO per keer)
In de shouldFailSafeBeUsed(int numberChosen) methode ga je kijken of dat het huis al meer dan 3 keer heeft verloren OF dat
de payout kleiner is dan 500. Als 1 van die twee condities waar zijn, dan blijf een nieuw random nummer trekken tussen 0 en 20
totdat de winningNumber niet hetzelfde is als de numberChosen. Zoniet moet je maar 1 keer die random trekken.
In de rollTheRoulette(int moneyPutIn, int numberChosen) methode kijk je nog eens na of de winningNumber overeenkomt met de numberChosen.
Als dit het geval is, krijgt de player 500 EURO.
*/

