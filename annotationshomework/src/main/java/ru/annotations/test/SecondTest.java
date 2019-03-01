package ru.annotations.test;

import ru.annotations.annotations.After;
import ru.annotations.annotations.Before;
import ru.annotations.annotations.Test;

public class SecondTest {
    @Before
    public void onBefore() {
        System.out.println("Before second test" + this.toString());
    }

    @Test
    public void onTest()throws Exception {
        System.out.println("Second test");
        throw new Exception("test");
    }

    @Test
    public void onTestSecond() {
        System.out.println("Second test again");
    }

    @After
    public void onAfter() {
        System.out.println("After second test");
    }
}
