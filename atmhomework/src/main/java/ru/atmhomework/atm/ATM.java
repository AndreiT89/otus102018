package ru.atmhomework.atm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ATM {
    private HashMap<FaceValue, Cassete> storage;

    public ATM(HashMap<FaceValue, Cassete> cassetes) {
        this.storage = cassetes;
    }

    public HashMap<FaceValue, Cassete> get(int sum) {
        HashMap<FaceValue, Cassete> requestedSum = new HashMap<>();
        if (sum <= checkAvailableAmount()) {
            requestedSum.put(FaceValue.ONES, new Cassete(FaceValue.ONES, sum % 10));
            requestedSum.put(FaceValue.TENS, new Cassete(FaceValue.TENS, sum % 100 / 10));
            requestedSum.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, sum / 100));
            int result = this.storage.get(FaceValue.HUNDREDS).substractAmount(requestedSum.get(FaceValue.HUNDREDS).getAmount());
            if (result == -1) {
                result = this.storage.get(FaceValue.TENS).substractAmount(requestedSum.get(FaceValue.HUNDREDS).getAmount() * 10);
            }
            result = this.storage.get(FaceValue.TENS).substractAmount(requestedSum.get(FaceValue.TENS).getAmount());
            if (result == -1) {
                result = this.storage.get(FaceValue.ONES).substractAmount(requestedSum.get(FaceValue.TENS).getAmount() * 10);
            }
            result = this.storage.get(FaceValue.ONES).substractAmount(requestedSum.get(FaceValue.ONES).getAmount());
        }
        return requestedSum;
    }

    public void put(HashMap<FaceValue, Integer> sum) {
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            entry.addAmount(sum.get(key));
        }
    }

    public int checkAvailableAmount() {
        int sum = 0;
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            sum += entry.getAmount() * key.getFaceValue();
        }
        return sum;
    }
}
