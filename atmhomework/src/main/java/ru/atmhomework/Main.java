package ru.atmhomework;

import ru.atmhomework.atm.ATM;
import ru.atmhomework.atm.Cassete;
import ru.atmhomework.atm.FaceValue;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 2));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        System.out .println(atm.checkAvailableAmount());
    }
}
