package ru.annotations;

import ru.annotations.test.FirstTest;
import ru.annotations.test.SecondTest;
import ru.annotations.test.ThirdTest;
import java.util.ArrayList;
import java.util.List;
import ru.annotations.test.TestLauncher;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<>();
        FirstTest ft = new FirstTest();
        SecondTest st = new SecondTest();
        ThirdTest tt = new ThirdTest();
        list.add(ft);
        list.add(st);
        list.add(tt);
        TestLauncher.launch(list);
    }


}