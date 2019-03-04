package ru.atmhomework.atm;

import java.util.HashMap;
import java.util.Iterator;

public class ATM {
    private HashMap<FaceValue, Cassete> storage;

    public ATM(HashMap<FaceValue, Cassete> cassetes) {
        this.storage = cassetes;
    }

    public MoneySum get(int sum) throws Exception {
        MoneySum requestedSum = new MoneySum(sum);
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            entry.substractAmount(requestedSum.getDigits(key));
        }
        return requestedSum;
    }

    public void put(int sum) {
        MoneySum requestedSum = new MoneySum(sum);
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            entry.addAmount(requestedSum.getDigits(key));
        }
    }

    public int checkAvailableAmount(){
        int sum = 0;
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            sum+=entry.getAmount()*key.getFaceValue();
        }
        return sum;
    }
}
