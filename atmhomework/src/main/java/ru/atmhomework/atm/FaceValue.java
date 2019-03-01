package ru.atmhomework.atm;

public enum FaceValue {
    ONES(1),
    TENS(10),
    HUNDREDS(100),
    THOUSANDS(1000);
    private int faceValue;
    FaceValue(int faceValue){
        this.faceValue = faceValue;
    }
    public int getFaceValue(){
        return faceValue;
    }
}
