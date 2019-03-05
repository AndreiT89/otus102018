package ru.atmhomework.atm;

public class Cassete {
    public Cassete(FaceValue faceValue, int amount) {
        this.faceValue = faceValue;
        this.amount = amount;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    private final FaceValue faceValue;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public int substractAmount(int sum)  {
        if (this.amount >= sum) {
            this.amount = this.amount - sum;
            return 0;
        } else {
           return  -1;
        }
    }

    public void addAmount(int sum) {
        this.amount += sum;
    }

}
