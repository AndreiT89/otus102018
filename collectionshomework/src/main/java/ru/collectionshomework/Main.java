package ru.collectionshomework;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        MyArrayList<String> array = new MyArrayList<>();
        String[] elements = {"one", "two", "three", "six", "seven", "five", "four", "eight", "nine"};
        Collections.addAll(array, elements);
        Iterator itr = array.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next().toString());
        }

        MyArrayList<String> arrayNew = new MyArrayList<>();
        Collections.copy(array, arrayNew);
        Iterator itrCopied = arrayNew.listIterator();
        while(itrCopied.hasNext()){
            System.out.println(itrCopied.next().toString());
        }
        System.out.println(arrayNew.toString());
        Collections.sort(array, Comparator.naturalOrder());
        itr = null;
        itr = array.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next().toString());
        }
    }
}
