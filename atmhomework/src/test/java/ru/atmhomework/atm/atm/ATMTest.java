package ru.atmhomework.atm.atm;

import org.junit.Before;
import org.junit.Test;
import ru.atmhomework.atm.ATM;
import ru.atmhomework.atm.Cassete;
import ru.atmhomework.atm.FaceValue;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ATMTest {
    @Test
    public void get() {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 2));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(127, atm.checkAvailableAmount());

        atm.get(123);
        assertEquals(4, atm.checkAvailableAmount());
    }

    @Test
    public void getAbscentAmount() {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 30));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 0));

        ATM atm = new ATM(storage);
        assertEquals(307, atm.checkAvailableAmount());
        atm.get(223);
        assertEquals(84, atm.checkAvailableAmount());
    }

    @Test
    public void getDifferentFaceValues() {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 30));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(407, atm.checkAvailableAmount());
        atm.get(323);
        assertEquals(84, atm.checkAvailableAmount());
    }

    @Test
    public void put() {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 2));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(127, atm.checkAvailableAmount());
        HashMap<FaceValue, Integer> sum = new HashMap<>();
        sum.put(FaceValue.ONES, 3);
        sum.put(FaceValue.TENS, 2);
        sum.put(FaceValue.HUNDREDS, 1);
        atm.put(sum);
        assertEquals(250, atm.checkAvailableAmount());

    }
}
