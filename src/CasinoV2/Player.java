package CasinoV2;

public class Player {
        private final String name;
        private int wallet;

        public Player(String name, int wallet) {
            this.name = name;
            this.wallet = wallet;
        }

        public String getName() {        return name;    }
        public int getWallet() {        return wallet;    }

        public void walletOut(int amount) {
            wallet -= amount;
            System.out.println("€" + amount + " taken from the wallet \uD83D\uDCB8. current balance: €"+ getWallet());
        }
        public void walletIn(int amount) {
            wallet += amount;
            System.out.println("€" + amount + " added to the wallet \uD83D\uDCB0! current balance: €" + getWallet());
        }
    }

