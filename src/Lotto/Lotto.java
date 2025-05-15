package Lotto;

//BACK UP TEST 1

//Lotto class represents a lottery game with three numbers and payout functionality, Price per play: EUR 100

import java.util.Random;

public class Lotto {
    private int number1;
    private int number2;
    private int number3;
    private int currentPayout;
    private boolean didYouWin;

    private Random randomNumber = new Random();

    public void setCurrentPayout(int currentPayout) {
        this.currentPayout = currentPayout;
    }

    public int getCurrentPayout() {
        return currentPayout;
    }

    public boolean getDidYouWin() {
        return didYouWin;
    }

    public void setDidYouWin(boolean didYouWin) {
        this.didYouWin = didYouWin;
    }

    //Rolls random numbers between 1 - 10
    public void rollRandomNumbers() {
        // Assuming lottery numbers between 1-45
        this.number1 = randomNumber.nextInt(10) + 1;
        this.number2 = randomNumber.nextInt(10) + 1;
        this.number3 = randomNumber.nextInt(10) + 1;
        this.didYouWin = false; // Reset win status
    }

    //Compares the user's numbers with the lottery numbers, return true if at least one number matches
    public boolean compareNumbers(int number1, int number2, int number3) {
        // Check if any of the user's numbers match the lottery numbers
        boolean youWin = (this.number1 == number1 || this.number2 == number2 || this.number3 == number3);

        this.didYouWin = youWin;
        return youWin;
    }


     //Returns the numbers and calculates payout if there's a win, return array of lottery numbers
    public int[] getNumbers(int number1, int number2, int number3, int moneyPutIn) {

        //Update the current payout with the money put in by the player
        this.currentPayout += moneyPutIn;

        //First roll random numbers
        rollRandomNumbers();

        //Second compare to see if user wom
        boolean won = compareNumbers(number1, number2, number3);

        if (won) {
            //Check if payout is >500
            if (this.currentPayout > 500) {
                this.currentPayout = 500;
            } else {
                while (this.didYouWin) {
                    rollRandomNumbers();
                    this.didYouWin = (this.number1 == number1 || this.number2 == number2 || this.number3 == number3);
                }

                //User lost, no payout
                this.currentPayout = 0;
            }

        } else {
            //User lost, no payout
            this.currentPayout = 0;
        }

        return new int[]{this.number1, this.number2, this.number3};
    }
}

