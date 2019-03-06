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
        int result = 0;
        if (sum <= checkAvailableAmount()) {
            requestedSum.put(FaceValue.ONES, new Cassete(FaceValue.ONES, sum % 10));
            requestedSum.put(FaceValue.TENS, new Cassete(FaceValue.TENS, sum % 100 / 10));
            requestedSum.put(FaceValue.HUNDREDS, new Cassete(FaceValue.HUNDREDS, sum / 100));
            result = withdrawSum(requestedSum.get(FaceValue.HUNDREDS), FaceValue.TENS);

            result = withdrawSum(requestedSum.get(FaceValue.TENS), FaceValue.ONES);

            result = withdrawSum(requestedSum.get(FaceValue.ONES), null);

        }
        if (result == -1) {
            requestedSum = null;
        }
        return requestedSum;
    }

    private int withdrawSum(Cassete requestedSum, FaceValue lowerFace) {
        int result = this.storage.get(requestedSum.getFaceValue()).substractAmount(requestedSum.getAmount());
        if (result == -1 && lowerFace != null) {
            result = this.storage.get(lowerFace).substractAmount(requestedSum.getAmount() * 10);
        } else if (result == -1 && lowerFace == null) {
            result = -1;
        }
        return result;
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
