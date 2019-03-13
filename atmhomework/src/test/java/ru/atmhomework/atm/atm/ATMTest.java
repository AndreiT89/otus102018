package ru.atmhomework.atm.atm;

import org.junit.Test;
import ru.atmhomework.atm.ATM;
import ru.atmhomework.atm.Cassete;
import ru.atmhomework.atm.FaceValue;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class ATMTest {
    @Test
    public void get() {
        TreeMap<FaceValue, Cassete> storage = new TreeMap<>();
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
        TreeMap<FaceValue, Cassete> storage = new TreeMap<>();
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

        TreeMap<FaceValue, Cassete> storage = new TreeMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 30));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(407, atm.checkAvailableAmount());
        atm.get(323);
        assertEquals(84, atm.checkAvailableAmount());
    }
    @Test
    public void getLargeAmount(){
        TreeMap<FaceValue, Cassete> storage = new TreeMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 30));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(407, atm.checkAvailableAmount());
        atm.get(423);
        assertEquals(0, atm.checkAvailableAmount());
    }
    @Test
    public void put() {
        TreeMap<FaceValue, Cassete> storage = new TreeMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 2));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(127, atm.checkAvailableAmount());
        TreeMap<FaceValue, Integer> sum = new TreeMap<>();
        sum.put(FaceValue.ONES, 3);
        sum.put(FaceValue.TENS, 2);
        sum.put(FaceValue.HUNDREDS, 1);
        atm.put(sum);
        assertEquals(250, atm.checkAvailableAmount());

    }
}
