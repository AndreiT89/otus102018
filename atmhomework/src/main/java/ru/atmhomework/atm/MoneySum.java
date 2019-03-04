package ru.atmhomework.atm;

import java.util.HashMap;

public class MoneySum {
    public int getSum() {
        return sum;
    }

    private final int sum;
    private HashMap<FaceValue, Integer> digits;


    public MoneySum(int sum){
        this.sum = sum;
        digits = new HashMap<>();
        digits.put(FaceValue.ONES, sum%10);
        digits.put(FaceValue.TENS, sum%100/10);
        digits.put(FaceValue.HUNDREDS, sum/100);

    }

    public Integer getDigits(FaceValue key){
        return this.digits.get(key);
    }
}
