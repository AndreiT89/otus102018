package ru.atmhomework.atm;

import java.util.HashMap;
import java.util.Iterator;

public class ATM {
    private HashMap<FaceValue, Cassete> storage;

    public ATM(HashMap<FaceValue, Cassete> cassetes) {
        this.storage = cassetes;
    }

    public HashMap<FaceValue, Cassete> get(int sum) {
        HashMap<FaceValue, Cassete> requestedSum = new HashMap<>();
        requestedSum.put(FaceValue.ONES, new Cassete(FaceValue.ONES,sum%10));
        requestedSum.put(FaceValue.TENS, new Cassete(FaceValue.ONES,sum%100/10));
        requestedSum.put(FaceValue.HUNDREDS, new Cassete(FaceValue.ONES,sum/100));
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            int result = entry.substractAmount(requestedSum.get(key).getAmount());
            if (result < 0){
                requestedSum.remove(key);
            }
        }
        return requestedSum;
    }

    public void put(HashMap<FaceValue, Cassete> sum) {
        Iterator itr = this.storage.keySet().iterator();
        while (itr.hasNext()) {
            FaceValue key = (FaceValue) itr.next();
            Cassete entry = this.storage.get(key);
            entry.addAmount(sum.get(key).getAmount());
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
