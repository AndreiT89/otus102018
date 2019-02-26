package ru.annotations.test;

import ru.annotations.annotations.After;
import ru.annotations.annotations.Before;
import ru.annotations.annotations.Test;

public class ThirdTest {
    @Before
    public void onBefore() {
        System.out.println("Before third test");
    }

    @Test
    public void onTest() {
        System.out.println("Third test");
    }

    @After
    public void onAfter() {
        System.out.println("After third test");
    }
}
