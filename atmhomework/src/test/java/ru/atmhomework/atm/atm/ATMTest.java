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
    public void get() throws Exception {
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
    public void put() {
        HashMap<FaceValue, Cassete> storage = new HashMap<>();
        storage.put(FaceValue.ONES, new Cassete(FaceValue.ONES, 7));
        storage.put(FaceValue.TENS, new Cassete(FaceValue.TENS, 2));
        storage.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, 1));

        ATM atm = new ATM(storage);
        assertEquals(127, atm.checkAvailableAmount());

        atm.put(123);
        assertEquals(250, atm.checkAvailableAmount());

    }
}
