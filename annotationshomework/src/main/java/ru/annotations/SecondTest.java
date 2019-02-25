package ru.annotations;

public class SecondTest {
    @Before
    public void onBefore() {
        System.out.println("Before second test");
    }

    @Test
    public void onTest() {
        System.out.println("Second test");
    }

    @After
    public void onAfter() {
        System.out.println("After second test");
    }
}
