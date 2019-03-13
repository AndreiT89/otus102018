package ru.atmhomework.atm;

import java.util.Iterator;
import java.util.TreeMap;

public class ATM {
    private TreeMap<FaceValue, Cassete> storage;

    public ATM(TreeMap<FaceValue, Cassete> cassetes) {
        this.storage = cassetes;
    }

    public TreeMap<FaceValue, Integer> get(Integer sum) {
        TreeMap<FaceValue, Integer> requestedSum = new TreeMap<>();
        int remainder = sum.intValue();
        Iterator itr = storage.descendingKeySet().iterator();
        while(itr.hasNext()){
            FaceValue value = (FaceValue)itr.next();
            Integer max = remainder/value.getFaceValue();
            Integer totalInCassette = storage.get(value).getAmount();
            Integer sumIssue = Integer.min(max,totalInCassette);
            requestedSum.put(value,sumIssue);
            storage.get(value).substractAmount(sumIssue);
            remainder=remainder-sumIssue*value.getFaceValue();
        }
        return requestedSum;
    }

    public void put(TreeMap<FaceValue, Integer> sum) {
        Iterator itr = sum.keySet().iterator();
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
