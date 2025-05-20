package CasinoV2;

public class ClawMachine {
        private int numberOfTries;
        private int moneyInTheBank = 0;

        public ClawMachine() {
            this.numberOfTries = 0;
        }

        // Determines if player should win on this try
        public boolean readyToWin() {
            return numberOfTries == 2;
        }

        // Bonus condition, optional
        public boolean readyToWinBigTime() {
            return numberOfTries > 10;
        }

        /**
         * Runs a game round: deducts cost, checks win, updates bank.
         * @param player the player
         * @param cost cost to play the round
         * @return result message
         */
        public String playGame(Player player, int cost) {
            if (!player.walletOut(cost)) {
                return "Not enough money in your wallet.";
            }

            numberOfTries++;
            moneyInTheBank += cost;

            if (readyToWin()) {
                int payout = cost * 2;
                player.walletIn(payout);
                return "Congratulations! You won!";
            }

            if (numberOfTries == 15) {
                numberOfTries = 0;
            }

            return "Better luck next time!";
        }

        public int getMoneyInTheBank() {
            return moneyInTheBank;
        }
    }

