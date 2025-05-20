package CasinoV2;

import java.util.Random;

public class SlotMachine {

    private int currentPayout;
    private int odds;
    private final String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
    private final Random random = new Random();

    private Player player; //  Connected player

    public void connectPlayer(Player player) {
        this.player = player;
        System.out.println("🔗 Player connected: " + player.getName());
    }

    public int playTheSlots(int moneyPaid) {
        // Checking if the player has money
        if (player == null) {
            System.out.println("No player connected.");
            return 0;
        }

        if (!player.walletOut(moneyPaid)) {
            System.out.println(" Cannot play. Not enough funds.");
            return 0;
        }

        currentPayout += moneyPaid;
        whatOddsToGive();

        String[] row = spinRow();
        printRow(row);

        int number = random.nextInt(odds);

        if (number == 7) {
            currentPayout -= 3000;
            System.out.println("💰 Jackpot! You won €3,000!");
            player.walletIn(3000);
            return 3000;
        }

        if (row[0].equals(row[1]) && row[1].equals(row[2])) {
            int payout = switch (row[0]) {
                case "🍒" -> 200;
                case "🍉" -> 300;
                case "🍋" -> 400;
                case "🔔" -> 500;
                case "⭐" -> 600;
                default -> 0;
            };
            currentPayout -= payout;
            System.out.println("🎉 Three identical symbols! You win " + payout + "€");
            player.walletIn(payout);
            return payout;
        }

        if (row[0].equals(row[1]) || row[1].equals(row[2]) || row[0].equals(row[2])) {
            String matched = findMatchingSymbol(row);
            int payout = switch (matched) {
                case "🍒" -> 25;
                case "🍉" -> 35;
                case "🍋" -> 50;
                case "🔔" -> 60;
                case "⭐" -> 75;
                default -> 0;
            };
            currentPayout -= payout;
            System.out.println("😊 Two matches! You win " + payout + "€");
            player.walletIn(payout);
            return payout;
        }

        System.out.println("🙁 Oops, you lost. Try again!");
        return 0;
    }

    public int getCurrentPayout() {
        return currentPayout;
    }

    public String[] spinRow() {
        String[] row = new String[3];
        for (int i = 0; i < 3; i++) {
            row[i] = symbols[random.nextInt(symbols.length)];
        }
        return row;
    }

    public void printRow(String[] row) {
        System.out.println("***************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("***************");
    }

    private void whatOddsToGive() {
        if (currentPayout > 1000) {
            odds = 10;
        } else if (currentPayout > 900) {
            odds = 100;
        } else if (currentPayout > 800) {
            odds = 1000;
        } else {
            odds = 10000;
        }
    }

    private String findMatchingSymbol(String[] row) {
        if (row[0].equals(row[1]) || row[0].equals(row[2])) {
            return row[0];
        } else {
            return row[1];
        }
    }

    public class MainApp {
        public static void main(String[] args) {
            SlotMachine machine = new SlotMachine();

            // Create a player
            Player player = new Player("Alex", 1000);
            machine.connectPlayer(player);

            // 💸 Играем
            machine.playTheSlots(300);
            machine.playTheSlots(500);
            machine.playTheSlots(250);

            System.out.println(" Current payout in machine: €" + machine.getCurrentPayout());
        }
    }
}

