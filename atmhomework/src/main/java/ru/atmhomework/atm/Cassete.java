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

    public void substractAmount(int sum) throws Exception {
        if (this.amount >= sum) {
            this.amount = this.amount - sum;
        } else {
            throw new Exception("requested amount is not available");
        }
    }

    public void addAmount(int sum) {
        this.amount += sum;
    }

}
