package ru.annotations.test;

import ru.annotations.annotations.After;
import ru.annotations.annotations.Before;
import ru.annotations.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestLauncher {
    public static void launch(List<?> objs) throws Exception {

        for (Object obj : objs) {
            Method[] clsMeth = obj.getClass().getDeclaredMethods();
            for (Method meth : clsMeth) {
                if (meth.getAnnotation(Before.class) != null) {
                    try {
                        meth.invoke(obj);
                    } catch (IllegalAccessException ilEx) {
                        throw new Exception("test preparation failed");
                    } catch (InvocationTargetException invExc) {
                        throw new Exception("test preparation failed");
                    }

                }
            }
            for (Method meth : clsMeth) {
                if (meth.getAnnotation(Test.class) != null) {
                    try {
                        meth.invoke(obj);
                    } catch (IllegalAccessException ilEx) {
                        throw new Exception("test run failed");
                    } catch (InvocationTargetException invExc) {
                        throw new Exception("test run failed");
                    }
                }
            }
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
}
