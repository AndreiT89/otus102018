package ru.annotations.test;

import ru.annotations.annotations.After;
import ru.annotations.annotations.Before;
import ru.annotations.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestLauncher {
    public static void launch(Class cls) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Method[] clsMeth = cls.getDeclaredMethods();
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(Test.class) != null) {
                Object obj = cls.getDeclaredConstructor().newInstance();
                try {
                    launchBefore(clsMeth, obj);
                    launchTest(meth, obj);
                    launchAfter(clsMeth, obj);
                } catch (InvocationTargetException invEx) {
                    System.out.println("test failed");
                } catch (IllegalAccessException illExc) {
                    System.out.println("test failed");
                } finally {
                    launchAfter(clsMeth, obj);
                }

            }
        }

    }

    private static void launchBefore(Method[] clsMeth, Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(Before.class) != null) {
                meth.invoke(obj);

            }
        }
    }

    private static void launchTest(Method meth, Object obj) throws InvocationTargetException, IllegalAccessException {

        if (meth.getAnnotation(Test.class) != null) {
            meth.invoke(obj);

        }

    }

    private static void launchAfter(Method[] clsMeth, Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(After.class) != null) {
                meth.invoke(obj);
            }
        }
    }
}
