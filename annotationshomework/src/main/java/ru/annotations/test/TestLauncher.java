package ru.annotations.test;

import ru.annotations.annotations.After;
import ru.annotations.annotations.Before;
import ru.annotations.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestLauncher {
    public static void launch(Class cls) throws Exception {
        Method[] clsMeth = cls.getClass().getDeclaredMethods();
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(Test.class) != null) {
                Object obj = cls.getDeclaredConstructor().newInstance();
                launchBefore(clsMeth, obj);
                launchTest(clsMeth, obj);
                launchAfter(clsMeth, obj);
            }
        }

    }

    private static void launchBefore(Method[] clsMeth, Object obj) throws Exception {
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(Before.class) != null) {
                try {
                    meth.invoke(obj);
                } catch (IllegalAccessException ilEx) {
                    throw new Exception("test preparation failed");
                } catch (InvocationTargetException invExc) {
                    throw new Exception("test preparation failed");
                } finally {
                    launchAfter(clsMeth, obj);
                }

            }
        }
    }

    private static void launchTest(Method[] clsMeth, Object obj) throws Exception {
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(Test.class) != null) {
                try {
                    meth.invoke(obj);
                } catch (IllegalAccessException ilEx) {
                    throw new Exception("test run failed");
                } catch (InvocationTargetException invExc) {
                    throw new Exception("test run failed");
                } finally {
                    launchAfter(clsMeth, obj);
                }
            }
        }
    }

    private static void launchAfter(Method[] clsMeth, Object obj) throws Exception {
        for (Method meth : clsMeth) {
            if (meth.getAnnotation(After.class) != null) {
                try {
                    meth.invoke(obj);
                } catch (IllegalAccessException ilEx) {
                    throw new Exception("test closing failed");
                } catch (InvocationTargetException invExc) {
                    throw new Exception("test closing failed");
                }
            }
        }
    }
}
