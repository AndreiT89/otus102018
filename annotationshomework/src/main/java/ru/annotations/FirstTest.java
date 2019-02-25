package ru.annotations;

public class FirstTest {
    @Before
    public void onBefore() {
        System.out.println("Before first test");
    }

    @Test
    public void onTest() {
        System.out.println("First test");
    }

    @After
    public void onAfter() {
        System.out.println("After first test");
    }
}
