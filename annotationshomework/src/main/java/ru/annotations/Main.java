package ru.annotations;

import ru.annotations.test.FirstTest;
import ru.annotations.test.SecondTest;
import ru.annotations.test.ThirdTest;

import java.util.ArrayList;
import java.util.List;

import ru.annotations.test.TestLauncher;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Class> list = new ArrayList<>();
        list.add(FirstTest.class);
        list.add(SecondTest.class);
        list.add(ThirdTest.class);
        for (Class cls : list) {
            TestLauncher.launch(cls);
        }
    }


}